/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import event_obj.StatusEvent;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author ACER
 */
public class Validation implements IValidation {

    private Scanner sc = new Scanner(System.in);

    @Override
    public String checkString(String msg, Status status) {
        do {
            System.out.println(msg);
            String input = sc.nextLine().trim();
            if (status == Status.UPDATE && input.isEmpty()) {
                return input;
            }
            if (input.isEmpty() || input == null) {
                continue;
            }
            return input;
        } while (true);
    }

    @Override
    public int checkInt(String msg, int min, int max, Status status) {
        do {
            String number = checkString(msg, status);
            if (number.isEmpty() && status == Status.UPDATE) {
                return -1;
            }
            try {
                int toNumber = Integer.parseInt(number);

                if (toNumber < min || toNumber > max) {
                    System.out.println("Must input from " + min + " to " + max);
                    continue;
                }
                return toNumber;
            } catch (NumberFormatException e) {
                System.out.println("Must input number");
            }
        } while (true);
    }

    @Override
    public LocalDate checkDate(String msg, Status status) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = null;
        boolean valid = false;
        do {
            String input = checkString(msg, status);
            String[] arr = input.split("-");
            if (input.isEmpty() && status == Status.UPDATE) {
                return null;
            }
            try {
                date = LocalDate.parse(input, dtf);
                if (date.getDayOfMonth() != Integer.parseInt(arr[0])) {
                    System.out.println("Invalid date");
                    continue;
                }
                valid = true;
            } catch (DateTimeException e) {
                System.out.println("Wrong format of dd-MM-yyyy or invalid date\nPLS CHECK AGAIN!!");
            }
        } while (!valid);
        return date;
    }

    @Override
    public boolean checkYesorNo(String msg) {
        int choice = checkInt(msg, 1, 2, Status.INPUT);
        if (choice == 1) {
            return true;
        }
        return false;
    }

    @Override
    public String checkRegex(String msg, String regex) {
        do {
            String str = checkString(msg, Status.INPUT);
            if (str.toUpperCase().matches(regex)) {
                return str;
            } else {
                System.out.println("Wrong format");
            }
        } while (true);
    }

    @Override
    public StatusEvent checkEventTus(String msg, Status status) {
        while (true) {
            String input = checkString(msg, status);
            if (input.isEmpty() && status == Status.UPDATE) {
                return null;
            }
            if (input.equalsIgnoreCase("Available")) {
                return StatusEvent.AVAILABLE;
            } else if (input.equalsIgnoreCase("notavailable")) {
                return StatusEvent.NotAVAILABLE;
            } else {
                System.out.println("Must type available or not available");
            }
        }
    }

}
