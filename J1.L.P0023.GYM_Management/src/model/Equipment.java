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
public class Equipment {

    public static ArrayList<Equipment> equipList = new ArrayList<>();
    private String equipID, name, type;
    private int quantity;
    private String condition;

    public Equipment() {
        this.equipID = null;
        this.name = null;
        this.type = null;
        this.quantity = 0;
        this.condition = null;
    }

    public Equipment(String equipID, String name, String type, int quantity, String condition) {
        this.equipID = equipID;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.condition = condition;
    }

    public String getEquipID() {
        return equipID;
    }

    public void setEquipID(String equipID) {
        this.equipID = equipID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return String.format("%s _ %s _ %s _ %s _ %s", this.getEquipID(), this.getName(), this.getType(), this.getQuantity(), this.getCondition());
    }

}
