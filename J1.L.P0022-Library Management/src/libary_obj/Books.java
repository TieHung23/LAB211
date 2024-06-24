/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libary_obj;

import java.io.Serializable;

/**
 *
 * @author ACER
 */
public class Books implements Serializable {

    private String bookID, bookTitle, author, publicationYear, publisher, ISBN;
    private Status status;

    public Books(String bookID, String bookTitle, String author, String publicationYear, String publisher, String ISBN) {
        this.bookID = bookID.toUpperCase();
        this.bookTitle = bookTitle;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.status = Status.active;
    }

    public String getBookID() {
        return bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public boolean setBookTitle(String bookTitle) {
        if (!bookTitle.isEmpty()) {
            this.bookTitle = bookTitle;
            return true;
        }
        return false;
    }

    public String getAuthor() {
        return author;
    }

    public boolean setAuthor(String author) {
        if (!author.isEmpty()) {
            this.author = author;
            return true;
        }
        return false;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public boolean setPublicationYear(String publicationYear) {
        if (!publicationYear.equals("-1")) {
            this.publicationYear = publicationYear;
            return true;
        }
        return false;
    }

    public String getPublisher() {
        return publisher;
    }

    public boolean setPublisher(String publisher) {
        if (!publisher.isEmpty()) {
            this.publisher = publisher;
            return true;
        }
        return false;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean setISBN(String ISBN) {
        if (!ISBN.isEmpty()) {
            this.ISBN = ISBN;
            return true;
        }
        return false;
    }

    public Status getStatus() {
        return status;
    }

    public boolean setStatus(Status status) {
        if (status != null) {
            this.status = status;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Books{" + "bookID=" + bookID + ", bookTitle=" + bookTitle + ", author=" + author + ", publicationYear=" + publicationYear + ", publisher=" + publisher + ", ISBN=" + ISBN + ", status=" + status + '}';
    }
}
