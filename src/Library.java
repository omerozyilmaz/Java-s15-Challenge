import java.util.*;

public class Library {
    private List<MemberRecord> members;
    private Map<String, List<Book>> bookInventory;

    public Library() {
        members = new ArrayList<>();
        bookInventory = new HashMap<>();
    }

    public void addBook(Book book) {
        bookInventory.computeIfAbsent(book.getName(), k -> new ArrayList<>()).add(book);
    }

    public List<Book> getBooksByName(String name) {
        return bookInventory.getOrDefault(name, new ArrayList<>());
    }

    public void lendBook(String bookName, MemberRecord member) {
        List<Book> books = getBooksByName(bookName);
        if (books.isEmpty()) {
            System.out.println("Book not available.");
            return;
        }

        if (member.getNoBooksIssued() >= member.getMaxBookLimit()) {
            System.out.println("Book limit reached. Return a book to issue a new one.");
            return;
        }

        Book bookToLend = books.remove(0);
        member.incrementBooksIssued();
        bookToLend.changeOwner(member);
        member.borrowBook(bookToLend);
        System.out.println("Book issued: " + bookToLend.getName() + " to " + member.getName());
    }

    public void takeBackBook(Book book, MemberRecord member) {
        book.changeOwner(null);
        addBook(book);
        member.returnBook(book);
        member.decrementBooksIssued();
    }

    public void addMember(MemberRecord member) {
        members.add(member);
    }

    public MemberRecord findMemberById(String memberId) {
        for (MemberRecord member : members) {
            if (member.getMemberId().equals(memberId.toLowerCase(Locale.ROOT))) {
                return member;
            }
        }
        return null;
    }

    public void displayBooksForLibrarian() {
        for (List<Book> books : bookInventory.values()) {
            for (Book book : books) {
                book.display();
            }
        }
    }

    public void displayBooksForMember() {
        for (List<Book> books : bookInventory.values()) {
            for (Book book : books) {
                System.out.println("Book Name: " + book.getName() + ", Title: " + book.getTitle());
            }
        }
    }
}