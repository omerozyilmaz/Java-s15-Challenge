package com.example.myLibrary;

import java.util.ArrayList;
import java.util.List;

public class Author extends Person {
    private List<Book> booksWritten;

    public Author(String name) {
        super(name);
        this.booksWritten = new ArrayList<>();
    }

    @Override
    public String whoYouAre() {
        return "I am an Author.";
    }

    public void addBook(Book book) {
        booksWritten.add(book);
    }

    public List<Book> getBooksWritten() {
        return new ArrayList<>(booksWritten);
    }
}
