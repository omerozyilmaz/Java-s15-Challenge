package com.example.myLibrary.interfaces;

import com.example.myLibrary.Author;
import com.example.myLibrary.Librarian;
import com.example.myLibrary.Library;
import com.example.myLibrary.MemberRecord;

import java.util.Scanner;

public interface LibraryActions {
    void handleLibrarianActions(Library library, Scanner scanner, Librarian librarian);
    void handleMemberActions(Library library, Scanner scanner, Librarian librarian, MemberRecord member);
    void addBookToLibrary(Library library, Scanner scanner, Librarian librarian);
//    void issueBookToMember(Library library, Scanner scanner, Librarian librarian);
//    void viewMemberBooks(Library library, Scanner scanner, Librarian librarian);
}