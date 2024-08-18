import enums.Type;
import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

public class MemberRecord extends Reader {
    private String memberId;
    private Type type;
    private LocalDate dateOfMembership;
    private int maxBookLimit = 5;
    private int noBooksIssued = 0;

    public MemberRecord(String name, String memberId, Type type) {
        super(name);
        this.memberId = memberId.toLowerCase(Locale.ROOT);
        this.type = type;
        this.dateOfMembership = LocalDate.now();
    }

    public String getMemberId() {
        return memberId;
    }

    public Type getType() {
        return type;
    }

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public void incrementBooksIssued() {
        if (noBooksIssued < maxBookLimit) {
            noBooksIssued++;
        } else {
            System.out.println("Cannot issue more books. Limit reached.");
        }
    }

    public void decrementBooksIssued() {
        if (noBooksIssued > 0) {
            noBooksIssued--;
        }
    }

    @Override
    public String whoYouAre() {
        return "I am a Member.";
    }
}