package com.example.myLibrary;

import com.example.myLibrary.enums.Title;

import java.util.UUID;

public class Book {
    private String bookId;
    private Author author;
    private String name;
    private Title title;
    private String status;
    private String edition;
    private String dateOfPurchase;
    private Person owner;

    public Book(Author author, String name, Title title, String edition, String dateOfPurchase) {
        this.bookId = UUID.randomUUID().toString();
        this.author = author;
        this.name = name;
        this.title = title;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
    }

    public String getBookId() {
        return bookId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
