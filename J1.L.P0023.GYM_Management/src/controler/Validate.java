/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.Scanner;
import model.Classes;
import model.Equipment;
import model.Member;

/**
 *
 * @author ACER
 */
public class Validate implements IValidate {

    static Scanner sc = new Scanner(System.in);

    @Override
    public Object checkIDExist(String ID, Object obj) {
        if (obj instanceof model.Member) {
            for (Member mb : Member.memberList) {
                if (mb.getID().toUpperCase().equals(ID.toUpperCase())) {
                    return mb;
                }
            }
        }
        if (obj instanceof model.Equipment) {
            for (Equipment eq : Equipment.equipList) {
                if (eq.getEquipID().toUpperCase().equals(ID.toUpperCase())) {
                    return eq;
                }
            }
        }
        if (obj instanceof model.Classes) {
            for (Classes cl : Classes.classesList) {
                if (cl.getClassID().toUpperCase().equals(ID.toUpperCase())) {
                    return cl;
                }
            }
        }
        return null;
    }

    @Override
    public int checkChoice(String msg, int min, int max, String error) {
        int ops = 0;
        while (true) {
            String choice = checkRegex(msg, "\\d+", "Input digit only");
            ops = Integer.parseInt(choice);
            if (ops >= min && ops <= max) {
                return ops;
            } else {
                System.out.println(error);
                //sc.nextLine();
            }
        }
    }

    @Override
    public boolean checkYesorNo(String msg, String error) {
        String ops = null;
        boolean checked = false;
        while (checked == false) {
            try {
                ops = checkRegex(msg, "[12]", "Just 1 or 2");
                if ("1".equals(ops)) {
                    return true;
                }
                if ("2".equals(ops)) {
                    return false;
                }
                System.out.println(error);
            } catch (Exception e) {
                System.out.println(error);
                sc.next();
            }
        }
        return false;
    }

    @Override
    public String checkRegex(String msg, String regex, String errorRegex) {
        String strInput = null;
        do {
            System.out.println(msg);
            strInput = sc.nextLine().trim();
            if (strInput.toUpperCase().matches(regex)) {
                return strInput;
            } else {
                System.out.println(errorRegex);
            }
        } while (true);
    }
}
