package com.example.myLibrary;

import com.example.myLibrary.enums.Title;
import com.example.myLibrary.enums.Type;

import java.lang.reflect.Member;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        LibraryComputer libraryComputer = new LibraryComputer();
        Librarian librarian = new Librarian("Emre", "000");

        MemberRecord member1 = new MemberRecord("smurfCar", "smurf", Type.STUDENT);
        library.addMember(member1);
        library.addMember(new MemberRecord("Mahmut Manzur", "Manzur", Type.STUDENT));
        library.addMember(new MemberRecord("Berke Asim", "Thaildrin", Type.FACULTY));
        library.addMember(new MemberRecord("Omer Ozyilmaz", "Omerozy21", Type.GRADUATE));

        library.addBook("J.K. Rowling", "Harry Potter and the Philosopher's Stone", Title.FANTASY, "1st Edition", "1997-06-26");
        library.addBook("George Orwell", "1984", Title.FICTION, "1st Edition", "1949-06-08");
        library.addBook("George Orwell", "1984", Title.FICTION, "1st Edition", "1949-06-08");
        library.addBook("George Orwell", "1984", Title.FICTION, "1st Edition", "1949-06-08");
        library.addBook("J.R.R. Tolkien", "The Lord of the Rings", Title.FANTASY, "1st Edition", "1954-07-29");
        library.addBook("Harper Lee", "To Kill a Mockingbird", Title.FICTION, "1st Edition", "1960-07-11");
        library.addBook("F. Scott Fitzgerald", "The Great Gatsby", Title.FICTION, "1st Edition", "1925-04-10");
        library.addBook("Mary Shelley", "Frankenstein", Title.SCIENCE, "1st Edition", "1818-01-01");
        library.addBook("Stephen Hawking", "A Brief History of Time", Title.NONFICTION, "1st Edition", "1988-04-01");
        library.addBook("Yuval Noah Harari", "Sapiens: A Brief History of Humankind", Title.NONFICTION, "1st Edition", "2011-01-01");
        library.addBook("Jane Austen", "Pride and Prejudice", Title.FICTION, "1st Edition", "1813-01-28");
        library.addBook("Markus Zusak", "The Book Thief", Title.FICTION, "1st Edition", "2005-09-01");
        librarian.issueBook(library, "omerozy21","1984");

        System.out.println("Please select your role:");
        System.out.println("1. Librarian");
        System.out.println("2. Member");
        System.out.print("Selection: ");
        int userType = scanner.nextInt();
        scanner.nextLine();

        if (userType == 1) {

            System.out.print("Please enter your name: ");
            String name = scanner.nextLine().toLowerCase(Locale.ROOT);
            System.out.print("Please enter your password: ");
            String password = scanner.nextLine();

            if (librarian.verifyPassword(password) && librarian.getName().toLowerCase(Locale.ROOT).equals(name)) {
                libraryComputer.LibrarianActions(library, scanner, librarian);
            } else {
                System.out.println("Incorrect name or password! Exiting...");
            }
        } else if (userType == 2) {

            System.out.print("Please enter your member ID: ");
            String memberId = scanner.nextLine();
            MemberRecord member = library.getMemberById(memberId);

            if (member != null) {
                libraryComputer.MemberActions(library, scanner, librarian, member);
            } else {
                System.out.println("Member not found! Exiting...");
            }
        } else {
            System.out.println("Invalid selection! Exiting…");
        }
    }
}
