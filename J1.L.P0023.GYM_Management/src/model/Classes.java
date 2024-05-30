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
public class Classes {

    public static ArrayList<Classes> classesList = new ArrayList<>();
    private String classID, name, schedule, capacity;
    private ArrayList<Member> classesMember;
    private ArrayList<Equipment> classesEquipments;

    public Classes() {
        classesMember = new ArrayList<Member>();
        classesEquipments = new ArrayList<Equipment>();
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public ArrayList<Member> getClassesMember() {
        return classesMember;
    }

    public void setClassesMember(ArrayList<Member> classesMember) {
        this.classesMember = classesMember;
    }

    public ArrayList<Equipment> getClassesEquipments() {
        return classesEquipments;
    }

    public void setClassesEquipments(ArrayList<Equipment> classesEquipments) {
        this.classesEquipments = classesEquipments;
    }

    public String getMemberList() {
        String listMember = "";
        for (Member mb : this.getClassesMember()) {
            if (this.getClassesMember().get(this.getClassesMember().size() - 1).equals(mb)) {
                listMember += mb.getID();
            } else {
                listMember += mb.getID() + ", ";
            }
        }
        return listMember;
    }

    public String getEquipList() {
        String listEquip = "";
        for (Equipment eq : this.getClassesEquipments()) {
            if (this.getClassesEquipments().get(this.getClassesEquipments().size() - 1).equals(eq)) {
                listEquip += eq.getEquipID();
            } else {
                listEquip += eq.getEquipID() + ", ";
            }
        }
        return listEquip;
    }

    @Override
    public String toString() {
        String line = String.format("%s _ %s _%s _ %s _ %s _ %s", this.getClassID(), this.getName(), this.getSchedule(), this.getCapacity(), this.getMemberList(), this.getEquipList());
        return line;
    }

    public int getNumberMember() {
        String[] number = this.getCapacity().split(" ");
        return Integer.parseInt(number[0]);
    }
}
