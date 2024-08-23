package com.example.myLibrary;

import java.util.*;

public class BookInventory {
    private Map<String, List<Book>> bookInventory;

    public BookInventory() {
        this.bookInventory = new HashMap<>();
    }

    public void addBook(Book book) {
        bookInventory.computeIfAbsent(book.getName(), k -> new ArrayList<>()).add(book);
    }

    public List<Book> getBooksByName(String name) {
        return bookInventory.getOrDefault(name, Collections.emptyList());
    }


    public Map<String, List<Book>> getAllBooks() {
        return bookInventory;
    }
}
