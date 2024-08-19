package com.example.myLibrary;

import com.example.myLibrary.enums.Type;

import java.time.LocalDate;
import java.util.Locale;

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

    @Override
    public void borrowBook(Book book) {
        if (noBooksIssued < maxBookLimit) {
            super.borrowBook(book);
            noBooksIssued++;
        } else {
            System.out.println("Cannot borrow more books. Maximum limit reached.");
        }
    }

    @Override
    public void returnBook(Book book) {
        super.returnBook(book);
        noBooksIssued--;
    }
}
