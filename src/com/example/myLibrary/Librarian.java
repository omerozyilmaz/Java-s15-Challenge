package com.example.myLibrary;

import java.util.List;
import java.util.Optional;

public class Librarian extends Person {
    private String password;

    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }

    @Override
    public String whoYouAre() {
        return "I am a Librarian.";
    }

    public boolean verifyPassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }

    public void issueBook(Library library, MemberRecord member, String bookName) {
        List<Book> books = library.getBooksByName(bookName);

        if (books != null && !books.isEmpty()) {
            Optional<Book> bookToIssue = books.stream()
                    .filter(book -> book.getOwner() == null)
                    .findFirst();

            if (bookToIssue.isPresent()) {
                Book book = bookToIssue.get();
                book.setOwner(member);
                member.borrowBook(book);
                System.out.println("System Logs :");
                System.out.println("Issued book: " + book.getName() + " to member of " + member.getName());
            } else {
                System.out.println("All copies of the book '" + bookName + "' are currently issued to other members.");
            }
        } else {
            System.out.println("Book not found or not available.");
        }
    }


    public void issueBook(Library library, String memberId, String bookName) {
        MemberRecord member = library.getMemberById(memberId);

        if (member != null) {
            issueBook(library, member, bookName);
        } else {
            System.out.println("Member not found with ID: " + memberId);
        }
    }

    public void returnBook(Library library, MemberRecord member, Book book) {
        if (book.getOwner() != null && book.getOwner().equals(member)) {
            member.returnBook(book);
            book.setOwner(null);
            System.out.println("Returned book: " + book.getName() + " from member: " + member.getName());
        } else {
            System.out.println("This book wasn't issued to " + member.getName() + " or the book is not recognized.");
        }
    }
}
