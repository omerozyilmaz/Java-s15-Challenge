package com.example.myLibrary;

import com.example.myLibrary.enums.Title;
import com.example.myLibrary.interfaces.LibraryActions;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class LibraryComputer implements LibraryActions {

    private List<Author> authorsList;
    private List<Book> booksList;
    private List<String> bookIdsList;
    private Set<MemberRecord> membersList;

    @Override
    public void LibrarianActions(Library library, Scanner scanner, Librarian librarian) {

        authorsList = library.getAllAuthors();
        membersList = library.getMemberManager().getAllMembers();

        while (true) {
            System.out.println("1. Add a Book");
            System.out.println("2. List Books");
            System.out.println("3. Issue a Book to a Member");
            System.out.println("4. View Member's Books");
            System.out.println("5. Find Books");
            System.out.println("6. Delete a Book");
            System.out.println("7. List Authors");
            System.out.println("8. Search Author's Books");
            System.out.println("9. List Members");
            System.out.println("10. Exit");
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
                    System.out.print("Enter Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter Book Name: ");
                    String bookName = scanner.nextLine();
                    MemberRecord members = library.getMemberManager().getMemberById(memberId);
                    if (members != null) {
                        librarian.issueBook(library, members, bookName);
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter Member ID: ");
                    memberId = scanner.nextLine();
                    members = library.getMemberManager().getMemberById(memberId);
                    if (members != null) {
                        System.out.println("Books owned by " + members.getName() + ":");
                        members.getBooksOwned().forEach(book ->
                                System.out.println(book.getName()));
                    } else {
                        System.out.println("Member not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Book Name: ");
                    String bookNameScan = scanner.nextLine();
                    bookIdsList = library.getBookIdsByName(bookNameScan);

                    if (!bookIdsList.isEmpty()) {
                        System.out.println("Found books with name '" + bookNameScan + "':");
                        bookIdsList.forEach(id -> System.out.println(" - Book ID: " + id));
                    } else {
                        System.out.println("No books found with the name '" + bookNameScan + "'.");
                    }
                    break;
                case 6:
                    System.out.print("Enter Book ID: ");
                    String bookId = scanner.nextLine();

                    boolean bookDeleted = library.getBookInventory().values().stream()
                            .flatMap(List::stream)
                            .filter(book -> book.getBookId().equals(bookId))
                            .findFirst()
                            .map(book -> {
                                library.getBookInventory().get(book.getName()).remove(book);
                                System.out.println("Book '" + book.getName() + "' has been deleted from the library.");
                                return true;
                            })
                            .orElse(false);

                    if (!bookDeleted) {
                        System.out.println("Book ID not found.");
                    }
                    break;
                case 7:
                    selectAuthorByName(scanner);
                    break;
                case 8:
                    Author selectedAuthor = selectAuthorByName(scanner);
                    if (selectedAuthor != null) {
                        booksList = selectedAuthor.getBooksWritten();
                        if (booksList.isEmpty()) {
                            System.out.println(selectedAuthor.getName() + " has not written any books.");
                        } else {
                            System.out.println("Books written by " + selectedAuthor.getName() + ":");
                            booksList.forEach(book -> System.out.println(" - " + book.getName()));
                        }
                    }
                    break;
                case 9:
                    if (membersList.isEmpty()) {
                        System.out.println("No members found in the system.");
                    } else {
                        System.out.println("List of Members in the Library:");
                        membersList.stream()
                                .sorted(Comparator.comparing(MemberRecord::getName))
                                .forEach(member -> System.out.println(" - " + member.getName() + " (ID: " + member.getMemberId() + ")"));
                    }
                    break;
                case 10:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @Override
    public void MemberActions(Library library, Scanner scanner, Librarian librarian, MemberRecord member) {
        while (true) {
            System.out.println("1. List Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    library.displayBooksForLibrarian();
                    break;
                case 2:
                    System.out.print("Enter Book Name: ");
                    String bookName = scanner.nextLine();
                    librarian.issueBook(library, member, bookName);
                    break;
                case 3:
                    System.out.print("Enter Book Name: ");
                    String bookNameToReturn = scanner.nextLine();
                    booksList = member.getBooksOwned();
                    Book bookToReturn = booksList.stream()
                            .filter(b -> b.getName().equals(bookNameToReturn))
                            .findFirst()
                            .orElse(null);
                    if (bookToReturn != null) {
                        librarian.returnBook(library, member, bookToReturn);
                    } else {
                        System.out.println("Book not found in member's possession.");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    @Override
    public void addBookToLibrary(Library library, Scanner scanner, Librarian librarian) {
        System.out.print("Enter Author's Name: ");
        String authorName = scanner.nextLine();
        System.out.print("Enter Book Name: ");
        String bookName = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Edition: ");
        String edition = scanner.nextLine();
        System.out.print("Enter Date of Purchase: ");
        String dateOfPurchase = scanner.nextLine();

        library.addBook(authorName, bookName, Title.valueOf(title.toUpperCase()), edition, dateOfPurchase);
    }

    private Author selectAuthorByName(Scanner scanner) {
        if (authorsList.isEmpty()) {
            System.out.println("No authors found in the system.");
            return null;
        } else {
            System.out.println("List of Authors in the Library:");
            authorsList.stream()
                    .sorted(Comparator.comparing(Author::getName))
                    .forEach(author -> System.out.println(" - " + author.getName()));

            System.out.print("Enter the name of the author to view their books: ");
            String authorName = scanner.nextLine();

            return authorsList.stream()
                    .filter(author -> author.getName().equalsIgnoreCase(authorName))
                    .findFirst()
                    .orElse(null);
        }
    }
}
