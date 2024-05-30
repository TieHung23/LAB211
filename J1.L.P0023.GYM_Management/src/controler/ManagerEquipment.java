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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Classes;
import model.Equipment;
import model.Member;
import view.color;

/**
 *
 * @author ACER
 */
public class ManagerEquipment implements IManager, IFileReaderWriter, Comparator<Equipment> {

    Validate valueCheck = new Validate();
    Equipment equip = new Equipment();

    @Override
    public void show() {
        System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-30s|%-30s|%-29s|%-20s|\n" + color.RESET, "ID Equipment", "Name", "Type", "Quantity", "Condition");
        for (Equipment equipment : Equipment.equipList) {
            System.out.printf("|%-15s|%-30s|%-30s|%-29s|%-20s|%n", equipment.getEquipID(), equipment.getName(), equipment.getType(), equipment.getQuantity(), equipment.getCondition());
        }

    }

    @Override
    public int search() {
        do {
            int cnt = 0;
            String ID = valueCheck.checkRegex("Input ID", ".+", "Do not input empty");
            System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-30s|%-30s|%-29s|%-20s|\n" + color.RESET, "ID Equipment", "Name", "Type", "Quantity", "Condition");
            for (Equipment equipment : Equipment.equipList) {
                if (equipment.getEquipID().toUpperCase().contains(ID.toUpperCase())) {
                    System.out.printf("|%-15s|%-30s|%-30s|%-29s|%-20s|%n", equipment.getEquipID(), equipment.getName(), equipment.getType(), equipment.getQuantity(), equipment.getCondition());
                    cnt++;
                    equip = equipment;
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

                        String equipName = valueCheck.checkRegex("Input name", ".+", "Do not input empty");
                        equip.setName(equipName);

                        String typeEquip = valueCheck.checkRegex("Input type", ".+", "Do not input empty");
                        equip.setType(typeEquip);

                        int quantity = valueCheck.checkChoice("Enter equip quantity", 0, 1000, "Quantity is > 0 and < 1000");
                        equip.setQuantity(quantity);

                        int type = valueCheck.checkChoice("Chose condition of equip\n1. New\n2. Old\n3. Normal", 1, 3, "Chose from 1 - 3");
                        if (type == 1) {
                            equip.setCondition("New");
                        }
                        if (type == 2) {
                            equip.setCondition("Old");
                        }
                        if (type == 3) {
                            equip.setCondition("Normal");
                        }
                        System.out.println("Update success");
                        break;
                    } else {
                        break;
                    }
                default:
                    System.out.println("Too much equip can not update");
                    break;
            }
            if (!valueCheck.checkYesorNo("Do you want to update again\n1. Yes\n2. No", "Just 1 or 2")) {
                break;
            }
        } while (true);
    }

    @Override
    public void delete() {
        int countObj = search();
        do {
            switch (countObj) {
                case 0:
                    break;
                case 1:
                    if (valueCheck.checkYesorNo("Do you want to remove quantity\n1. Yes\n2. No", "Just 1 or 2")) {
                        int quantityRemove = valueCheck.checkChoice("Enter quantity to remove", 1, equip.getQuantity(), "Quantity remove can not lower than 1 and higher than quantity of this equip");
                        if (quantityRemove == equip.getQuantity()) {
                            if (valueCheck.checkYesorNo("Remove this equip ?\n1. Yes\n2. No", "Just 1 or 2")) {
                                for (Classes cl : Classes.classesList) {
                                    cl.getClassesEquipments().remove(equip);
                                }
                                Equipment.equipList.remove(equip);
                                System.out.println("Remove success");
                                break;
                            }
                            break;
                        } else {
                            if (valueCheck.checkYesorNo("Remove the quantity ?\n1. Yes\n2. No", "Just 1 or 2")) {
                                equip.setQuantity(equip.getQuantity() - quantityRemove);
                                System.out.println("Remove success");
                                break;
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Too much member can not remove");
                    break;
            }
            if (!valueCheck.checkYesorNo("Do you want to delete again\n1. Yes\n2. No", "Just 1 or 2")) {
                break;
            }
        } while (true);
    }

    @Override
    public void sort() {
        ArrayList<Equipment> equip = new ArrayList<>(Equipment.equipList);
        Collections.sort(equip, new ManagerEquipment());
        System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-30s|%-30s|%-29s|%-20s|\n" + color.RESET, "ID Equipment", "Name", "Type", "Quantity", "Condition");
        for (Equipment equipment : equip) {
            System.out.printf("|%-15s|%-30s|%-30s|%-29s|%-20s|%n", equipment.getEquipID(), equipment.getName(), equipment.getType(), equipment.getQuantity(), equipment.getCondition());
        }
    }

    @Override
    public void readFile() {
        String filePath = "src/file/Equipment.txt";
        File f = new File(filePath);
        if (!f.exists()) {
            System.out.println("Cannot load equipment file");
            return;
        }
        try {
            List<String> allLines = Files.readAllLines(f.toPath());
            allLines.forEach((line) -> {
                addElementFromFile(line, equip, Equipment.equipList);
            });
            System.out.println("Load equipment file success...");
        } catch (IOException e) {
            System.out.println("Cannot load equipment file");
        }
    }

    @Override
    public void addElementFromFile(String line, Object obj, ArrayList array) {
        Equipment equipment = new Equipment();
        String[] lineObj = line.split("\\s*_\\s*");
        equipment.setEquipID(lineObj[0]);
        equipment.setName(lineObj[1]);
        equipment.setType(lineObj[2]);
        equipment.setQuantity(Integer.parseInt(lineObj[3]));
        equipment.setCondition(lineObj[4]);
        array.add(equipment);
    }

    @Override
    public void add() {
        do {
            Equipment equip1 = new Equipment();
            do {
                String IDequip = valueCheck.checkRegex("Enter equip ID: ",
                        "EQU\\d{5}",
                        "Your ID don't match with type EQUxxxxx");
                if (valueCheck.checkIDExist(IDequip, equip1) == null) {
                    equip1.setEquipID(IDequip.toUpperCase());
                    break;
                } else {
                    System.out.println("ID Existed");
                }
            } while (true);

            String equipName = valueCheck.checkRegex("Input name", ".+", "Do not input empty");
            equip1.setName(equipName);

            String typeEquip = valueCheck.checkRegex("Input type", ".+", "Do not input empty");
            equip1.setType(typeEquip);

            int quantity = valueCheck.checkChoice("Input quantity", 0, 1000, "Quantity is > 0 and < 1000");
            equip1.setQuantity(quantity);

            int type = valueCheck.checkChoice("Chose condition of equip\n1. New\n2. Old\n3. Normal", 1, 3, "Chose from 1 - 3");
            if (type == 1) {
                equip1.setCondition("New");
            }
            if (type == 2) {
                equip1.setCondition("Old");
            }
            if (type == 3) {
                equip1.setCondition("Normal");
            }

            Equipment.equipList.add(equip1);
            System.out.println("Added successful !!!");
            if (!valueCheck.checkYesorNo("Do you want to add more ?\n1. Yes\n2. No", "Chose between 1 or 2 !!!")) {
                break;
            }
        } while (true);
    }

    @Override
    public int compare(Equipment o1, Equipment o2) {
        String[] arr1 = o1.getName().split("\\s+");
        String[] arr2 = o2.getName().split("\\s+");
        String lastName1 = arr1[arr1.length - 1];
        String lastName2 = arr2[arr2.length - 1];
        return lastName1.compareTo(lastName2);
    }

    @Override
    public void saveFile() {

        String filePath = "src/file/Equipment.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Cannot load equipment file");
            }
        }
        try {
            PrintWriter pw = new PrintWriter(filePath);
            for (Equipment eq : Equipment.equipList) {
                pw.println(eq.toString());
            }
            System.out.println("Save equipment file success");
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Save unsuccess");
        }
    }

}
