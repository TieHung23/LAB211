/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libary_obj;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author ACER
 */
public class Loans implements Serializable {

    private String LID;
    private Books books;
    private User user;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Loans(String LID, Books books, User user, LocalDate borrowDate, LocalDate returnDate) {
        this.LID = LID;
        this.books = books;
        this.user = user;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        books.setStatus(Status.unactive);
    }

    public String getLID() {
        return LID;
    }

    public Books getBooks() {
        return books;
    }

    public boolean setBooks(Books books) {
        if (books != null) {
            this.books = books;
            books.setStatus(Status.unactive);
            return true;
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public boolean setUser(User user) {
        if (user != null) {
            this.user = user;
            return true;
        }
        return false;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public boolean setBorrowDate(LocalDate borrowDate) {
        if (borrowDate != null) {
            this.borrowDate = borrowDate;
            return true;
        }
        return false;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public boolean setReturnDate(LocalDate returnDate) {
        if (returnDate != null) {
            this.returnDate = returnDate;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Loans{" + "LID=" + LID + ", books=" + books.getBookID() + ", user=" + user.getUserID() + ", borrowDate=" + borrowDate + ", returnDate=" + returnDate + '}';
    }

}
