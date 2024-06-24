/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_obj;

import libary_obj.Books;
import libary_obj.Status;
import map_obj.IMap;

/**
 *
 * @author ACER
 */
public interface IDAOBooks {

    IMap<String, Books> map();

    void add(Books book);

    Books[] toArrObj();

    Books checkExistID(String ID);

    Books checkExistISBN(String ISBN);

    void delete(Books book);

    void update(Books book, String title, String author, String publicationYear, String publisher, String ISBN, Status status);

    boolean readFile();

    boolean wirtefile();
}
