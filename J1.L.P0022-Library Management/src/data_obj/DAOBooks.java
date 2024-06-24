/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_obj;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import libary_obj.Books;
import libary_obj.Status;
import map_obj.IMap;
import map_obj.Map;
import program.color;

/**
 *
 * @author ACER
 */
public class DAOBooks implements IDAOBooks {

    private IMap<String, Books> booksList = new Map<>();
    private final String BOOK_FILE = "books.dat";

    public DAOBooks() {
        booksList = new Map<>();
    }

    @Override
    public IMap<String, Books> map() {
        return booksList;
    }

    @Override
    public void add(Books book) {
        booksList.add(book.getBookID(), book);
    }

    @Override
    public Books checkExistID(String ID) {
        return booksList.search(ID);
    }

    @Override
    public Books checkExistISBN(String ISBN) {
        Books arr[] = toArrObj();
        int note = 0;
        for (int i = 0; i < arr.length; i++) {
            if (ISBN.equals(arr[i].getISBN())) {
                return booksList.searchByIndex(note);
            }
        }
        return null;
    }

    @Override
    public void delete(Books book) {
        book.setStatus(Status.unactive);
        System.out.println("Delete success");
    }

    @Override
    public void update(Books book, String title, String author, String publicationYear, String publisher, String ISBN, Status status) {
        if (book.setBookTitle(title)) {
            System.out.println("Change Book title to: " + title);
        }
        if (book.setAuthor(author)) {
            System.out.println("Change Book author to: " + author);
        }
        if (book.setPublicationYear(publicationYear)) {
            System.out.println("Change Book publication year to: " + publicationYear);
        }
        if (book.setPublisher(publisher)) {
            System.out.println("Change Book publisher to: " + publisher);
        }
        if (book.setISBN(ISBN)) {
            System.out.println("Change Book title to: " + ISBN);
        }
        if (book.setStatus(status)) {
            System.out.println("Change Book title to: " + status);
        }
        System.out.println("Change success to: ");
        System.out.println(book.toString());
    }

    @Override
    public boolean readFile() {
        File file = new File(BOOK_FILE);
        try {
            FileInputStream readData = new FileInputStream(file);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            boolean more = true;
            while (more) {
                Books books = (Books) readStream.readObject();
                if (books != null) {
                    this.add(books);
                } else {
                    more = false;
                }
            }
            readStream.close();
            readData.close();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public Books[] toArrObj() {
        Object[] objArr = booksList.arrOfNode();
        Books arr[] = new Books[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            arr[i] = (Books) objArr[i];
        }
        return arr;
    }

    @Override
    public boolean wirtefile() {
        File file = new File(BOOK_FILE);
        try {
            FileOutputStream writeData = new FileOutputStream(file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            Books[] arr = toArrObj();
            for (int i = 0; i < arr.length; i++) {
                writeStream.writeObject(arr[i]);
            }
            writeStream.flush();
            writeStream.close();
            writeData.close();
        } catch (IOException e) {
            System.out.println("Save unsuccess");
            return false;
        }
        System.out.println("Save success");
        return true;
    }
}
