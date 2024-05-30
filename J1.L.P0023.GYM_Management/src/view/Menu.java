/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controler.ManagerClasses;
import controler.ManagerEquipment;
import controler.ManagerMember;
import controler.Validate;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class Menu {

    public void show() {
        Validate valueCheck = new Validate();
        ManagerMember mm = new ManagerMember();
        mm.readFile();
        ManagerEquipment me = new ManagerEquipment();
        me.readFile();
        ManagerClasses mc = new ManagerClasses();
        mc.readFile();
        int choice = 0;
        boolean checked = true;
        ArrayList<String> Options = new ArrayList<>();
        Options.add("                                    1.       Add new Member");
        Options.add("                                    2.       Sort and Display Member");
        Options.add("                                    3.       Search by ID Member and Update Info");
        Options.add("                                    4.       Search by ID Member and Delete Member");
        Options.add("                                    5.       Show member list");

        Options.add("                                    6.       Add new Equipment");
        Options.add("                                    7.       Sort and Display Equipment");
        Options.add("                                    8.       Search by ID Equip and Update Info");
        Options.add("                                    9.       Search by ID Equip and Delete by quantity");
        Options.add("                                    10.      Show equipment list");

        Options.add("                                    11.      Add new Classes");
        Options.add("                                    12.      Update and Manage Class");
        Options.add("                                    13.      Remove a Class");
        Options.add("                                    14.      Show classes list");

        Options.add("                                    15.      Data Management");
        Options.add("                                    16.      Exit");
        while (checked) {
            System.out.println("----------------------------------------------------Gym Management---------------------------------------------------------------");;
            for (String ops : Options) {
                System.out.println(ops);
            }
            System.out.println("------------------------------------------------Pham Tien Hung SE183180----------------------------------------------------------");
            choice = valueCheck.checkChoice("Please chose your choice: ", 1, Options.size(), "You must chose from 1 - 16");
            switch (choice) {
                case 1:
                    mm.add();
                    break;
                case 2:
                    mm.sort();
                    break;
                case 3:
                    mm.update();
                    break;
                case 4:
                    mm.delete();
                    break;
                case 5:
                    mm.show();
                    break;
                case 6:
                    me.add();
                    break;
                case 7:
                    me.sort();
                    break;
                case 8:
                    me.update();
                    break;
                case 9:
                    me.delete();
                    break;
                case 10:
                    me.show();
                    break;
                case 11:
                    mc.add();
                    break;
                case 12:
                    mc.update();
                    break;
                case 13:
                    mc.delete();
                    break;
                case 14:
                    mc.show();
                    break;
                case 15:
                    mm.saveFile();
                    me.saveFile();
                    mc.saveFile();
                    checked = false;
                    break;
                default:
                    System.out.println("See you again !");
                    checked = false;
                    break;
            }

        }
    }

}
