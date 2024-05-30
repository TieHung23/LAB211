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
import model.Member;
import view.color;

/**
 *
 * @author ACER
 */
public class ManagerMember implements IManager, IFileReaderWriter, Comparator<Member> {

    Validate valueCheck = new Validate();
    Member member = new Member();

    @Override
    public void show() {
        System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-10s|%-30s|%-30s|%-29s|%-25s|\n" + color.RESET, "ID Member", "Name", "Address", "Phone Number", "Membership Type");
        for (Member mb : Member.memberList) {
            System.out.printf("|%-10s|%-30s|%-30s|%-29s|%-25s|%n" + color.RESET, mb.getID(), mb.getName(), mb.getAddress(), mb.getContactInfor(), mb.getMemberType());
        }
    }

    @Override
    public int search() {
        do {
            int cnt = 0;
            String ID = valueCheck.checkRegex("Input ID", ".+", "Do not input empty");
            System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-10s|%-30s|%-30s|%-29s|%-25s|\n" + color.RESET, "ID Member", "Name", "Address", "Phone Number", "Membership Type");
            for (Member mb : Member.memberList) {
                if (mb.getID().toUpperCase().contains(ID.toUpperCase())) {
                    System.out.printf("|%-10s|%-30s|%-30s|%-29s|%-25s|%n", mb.getID(), mb.getName(), mb.getAddress(), mb.getContactInfor(), mb.getMemberType());
                    cnt++;
                    member = mb;
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
            switch (search()) {
                case 0:
                    break;
                case 1:
                    if (valueCheck.checkYesorNo("Do you want to update\n1. Yes\n2. No", "Just 1 or 2")) {
                        String nameMem = valueCheck.checkRegex("Input Name", ".+", "Do not input empty");
                        member.setName(nameMem);

                        String addressMem = valueCheck.checkRegex("Input address", ".+", "Do not input empty");
                        member.setAddress(addressMem);

                        String contactMem = valueCheck.checkRegex("Input contact", "^0\\d{9}$", "Contact must have ONLY 10 digit !");
                        member.setContactInfor(contactMem);

                        int type = valueCheck.checkChoice("Chose type of member\n1. Normal\n2. Vip\n3. VVip", 1, 3, "Chose from 1 - 3");
                        if (type == 1) {
                            member.setMemberType("Normal");
                        }
                        if (type == 2) {
                            member.setMemberType("Vip");
                        }
                        if (type == 3) {
                            member.setMemberType("VVip");
                        }
                        System.out.println("Update success");
                        break;
                    } else {
                        break;
                    }
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
        int countObj = search();
        do {
            switch (countObj) {
                case 0:
                    break;
                case 1:
                    if (valueCheck.checkYesorNo("Do you want to delete\n1. Yes\n2. No", "Just 1 or 2")) {
                        Member.memberList.remove(member);
                        for (Classes cl : Classes.classesList) {
                            cl.getClassesMember().remove(member);
                        }
                        System.out.println("Delete success");
                        break;
                    }
                default:
                    System.out.println("Too much member can not delete");
                    break;
            }
            if (!valueCheck.checkYesorNo("Do you want to delete again\n1. Yes\n2. No", "Just 1 or 2")) {
                break;
            }
        } while (true);
    }

    @Override
    public int compare(Member o1, Member o2) {
        String[] nameField0 = o1.getName().split("\\s+");
        String[] nameField1 = o2.getName().split("\\s+");
        String lastName0 = nameField0[nameField0.length - 1];
        String lastName1 = nameField1[nameField1.length - 1];
        return lastName0.compareTo(lastName1);
    }

    @Override
    public void saveFile() {
        String filePath = "src/file/Member.txt";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println("Cannot load member file");
            }
        }
        try {
            PrintWriter pw = new PrintWriter(filePath);
            for (Member mb : Member.memberList) {
                pw.println(mb.toString());
            }
            System.out.println("Save member file success");
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Save unsuccess");
        }
    }

    @Override
    public void readFile() {
        String filePath = "src/file/Member.txt";
        File f = new File(filePath);
        if (!f.exists()) {
            System.out.println("Cannot load member file");
            return;
        }
        try {
            List<String> allLines = Files.readAllLines(f.toPath());
            allLines.forEach((line) -> {
                Member mb = new Member();
                addElementFromFile(line, mb, Member.memberList);
            });
            System.out.println("Load member file success...");
        } catch (IOException e) {
            System.out.println("Cannot load member file");
        }
    }

    @Override
    public void addElementFromFile(String line, Object obj, ArrayList array) {
        Member mb = new Member();
        String[] lineObj = line.split("\\s*_\\s*");
        mb.setID(lineObj[0]);
        mb.setName(lineObj[1]);
        mb.setAddress(lineObj[2]);
        mb.setContactInfor(lineObj[3]);
        mb.setMemberType(lineObj[4]);
        array.add(mb);
    }

    @Override
    public void sort() {
        ArrayList<Member> mb = new ArrayList<>(Member.memberList);
        Collections.sort(mb, new ManagerMember());
        System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-10s|%-30s|%-30s|%-29s|%-25s|%n" + color.RESET, "ID Member", "Name", "Address", "Phone Number", "Membership Type");
        for (Member member : mb) {
            System.out.printf("|%-10s|%-30s|%-30s|%-29s|%-25s|%n", member.getID(), member.getName(), member.getAddress(), member.getContactInfor(), member.getMemberType());
        }
    }

    @Override
    public void add() {
        do {
            Member member1 = new Member();
            do {
                String IDmember = valueCheck.checkRegex("Enter member ID: ",
                        "GY\\d{5}",
                        "Your ID don't match with type GYxxxxx");
                if (valueCheck.checkIDExist(IDmember, member1) == null) {
                    member1.setID(IDmember.toUpperCase());
                    break;
                } else {
                    System.out.println("ID Existed");
                }
            } while (true);

            String nameMem = valueCheck.checkRegex("Input name", ".+", "Do not input empty");
            member1.setName(nameMem);

            String addressMem = valueCheck.checkRegex("Input address", ".+", "Do not input empty");
            member1.setAddress(addressMem);

            String contactMem = valueCheck.checkRegex("Input contact", "^0\\d{9}$", "Contact must have ONLY 10 digit !");
            member1.setContactInfor(contactMem);

            int type = valueCheck.checkChoice("Chose type of member\n1. Normal\n2. Vip\n3. VVip", 1, 3, "Chose from 1 - 3");
            if (type == 1) {
                member1.setMemberType("Normal");
            }
            if (type == 2) {
                member1.setMemberType("Vip");
            }
            if (type == 3) {
                member1.setMemberType("VVip");
            }

            Member.memberList.add(member1);
            System.out.println("Added successful !!!");
            if (!valueCheck.checkYesorNo("Do you want to add more ?\n1. Yes\n2. No", "Chose between 1 or 2 !!!")) {
                break;
            }
        } while (true);
    }

}
