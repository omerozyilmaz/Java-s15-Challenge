import enums.Title;

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
        this.status = "Available";
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.owner = null;
    }

    public String getBookId() {
        return bookId;
    }

    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public Title getTitle() {
        return title;
    }

    public String getEdition() {
        return edition;
    }

    public String getStatus() {
        return status;
    }

    public void changeOwner(Person newOwner) {
        this.owner = newOwner;
        this.status = (newOwner == null) ? "Available" : "Checked out";
    }

    public Person getOwner() {
        return owner;
    }

    public void display() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author.getName());
        System.out.println("Edition: " + edition);
        System.out.println("Status: " + status);
        System.out.println("Date of Purchase: " + dateOfPurchase);
        System.out.println("Owner: " + (owner != null ? owner.getName() : "None"));
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
}