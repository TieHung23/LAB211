/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

/**
 *
 * @author ACER
 */
public interface IService {

    //Books
    void addBooks();

    void updateBook();

    void deleteBook();

    void showAllBook(int num);

    //Users
    void addUsers();

    void updateUsers();

    void deleteUses();

    void showAllUsers(int num);

    //Loans
    void addLoans();

    void updateLoans();

    void showAllLoans();
}
