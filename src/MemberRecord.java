import enums.Type;

import java.time.LocalDate;

public class MemberRecord {
    private String  memberId;
    private Type type;
    private LocalDate dateOfMemberShip;
    private int maxBookLimit;
    private int noBooksIssued;

    public MemberRecord(String memberId, Type type, LocalDate dateOfMemberShip ) {
        this.memberId = memberId;
        this.type = type;
        this.dateOfMemberShip = dateOfMemberShip;
        this.maxBookLimit = 5;
        this.noBooksIssued = 0;
    }
    public String getMember(){
        return memberId;
    }
    public LocalDate getDateOfMemberShip(){
        return dateOfMemberShip;
    }

    public int getNoBookIssued() {
        return noBooksIssued;
    }
    public void incBookIssued(){
        if (noBooksIssued < maxBookLimit) {
            noBooksIssued++;
        } else {
            System.out.println("Book limit reached.");
        }
    }
    public void decBookIssued() {
        if (noBooksIssued > 0) {
            noBooksIssued--;
        }
    }
}
