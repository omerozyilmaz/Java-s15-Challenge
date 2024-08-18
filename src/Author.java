import java.util.*;

public class Author extends Person {
    private static Map<String, List<Book>> booksByAuthor = new HashMap<>();

    public Author(String name) {
        super(name);
    }

    private void addBookToAuthor(Book book) {
        booksByAuthor.computeIfAbsent(this.getName(), k -> new ArrayList<>()).add(book);
    } //Dokumasyondaki newBook metodu

    public void displayBooksByAuthor() {
        Optional.ofNullable(booksByAuthor.get(this.getName()))
                .filter(books -> !books.isEmpty())
                .ifPresentOrElse(
                        books -> {
                            System.out.println("Books by " + this.getName() + ":");
                            books.stream().forEach(Book::display);
                        },
                        () -> System.out.println("No books found for author: " + this.getName())
                );
    }

    @Override
    public String whoYouAre() {
        List<Book> books = booksByAuthor.get(this.getName());
        return "I’m an Author. I have written " + (books != null ? books.size() : 0) + " books.";
    }
}