public class Librarian extends Person {
    private String password;

    public Librarian(String name, String password) {
        super(name);
        this.password = password;
    }

    @Override
    public String whoYouAre() {
        return "I`m Librarian";
    }
    public boolean verifyPassword(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }
    public  void  showBookOwner(Book book, String enteredPassword){
        String ownerName = book.getOwnerNameForLibrarian(this,enteredPassword);
        System.out.println("The book: " + book.getTitle() + " is currently with: " + ownerName);
    }

}
