import java.util.ArrayList;
import java.util.List;

public class Reader extends Person {
    private List<Book>booksReaden;

    public Reader(String name) {
        super(name);
        this.booksReaden = new ArrayList<>();
    }

    @Override
    public String whoYouAre() {
        return "";
    }
    public void showBook() {
        this.booksReaden.stream().forEach(System.out::println);
    }

//    public String purchaseBook(){
//
//    }


}
