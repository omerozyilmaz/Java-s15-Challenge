import enums.Title;
import enums.Type;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        MemberRecord member1 = new MemberRecord("smurfCar", "smurf", Type.STUDENT);
        library.addMember(member1);
        Librarian librarian = new Librarian("Mr. Librarian", "000");


        library.addMember(new MemberRecord("Mahmut Manzur", "Manzur", Type.STUDENT));
        library.addMember(new MemberRecord("Berke Asim", "Thaildrin", Type.FACULTY));
        library.addMember(new MemberRecord("Omer Ozyilmaz", "Omerozy21", Type.GRADUATE));

        library.addBook(new Book(new Author("J.K. Rowling"), "Harry Potter and the Philosopher's Stone", Title.FANTASY, "1st Edition", "1997-06-26"));
        library.addBook(new Book(new Author("George Orwell"), "1984", Title.FICTION, "1st Edition", "1949-06-08"));
        library.addBook(new Book(new Author("J.R.R. Tolkien"), "The Lord of the Rings", Title.FANTASY, "1st Edition", "1954-07-29"));
        library.addBook(new Book(new Author("Harper Lee"), "To Kill a Mockingbird", Title.FICTION, "1st Edition", "1960-07-11"));
        library.addBook(new Book(new Author("F. Scott Fitzgerald"), "The Great Gatsby", Title.FICTION, "1st Edition", "1925-04-10"));
        library.addBook(new Book(new Author("Mary Shelley"), "Frankenstein", Title.SCIENCE, "1st Edition", "1818-01-01"));
        library.addBook(new Book(new Author("Stephen Hawking"), "A Brief History of Time", Title.NONFICTION, "1st Edition", "1988-04-01"));
        library.addBook(new Book(new Author("Yuval Noah Harari"), "Sapiens: A Brief History of Humankind", Title.NONFICTION, "1st Edition", "2011-01-01"));
        library.addBook(new Book(new Author("Jane Austen"), "Pride and Prejudice", Title.FICTION, "1st Edition", "1813-01-28"));
        library.addBook(new Book(new Author("Markus Zusak"), "The Book Thief", Title.FICTION, "1st Edition", "2005-09-01"));



        System.out.println("Please select your role:");
        System.out.println("1. Librarian");
        System.out.println("2. Member");
        System.out.print("Selection: ");
        int userType = scanner.nextInt();
        scanner.nextLine();

        if (userType == 1) {
            System.out.print("Please enter your password: ");
            String password = scanner.nextLine();

            if (librarian.verifyPassword(password)) {
                handleLibrarianActions(library, scanner, librarian);
            } else {
                System.out.println("Incorrect password! Exiting...");
            }
        } else if (userType == 2) {
            System.out.print("Please enter your member ID: ");
            String memberId = scanner.nextLine();
            MemberRecord member = library.findMemberById(memberId);

            if (member != null) {
                handleMemberActions(library, scanner, librarian, member);
            } else {
                System.out.println("Member not found! Exiting...");
            }
        } else {
            System.out.println("Invalid selection! Exiting...");
        }
    }

    private static void handleLibrarianActions(Library library, Scanner scanner, Librarian librarian) {
        while (true) {
            System.out.println("1. Add a Book");
            System.out.println("2. List Books");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBookToLibrary(library, scanner, librarian);
                    break;
                case 2:
                    library.displayBooksForLibrarian();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid selection! Please try again.");
            }
        }
    }

    private static void handleMemberActions(Library library, Scanner scanner, Librarian librarian, MemberRecord member) {
        while (true) {
            System.out.println("1. List Books");
            System.out.println("2. Request a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    library.displayBooksForMember();
                    break;
                case 2:
                    System.out.print("Enter the name of the book you want: ");
                    String bookName = scanner.nextLine();
                    librarian.issueBook(library, member, bookName);
                    break;
                case 3:
                    System.out.print("Enter the name of the book you want to return: ");
                    String returnBookName = scanner.nextLine();
                    List<Book> booksOwned = member.getBooksOwned();
                    Book bookToReturn = null;

                    for (Book book : booksOwned) {
                        if (book.getName().equalsIgnoreCase(returnBookName)) {
                            bookToReturn = book;
                            break;
                        }
                    }

                    if (bookToReturn != null) {
                        librarian.returnBook(library, member, bookToReturn);
                    } else {
                        System.out.println("No record of you having borrowed this book.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid selection! Please try again.");
            }
        }
    }

    private static void addBookToLibrary(Library library, Scanner scanner, Librarian librarian) {
        System.out.print("Author: ");
        String authorName = scanner.nextLine();
        Author author = new Author(authorName);

        System.out.print("Book Name: ");
        String name = scanner.nextLine();

        System.out.println("Available Titles:");
        for (Title title : Title.values()) {
            System.out.println("- " + title);
        }

        System.out.print("Select a Title: ");
        String titleString = scanner.nextLine().toUpperCase();
        Title title = Title.valueOf(titleString);

        System.out.print("Edition: ");
        String edition = scanner.nextLine();

        System.out.print("Date of Purchase: ");
        String dateOfPurchase = scanner.nextLine();

        Book book = new Book(author, name, title, edition, dateOfPurchase);
        librarian.addBookToLibrary(library, book);
    }
}