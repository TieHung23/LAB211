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
import libary_obj.Status;
import libary_obj.User;
import map_obj.IMap;
import map_obj.Map;

/**
 *
 * @author ACER
 */
public class DAOUsers implements IDAOUsers {

    private IMap<String, User> userList = new Map<>();
    private final String USER_FILE = "user.dat";

    public DAOUsers() {
        userList = new Map<>();
    }

    @Override
    public IMap<String, User> mapUser() {
        return userList;
    }

    @Override
    public void add(User user) {
        userList.add(user.getUserID(), user);
    }

    @Override
    public User[] toArrObj() {
        Object[] objArr = userList.arrOfNode();
        User arr[] = new User[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            arr[i] = (User) objArr[i];
        }
        return arr;
    }

    @Override
    public User checkExistID(String ID) {
        return userList.search(ID);
    }

    @Override
    public void delete(User user) {
        user.setStatus(Status.unactive);
        System.out.println("Delete success");
    }

    @Override
    public void update(User user, String name, LocalDate dateOfBirth, String phoneNum, String email, Status status) {
        if (user.setName(name)) {
            System.out.println("Change Book title to: " + name);
        }
        if (user.setDateOfBirth(dateOfBirth)) {
            System.out.println("Change Book author to: " + dateOfBirth);
        }
        if (user.setPhoneNum(phoneNum)) {
            System.out.println("Change Book publication year to: " + phoneNum);
        }
        if (user.setEmail(email)) {
            System.out.println("Change Book publisher to: " + email);
        }
        if (user.setStatus(status)) {
            System.out.println("Change Book title to: " + status);
        }
        System.out.println("Change success to: " + user.toString());
    }

    @Override
    public boolean readFile() {
        File file = new File(USER_FILE);
        try {
            FileInputStream readData = new FileInputStream(file);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            boolean more = true;
            while (more) {
                User user = (User) readStream.readObject();
                if (user != null) {
                    add(user);
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
        File file = new File(USER_FILE);
        try {
            FileOutputStream writeData = new FileOutputStream(file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            User[] arr = toArrObj();
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

    @Override
    public User genUserID() {
        do {
            User user = new User();
            if (checkExistID(user.getUserID()) == null) {
                return user;
            }
        } while (true);
    }
}
