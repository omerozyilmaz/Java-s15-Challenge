import java.util.List;

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

    public void searchBook(Library library, String bookName) {
        List<Book> books = library.getBooksByName(bookName);
        if (books.isEmpty()) {
            System.out.println("No books found with name: " + bookName);
        } else {
            System.out.println(books.size() + " copies of '" + bookName + "' found.");
        }
    }

    public void issueBook(Library library, MemberRecord member, String bookName) {
        List<Book> books = library.getBooksByName(bookName);
        if (books.isEmpty()) {
            System.out.println("Sorry, the book '" + bookName + "' is not available.");
            return;
        }

        if (member.getNoBooksIssued() >= member.getMaxBookLimit()) {
            System.out.println("You have reached your book limit. Please return a book before issuing a new one.");
            return;
        }

        Book bookToIssue = books.remove(0);
        member.incrementBooksIssued();
        member.borrowBook(bookToIssue);
        bookToIssue.changeOwner(member);
        System.out.println("Book issued: " + bookToIssue.getName() + " to " + member.getName());
    }
    public void showBooksOwned(MemberRecord member) {
        List<Book> booksOwned = member.getBooksOwned();
        if (booksOwned.isEmpty()) {
            System.out.println(member.getName() + " does not own any books.");
        } else {
            System.out.println(member.getName() + " owns the following books:");
            for (Book book : booksOwned) {
                System.out.println("- " + book.getName());
            }
        }
    }

    public void returnBook(Library library, MemberRecord member, Book book) {
        if (member.getBooksOwned().contains(book)) {
            member.returnBook(book);
            library.takeBackBook(book, member);
            member.decrementBooksIssued();
            System.out.println("Book returned: " + book.getName());
        } else {
            System.out.println("This book was not borrowed by " + member.getName());
        }
    }

    public void addBookToLibrary(Library library, Book book) {
        library.addBook(book);
        System.out.println("Book added to the library: " + book.getName() + " (" + book.getTitle() + ")");
    }

    public void createBill() {
    }
}