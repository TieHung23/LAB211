/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_obj;

import java.time.LocalDate;
import libary_obj.Status;
import libary_obj.User;
import map_obj.IMap;

/**
 *
 * @author ACER
 */
public interface IDAOUsers {

    IMap<String, User> mapUser();

    void add(User user);

    User[] toArrObj();

    User checkExistID(String ID);

    void delete(User user);

    void update(User user, String name, LocalDate dateOfBirth, String phoneNum, String email, Status status);

    boolean readFile();

    boolean wirtefile();
    
    User genUserID();
}
