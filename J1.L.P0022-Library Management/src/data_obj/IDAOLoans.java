/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_obj;

import java.time.LocalDate;
import libary_obj.Books;
import libary_obj.Loans;
import libary_obj.Status;
import libary_obj.User;
import map_obj.IMap;

/**
 *
 * @author ACER
 */
public interface IDAOLoans {

    IMap<String, Loans> mapLoans();

    void add(Loans loan);

    Loans[] toArrObj();

    Loans checkExistID(String ID);

    void update(Loans loan, Books bookID, User userID, LocalDate borrowDate, LocalDate returnDate);

    boolean readFile();

    boolean wirtefile();
}
