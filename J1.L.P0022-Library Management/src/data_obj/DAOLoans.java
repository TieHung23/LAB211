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
import java.time.LocalDate;
import libary_obj.Books;
import libary_obj.Loans;
import libary_obj.User;
import map_obj.IMap;
import map_obj.Map;

/**
 *
 * @author ACER
 */
public class DAOLoans implements IDAOLoans {

    private IMap<String, Loans> loansList = new Map<>();
    private final String LOAN_FILE = "loans.dat";

    public DAOLoans() {
        loansList = new Map<>();
    }

    @Override
    public IMap<String, Loans> mapLoans() {
        return loansList;
    }

    @Override
    public void add(Loans loan) {
        loansList.add(loan.getLID(), loan);
    }

    @Override
    public Loans[] toArrObj() {
        Object[] objArr = loansList.arrOfNode();
        Loans arr[] = new Loans[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            arr[i] = (Loans) objArr[i];
        }
        return arr;
    }

    @Override
    public Loans checkExistID(String ID) {
        return loansList.search(ID);
    }

    @Override
    public void update(Loans loan, Books bookID, User userID, LocalDate borrowDate, LocalDate returnDate) {
        if (loan.setBooks(bookID)) {
            System.out.println("Change Book borrow to: " + bookID.getBookID());
        }
        if (loan.setUser(userID)) {
            System.out.println("Change User borrow to: " + userID.getUserID());
        }
        if (loan.setBorrowDate(borrowDate)) {
            System.out.println("Change borrow date to: " + borrowDate);
        }
        if (loan.setReturnDate(returnDate)) {
            System.out.println("Change return date to: " + returnDate);
        }
        System.out.println("Change success to: " + loan.toString());
    }

    @Override
    public boolean readFile() {
        File file = new File(LOAN_FILE);
        try {
            FileInputStream readData = new FileInputStream(file);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            boolean more = true;
            while (more) {
                Loans loans = (Loans) readStream.readObject();
                if (loans != null) {
                    add(loans);
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
    public boolean wirtefile() {
        File file = new File(LOAN_FILE);
        try {
            FileOutputStream writeData = new FileOutputStream(file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            Loans[] arr = toArrObj();
            for (int i = 0; i < arr.length; i++) {
                writeStream.writeObject(arr[i]);
            }
            writeStream.flush();
            writeStream.close();
            writeData.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
