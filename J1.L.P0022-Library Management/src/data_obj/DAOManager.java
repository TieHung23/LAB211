/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_obj;

import utils.IValidation;
import utils.Validation;

/**
 *
 * @author ACER
 */
public class DAOManager implements IDAOManager {

    @Override
    public IDAOBooks booksManager() {
        return new DAOBooks();
    }

    @Override
    public IValidation validation() {
        return new Validation();
    }

    @Override
    public IDAOUsers usersManager() {
        return new DAOUsers();
    }

    @Override
    public IDAOLoans loansManager() {
        return new DAOLoans();
    }

}
