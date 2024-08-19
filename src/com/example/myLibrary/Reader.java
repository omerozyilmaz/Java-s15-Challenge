package com.example.myLibrary;

import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book> booksOwned;

    public Reader(String name) {
        super(name);
        this.booksOwned = new ArrayList<>();
    }

    @Override
    public String whoYouAre() {
        return "I am a com.example.myLibrary.Reader.";
    }

    public void borrowBook(Book book) {
        booksOwned.add(book);
    }

    public void returnBook(Book book) {
        booksOwned.remove(book);
    }

    public List<Book> getBooksOwned() {
        return new ArrayList<>(booksOwned); // Return a copy to protect encapsulation
    }
}
