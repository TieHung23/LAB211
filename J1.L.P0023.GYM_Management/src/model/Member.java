/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class Member {

    public static ArrayList<Member> memberList = new ArrayList<>();
    private String ID, name, address;
    private String contactInfor;
    private String memberType;

    public Member() {
        this.ID = "empty";
        this.name = "empty";
        this.address = "empty";
        this.contactInfor = "empty";
        this.memberType = "empty";
    }

    public Member(String ID, String name, String address, String contactInfor, String memberType) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.contactInfor = contactInfor;
        this.memberType = memberType;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfor() {
        return contactInfor;
    }

    public void setContactInfor(String contactInfor) {
        this.contactInfor = contactInfor;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    @Override
    public String toString() {
        return String.format("%s _ %s _ %s _ %s _ %s", this.getID(), this.getName(), this.getAddress(), this.getContactInfor(), this.getMemberType());
    }
}
