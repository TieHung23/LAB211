/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;
import libary_obj.Status;

/**
 *
 * @author ACER
 */
public class Validation implements IValidation {

    Scanner sc = new Scanner(System.in);

    @Override
    public String checkString(String msg, statusInput stInput) {
        do {
            System.out.println(msg);
            String input = sc.nextLine().trim();
            if (stInput == statusInput.update && input.isEmpty()) {
                return input;
            }
            if (input.isEmpty() || input == null) {
                continue;
            }
            return input;
        } while (true);
    }

    @Override
    public int checkInt(String msg, int min, int max, statusInput stInput) {
        do {
            String number = checkString(msg, stInput);
            if (number.isEmpty() && stInput == statusInput.update) {
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
    public boolean checkYoN(String msg) {
        int choice = checkInt(msg, 1, 2, statusInput.input);
        if (choice == 1) {
            return true;
        }
        return false;
    }

    @Override
    public LocalDate checkDate(String msg, statusInput stInput) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = null;
        boolean valid = false;
        do {
            String input = checkString(msg, stInput);
            String[] arr = input.split("-");
            if (input.isEmpty() && stInput == statusInput.update) {
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
    public String checkRegex(String msg, String regex, statusInput stInput) {
        do {
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            String str = checkString(msg, stInput);
            if (str.isEmpty() && stInput == statusInput.update) {
                return str;
            }
            if (!pattern.matcher(str).matches() || str == null) {
                continue;
            }
            return str;
        } while (true);
    }

    @Override
    public Status status(String msg, statusInput stInput) {
        while (true) {
            String input = checkString(msg, stInput);
            if (input.isEmpty() && stInput == statusInput.update) {
                return null;
            }
            if (input.equalsIgnoreCase("active")) {
                return Status.active;
            } else if (input.equalsIgnoreCase("unactive")) {
                return Status.unactive;
            } else {
                System.out.println("Must type active or unactive");
            }
        }
    }
}
