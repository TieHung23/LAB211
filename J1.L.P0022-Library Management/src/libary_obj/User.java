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
public class User implements Serializable {

    private static int ID = 0;
    private String userID, name;
    private LocalDate dateOfBirth;
    private String phoneNum, email;
    private Status status;

    public User() {
        ID++;
        this.userID = genID(ID);
    }

    public User(String name, LocalDate dateOfBirth, String phoneNum, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNum = phoneNum;
        this.email = email;
        this.status = Status.active;
    }

    private String genID(int ID) {
        String UID = null;
        if (0 < ID && ID < 10) {
            UID = "U0000" + String.format("%s", ID);
        }
        if (10 < ID && ID < 100) {
            UID = "U000" + String.format("%s", ID);
        }
        if (100 < ID && ID < 1000) {
            UID = "U00" + String.format("%s", ID);
        }
        return UID;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
            return true;
        }
        return false;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean setDateOfBirth(LocalDate dateOfBirth) {
        if (dateOfBirth != null) {
            this.dateOfBirth = dateOfBirth;
            return true;
        }
        return false;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public boolean setPhoneNum(String phoneNum) {
        if (!phoneNum.isEmpty()) {
            this.phoneNum = phoneNum;
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if (!email.isEmpty()) {
            this.email = email;
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
        return "User{" + "userID=" + userID + ", name=" + name + ", dateOfBirth=" + dateOfBirth + ", phoneNum=" + phoneNum + ", email=" + email + ", status=" + status + '}';
    }

}

