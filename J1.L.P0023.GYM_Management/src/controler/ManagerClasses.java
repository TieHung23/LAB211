/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import model.Classes;
import model.Equipment;
import model.Member;
import view.color;

/**
 *
 * @author ACER
 */
public class ManagerClasses implements IManager, IFileReaderWriter {

    Validate valueCheck = new Validate();
    Classes classes = new Classes();
    Member member = new Member();
    Equipment equipment = new Equipment();

    @Override
    public int search() {
        do {
            int cnt = 0;
            String ID = valueCheck.checkRegex("Input ID", ".+", "Do not input empty");
            System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-10s|%-30s|%-25s|%-40s|%-40S|%n" + color.RESET, "ID classes", "Name", "Schedule", "Capacity", "Members", "Equipments");
            for (Classes cl : Classes.classesList) {
                if (cl.getClassID().toUpperCase().contains(ID.toUpperCase())) {
                    String line = String.format("|%-15s|%-10s|%-30s|%-25s|%-40s|%-40S|%n", cl.getClassID(), cl.getName(), cl.getSchedule(), cl.getCapacity(), cl.getMemberList(), cl.getEquipList());
                    System.out.printf(line);
                    cnt++;
                    classes = cl;
                }
            }
            if (cnt == 0) {
                System.out.println("ID do not existed");
                return 0;
            } else {
                return cnt;
            }
        } while (true);
    }

    @Override
    public void update() {
        do {
            int countObj = search();
            switch (countObj) {
                case 0:
                    break;
                case 1:
                    if (valueCheck.checkYesorNo("Do you want to update\n1. Yes\n2. No", "Just 1 or 2")) {
                        String nameCla = valueCheck.checkRegex("Input name", ".+", "Do not input empty");
                        classes.setName(nameCla);

                        classes.setSchedule(addSchedule());

                        int capacity = valueCheck.checkChoice("Enter the capacity of classes", 1, 30, "Capacity cannot bigger than 30 and lower than 1");
                        classes.setCapacity(capacity + " Members");

                        //Member options
                        int choice = valueCheck.checkChoice("Enter your option with member\n1. Add\n2. Delete\n3. Do nothing", 1, 3, "Just 1 - 3");

                        switch (choice) {
                            case 1:
                                do {
                                    Member memberUse = new Member();
                                    String IDmember = valueCheck.checkRegex("Enter member ID: ", "GY\\d{5}", "Your ID don't match with type GYxxxxx");
                                    memberUse = (Member) valueCheck.checkIDExist(IDmember.toUpperCase(), memberUse);
                                    System.out.println(memberUse.toString());
                                    if (memberUse != null && classes.getClassesMember().contains(memberUse) == false && classes.getClassesMember().size() < classes.getNumberMember()) {
                                        classes.getClassesMember().add(memberUse);
                                        System.out.println("Add success");
                                    } else if (memberUse == null) {
                                        System.out.println("ID member do not existed, please add first");
                                    } else if (classes.getClassesMember().contains(memberUse) == true) {
                                        System.out.println("Member already in class");
                                    } else {
                                        System.out.println("Classes full");
                                    }
                                    if (!valueCheck.checkYesorNo("Do you want to add more\n1. Yes\n2. No", "Just 1 or 2")) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 2:
                                do {
                                    Member memberUse = new Member();
                                    String IDmember = valueCheck.checkRegex("Enter member ID: ", "GY\\d{5}", "Your ID don't match with type GYxxxxx");
                                    memberUse = (Member) valueCheck.checkIDExist(IDmember.toUpperCase(), memberUse);
                                    if (memberUse != null && classes.getClassesMember().contains(memberUse) == true) {
                                        classes.getClassesMember().remove(memberUse);
                                        System.out.println("Remove success");
                                    } else if (memberUse == null) {
                                        System.out.println("ID member do not existed");
                                    } else {
                                        System.out.println("Member do not already in class");
                                    }
                                    if (!valueCheck.checkYesorNo("Do you want to delete more\n1. Yes\n2. No", "Just 1 or 2")) {
                                        break;
                                    }
                                } while (true);
                                break;
                            default:
                                break;
                        }
                        //Equipment options
                        int choice2 = valueCheck.checkChoice("Enter your option with equipment\n1. Add\n2. Delete\n3. Do nothing", 1, 3, "Just 1 - 3");

                        switch (choice2) {
                            case 1:
                                do {
                                    Equipment equipmentUse = new Equipment();
                                    String IDequip = valueCheck.checkRegex("Enter equip ID: ", "EQU\\d{5}", "Your ID don't match with type EQUxxxxx");
                                    equipmentUse = (Equipment) valueCheck.checkIDExist(IDequip.toUpperCase(), equipmentUse);
                                    if (equipmentUse != null && classes.getClassesEquipments().contains(equipmentUse) == false) {
                                        classes.getClassesEquipments().add(equipmentUse);
                                        System.out.println("Add success");
                                    } else if (equipmentUse == null) {
                                        System.out.println("ID equip do not existed, please add first");
                                    } else {
                                        System.out.println("Equipment already in class");
                                    }
                                    if (!valueCheck.checkYesorNo("Do you want to add more\n1. Yes\n2. No", "Just 1 or 2")) {
                                        break;
                                    }
                                } while (true);
                                break;
                            case 2:
                                do {
                                    Equipment equipmentUse = new Equipment();
                                    String IDequip = valueCheck.checkRegex("Enter equip ID to delete: ", "EQU\\d{5}", "Your ID don't match with type EQUxxxxx");
                                    equipmentUse = (Equipment) valueCheck.checkIDExist(IDequip.toUpperCase(), equipmentUse);
                                    if (equipmentUse != null && classes.getClassesEquipments().contains(equipmentUse) == true) {
                                        classes.getClassesEquipments().remove(equipmentUse);
                                        System.out.println("Remove success");
                                    } else if (equipmentUse == null) {
                                        System.out.println("ID equip do not existed, please add first");
                                    } else {
                                        System.out.println("Equipment do not already in class");
                                    }
                                    if (!valueCheck.checkYesorNo("Do you want to delete more\n1. Yes\n2. No", "Just 1 or 2")) {
                                        break;
                                    }
                                } while (true);
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("Too much member can not update");
                    break;
            }
            if (!valueCheck.checkYesorNo("Do you want to update again\n1. Yes\n2. No", "Just 1 or 2")) {
                break;
            }
        } while (true);
    }

    @Override

    public void delete() {
        do {
            int countObj = search();
            switch (countObj) {
                case 0:
                    break;
                case 1:
                    if (valueCheck.checkYesorNo("Do you want to delete\n1. Yes\n2. No", "Just 1 or 2")) {
                        Classes.classesList.remove(classes);
                        System.out.println("Delete success");
                        break;
                    }
                default:
                    System.out.println("Too much member can not update");
                    break;
            }
            if (!valueCheck.checkYesorNo("Do you want to delete again\n1. Yes\n2. No", "Just 1 or 2")) {
                break;
            }
        } while (true);
    }

    @Override
    public void readFile() {
        String filePath = "src/file/Classes.txt";
        File f = new File(filePath);
        if (!f.exists()) {
            System.out.println("Cannot load classes file");
            return;
        }
        try {
            List<String> allLines = Files.readAllLines(f.toPath());
            allLines.forEach((line) -> {
                addElementFromFile(line, classes, Classes.classesList);
            });
            System.out.println("Load classes file success...");
        } catch (IOException e) {
            System.out.println("Cannot load classes file");
        }
    }

    @Override
    public void addElementFromFile(String line, Object obj, ArrayList array) {
        Classes classesFile = new Classes();
        String[] lineObj = line.split("\\s*_\\s*");
        classesFile.setClassID(lineObj[0]);
        classesFile.setName(lineObj[1]);
        classesFile.setSchedule(lineObj[2]);
        classesFile.setCapacity(lineObj[3]);
        String[] memberArr = lineObj[4].split("\\s*,\\s*");
        for (int i = 0; i < memberArr.length; i++) {
            classesFile.getClassesMember().add((Member) valueCheck.checkIDExist(memberArr[i], member));
        }
        String[] equipArr = lineObj[5].split("\\s*,\\s*");
        for (int i = 0; i < equipArr.length; i++) {
            classesFile.getClassesEquipments().add((Equipment) valueCheck.checkIDExist(equipArr[i], equipment));
        }
        array.add(classesFile);
    }

    @Override
    public void add() {
        do {
            Classes classes1 = new Classes();
            do {
                String IDclasses = valueCheck.checkRegex("Enter classes ID: ",
                        "CLA\\d{5}",
                        "Your ID don't match with type CLAxxxxx");
                if (valueCheck.checkIDExist(IDclasses, classes1) == null) {
                    classes1.setClassID(IDclasses.toUpperCase());
                    break;
                } else {
                    System.out.println("ID Existed");
                }
            } while (true);

            String nameCla = valueCheck.checkRegex("Input name", ".+", "Do not input empty");
            classes1.setName(nameCla);

            classes1.setSchedule(addSchedule());

            int capacity = valueCheck.checkChoice("Enter the capacity of classes", 1, 30, "Capacity cannot bigger than 30 and lower than 1");
            classes1.setCapacity(capacity + " Members");

            do {
                Member member1 = new Member();
                String IDmember = valueCheck.checkRegex("Enter member ID to add: ",
                        "GY\\d{5}",
                        "Your ID don't match with type GYxxxxx");
                member1 = (Member) valueCheck.checkIDExist(IDmember.toUpperCase(), member);
                if (member1 != null && classes1.getClassesMember().contains(member1) == false && classes1.getClassesMember().size() < classes1.getNumberMember()) {
                    classes1.getClassesMember().add(member1);
                    System.out.println("Added success");
                } else if (member1 == null) {
                    System.out.println("ID member do not existed, please add first");
                } else if (classes1.getClassesMember().contains(member1) == true) {
                    System.out.println("Member already in class");
                } else {
                    System.out.println("Classes full");
                }
                if (!valueCheck.checkYesorNo("Do you want to add again\n1. Yes\n2. No", "Just 1 or 2")) {
                    break;
                }
            } while (true);

            do {
                Equipment equipment1 = new Equipment();
                String IDequip = valueCheck.checkRegex("Enter equip ID to add: ",
                        "EQU\\d{5}",
                        "Your ID don't match with type EQUxxxxx");
                equipment1 = (Equipment) valueCheck.checkIDExist(IDequip.toUpperCase(), equipment1);
                if (equipment1 != null && classes1.getClassesEquipments().contains(equipment1) == false) {
                    classes1.getClassesEquipments().add(equipment1);
                    System.out.println("Added success");
                } else if (equipment1 == null) {
                    System.out.println("ID equip do not existed, please add first");
                } else {
                    System.out.println("Equipment already in class");
                }
                if (!valueCheck.checkYesorNo("Do you want to add again\n1. Yes\n2. No", "Just 1 or 2")) {
                    break;
                }
            } while (true);
            Classes.classesList.add(classes1);
            System.out.println("Added successful !!!");
            if (!valueCheck.checkYesorNo("Do you want add more classes\n1. Yes\n2. No", "Just 1 or 2")) {
                break;
            }
        } while (true);
    }

    @Override
    public void show() {
        System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-10s|%-30s|%-25s|%-40s|%-40S|%n" + color.RESET, "ID classes", "Name", "Schedule", "Capacity", "Members", "Equipments");
        for (Classes cl : Classes.classesList) {
            String line = String.format("|%-15s|%-10s|%-30s|%-25s|%-40s|%-40S|%n", cl.getClassID(), cl.getName(), cl.getSchedule(), cl.getCapacity(), cl.getMemberList(), cl.getEquipList());
            System.out.printf(line);
        }
    }

    public String addSchedule() {
        String date;
        do {
            int dayOfWeek = valueCheck.checkChoice("Chose days of week for classes\n1. Monday\n2. Tuesday\n3. Wednesday\n4. Thursday\n5. Friday\n6. Saturday\n7. Sunday", 1, 7, "You must chose from 1 - 7");
            switch (dayOfWeek) {
                case 1:
                    date = "Mon";
                    break;
                case 2:
                    date = "Tue";
                    break;
                case 3:
                    date = "Wed";
                    break;
                case 4:
                    date = "Thu";
                    break;
                case 5:
                    date = "Fri";
                    break;
                case 6:
                    date = "Sat";
                    break;
                default:
                    date = "Sun";
                    break;
            }
            String hour;
            int timeStart = valueCheck.checkChoice("Enter time start of class: ", 1, 24, "Time start must from 1 - 24");
            int timeEnd = valueCheck.checkChoice("Enter time end of class: ", timeStart+1, 24, "Time end must bigger than time start and lower than 24");
            hour = timeStart + ":00 - " + timeEnd + ":00";
            date += " " + hour;
            int cnt = 0;
            for (Classes classes1 : Classes.classesList) {
                if (classes1.getSchedule().equals(date)) {
                    cnt++;
                }
            }
            if (cnt > 0) {
                System.out.println("Date or time is the same with orther classes\nPlease chose again");
            } else {
                break;
            }
        } while (true);
        return date;
    }

    @Override
    public void saveFile() {

        String filePath = "src/file/Classes.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Cannot load classes file");
            }
        } 
        try {
            PrintWriter pw = new PrintWriter(filePath);
            for (Classes cl : Classes.classesList) {
                pw.println(cl.toString());
            }
            System.out.println("Save classes success");
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Save unsuccess");
        }
    }

    @Override
    public void sort() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
