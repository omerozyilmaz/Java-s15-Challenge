package com.example.myLibrary;

import com.example.myLibrary.enums.Title;

import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private Set<MemberRecord> memberSet;
    private Map<String, List<Book>> bookInventory;
    private List<Author> authorsList;
    private MemberManager memberManager;

    public Library() {
        memberSet = new HashSet<>();
        bookInventory = new HashMap<>();
        authorsList = new ArrayList<>();
        memberManager = new MemberManager();
    }

    private Author CreateAuthor(String authorName) {
        for (Author author : authorsList) {
            if (author.getName().equals(authorName)) {
                return author;
            }
        }
        Author newAuthor = new Author(authorName);
        authorsList.add(newAuthor);
        return newAuthor;
    }

    public void addBook(String authorName, String bookName, Title title, String edition, String dateOfPurchase) {
        Author author = CreateAuthor(authorName);
        Book book = new Book(author, bookName, title, edition, dateOfPurchase);
        addBook(book);
        author.addBook(book);
    }


    public List<Author> getAllAuthors() {
        return new ArrayList<>(authorsList);
    }

    public void addBook(Book book) {
        bookInventory.computeIfAbsent(book.getName(), k -> new ArrayList<>()).add(book);
    }

    public List<Book> getBooksByName(String name) {
        return bookInventory.getOrDefault(name, Collections.emptyList());
    }

    public List<String> getBookIdsByName(String bookName) {
        return bookInventory.getOrDefault(bookName, Collections.emptyList()).stream()
                .map(Book::getBookId)
                .collect(Collectors.toList());
    }

    public void addMember(MemberRecord member) {
        memberManager.addMember(member);
    }

    public MemberRecord getMemberById(String memberId) {
        return memberManager.getMemberById(memberId);
    }

    public Set<MemberRecord> getAllMembers() {
        return memberManager.getAllMembers();
    }

    public void displayBooksForLibrarian() {
        System.out.println("Library Inventory:");
        bookInventory.forEach((bookName, books) -> {
            System.out.println("Book Name: " + bookName);
            books.forEach(book ->
                    System.out.println(" - Edition: " + book.getEdition() + ", Author: " + book.getAuthor().getName()));
        });
    }

    public Map<String, List<Book>> getBookInventory() {
        return bookInventory;
    }

    public MemberManager getMemberManager() {
        return memberManager;
    }
}
