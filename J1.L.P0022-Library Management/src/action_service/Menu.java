/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import utils.statusInput;

/**
 *
 * @author ACER
 */
public class Menu {

    static final IService service = new Service();

    public void mainMenu() {
        Service.MANAGER__LOANS.readFile();
        Service.MANAGER_USERS.readFile();
        Service.MANAGER_BOOKS.readFile();
        boolean isStop = true;
        do {
            System.out.println("╔═════════════════════════╗");
            System.out.println("║   ------ Library Management ------     ║");
            System.out.println("║   1. Books                             ║");
            System.out.println("║   2. Users                             ║");
            System.out.println("║   3. Loans                             ║");
            System.out.println("║   4. Exit!                             ║");
            System.out.println("╚═════════════════════════╝");
            int choice = Service.VALIDATION.checkInt("Input your choice: ", 1, 4, statusInput.input);
            switch (choice) {
                case 1:
                    menuBook();
                    break;
                case 2:
                    menuUser();
                    break;
                case 3:
                    menuLoan();
                    break;
                default:
                    isStop = false;
                    System.out.println("See you again!");
                    break;
            }

        } while (isStop);
    }

    public void menuBook() {
        boolean isStop = true;
        do {
            System.out.println("╔═════════════════════════╗");
            System.out.println("║      ------ Book Management ------     ║");
            System.out.println("║   1. Add new book                      ║");
            System.out.println("║   2. Update book                       ║");
            System.out.println("║   3. Delete book                       ║");
            System.out.println("║   4. Show all books                    ║");
            System.out.println("║   5. Back to main menu                 ║");
            System.out.println("╚═════════════════════════╝");
            int choice = Service.VALIDATION.checkInt("Input your choice: ", 1, 5, statusInput.input);
            switch (choice) {
                case 1:
                    service.addBooks();
                    break;
                case 2:
                    service.updateBook();
                    break;
                case 3:
                    service.deleteBook();
                    break;
                case 4:
                    service.showAllBook(2);
                    break;
                default:
                    Service.MANAGER_BOOKS.wirtefile();
                    isStop = false;
                    break;
            }
        } while (isStop);
    }

    public void menuUser() {
        boolean isStop = true;
        do {
            System.out.println("╔═════════════════════════╗");
            System.out.println("║   ------ User Management ------        ║");
            System.out.println("║   1. Add new user                      ║");
            System.out.println("║   2. Update user                       ║");
            System.out.println("║   3. Delete user                       ║");
            System.out.println("║   4. Show all user                     ║");
            System.out.println("║   5. Back to main menu                 ║");
            System.out.println("╚═════════════════════════╝");
            int choice = Service.VALIDATION.checkInt("Input your choice: ", 1, 5, statusInput.input);
            switch (choice) {
                case 1:
                    service.addUsers();
                    break;
                case 2:
                    service.updateUsers();
                    break;
                case 3:
                    service.deleteUses();
                    break;
                case 4:
                    service.showAllUsers(2);
                    break;
                default:
                    Service.MANAGER_USERS.wirtefile();
                    isStop = false;
                    break;
            }
        } while (isStop);
    }

    public void menuLoan() {
        boolean isStop = true;
        do {
            System.out.println("╔═════════════════════════╗");
            System.out.println("║   ------ Loan Management ------        ║");
            System.out.println("║   1. Add new loan                      ║");
            System.out.println("║   2. Update loan                       ║");
            System.out.println("║   3. Show all loans                    ║");
            System.out.println("║   4. Back to main menu                 ║");
            System.out.println("╚═════════════════════════╝");
            int choice = Service.VALIDATION.checkInt("Input your choice: ", 1, 4, statusInput.input);
            switch (choice) {
                case 1:
                    service.addLoans();
                    break;
                case 2:
                    service.updateLoans();
                    break;
                case 3:
                    service.showAllLoans();
                    break;
                default:
                    Service.MANAGER__LOANS.wirtefile();
                    isStop = false;
                    break;
            }
        } while (isStop);
    }
}
