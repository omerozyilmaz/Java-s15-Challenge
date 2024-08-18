import enums.Title;

public class Book {
    private int bookId;
    private Author author;
    private String name;
    private Title title;
    private int price;
    private String status;
    private String edition;
    private String dateOfPurchase;
    private Reader owner;

    public Book(int bookId, Author author, String name, Title title, int price, String status, String edition, String dateOfPurchase) {
        this.bookId = bookId;
        this.author = author;
        this.name = name;
        this.title = title;
        this.price = price;
        this.status = "Available now.";
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
        this.owner = null;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return this.name;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void changeOwner(Reader newOwner) {
        this.owner = newOwner;
        updateStatus();
    }

    private String getOwnerName() {
        return this.owner != null ? this.owner.getName() : "No owner at the moment.";
    }
    protected String getOwnerNameForLibrarian(Librarian librarian, String password) {
        if (librarian.verifyPassword(password)) {
            return getOwnerName();
        }
        return "Access denied: Incorrect password.";
    }

    public void display() {
        System.out.println("Book ID: " + this.bookId);
        System.out.println("Title: " + this.name);
        System.out.println("Author: " + this.author.getName());
        System.out.println("Price: " + this.price);
        System.out.println("Status: " + this.status);
        System.out.println("Edition: " + this.edition);
        System.out.println("Date of Purchase: " + this.dateOfPurchase);
        System.out.println("Owner: " + (this.owner != null ? this.owner.getName() : "No owner at the moment"));
    }

    public void updateStatus() {
        this.status = (owner == null) ? "Available now." : "Not currently available.";
    }
}