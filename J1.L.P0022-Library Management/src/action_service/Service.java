/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import data_obj.DAOManager;
import data_obj.IDAOBooks;
import data_obj.IDAOLoans;
import data_obj.IDAOManager;
import data_obj.IDAOUsers;
import java.time.LocalDate;
import libary_obj.Books;
import libary_obj.Loans;
import libary_obj.Status;
import libary_obj.User;
import program.color;
import utils.IValidation;
import utils.statusInput;

/**
 *
 * @author ACER
 */
public class Service implements IService {

    static final IDAOManager MANAGER = new DAOManager();
    static final IValidation VALIDATION = MANAGER.validation();
    static final IDAOBooks MANAGER_BOOKS = MANAGER.booksManager();
    static final IDAOUsers MANAGER_USERS = MANAGER.usersManager();
    static final IDAOLoans MANAGER__LOANS = MANAGER.loansManager();

    @Override
    public void addBooks() {
        do {
            String bookID;
            do {
                bookID = VALIDATION.checkRegex("Input Book ID: ", "B\\d{5}", statusInput.input).toUpperCase();
                if (MANAGER_BOOKS.checkExistID(bookID) == null) {
                    break;
                }
                System.out.println("ID Existed");
            } while (true);
            String bookTitle = VALIDATION.checkString("Input Book title: ", statusInput.input);
            String author = VALIDATION.checkString("Input Book author: ", statusInput.input);
            String publicationYear = String.valueOf(VALIDATION.checkInt("Input Book publication year: ", 1970, 2024, statusInput.input));
            String publisher = VALIDATION.checkString("Input publisher: ", statusInput.input);
            String ISBN;
            do {
                ISBN = VALIDATION.checkRegex("Input ISBN", "\\d{11}", statusInput.input);
                if (MANAGER_BOOKS.checkExistISBN(ISBN) == null) {
                    break;
                }
                System.out.println("ISBN is existed");
            } while (true);
            Books book = new Books(bookID, bookTitle, author, publicationYear, publisher, ISBN);
            System.out.println(book.toString());
            MANAGER_BOOKS.add(book);
            System.out.println("Add success !!!!");
            if (!VALIDATION.checkYoN("Do you want to add more ?\n1. Yes\n2. No")) {
                break;
            }
        } while (true);
    }

    @Override
    public void updateBook() {
        Books book = MANAGER_BOOKS.checkExistID(VALIDATION.checkRegex("Input Book ID", "B\\d{5}", statusInput.input).toUpperCase());
        if (book != null && VALIDATION.checkYoN("Do you want to update this book\n1. Yes\n2. No")) {
            String bookTitle = VALIDATION.checkString("Input Book title: ", statusInput.update);
            String author = VALIDATION.checkString("Input Book author: ", statusInput.update);
            String publicationYear = String.valueOf(VALIDATION.checkInt("Input Book publication year: ", 1970, 2024, statusInput.update));
            String publisher = VALIDATION.checkString("Input publisher: ", statusInput.update);
            String ISBN;
            do {
                ISBN = VALIDATION.checkRegex("Input ISBN", "\\d{11}", statusInput.update);
                if (MANAGER_BOOKS.checkExistISBN(ISBN) == null) {
                    break;
                }
                System.out.println("ISBN is existed");
            } while (true);
            Status status = VALIDATION.status("Input status", statusInput.update);
            MANAGER_BOOKS.update(book, bookTitle, author, publicationYear, publisher, ISBN, status);
        } else if (book == null) {
            System.out.println("ID does not existed");
        } else {
            System.out.println("See you again");
        }
    }

    @Override
    public void deleteBook() {
        Books book = MANAGER_BOOKS.checkExistID(VALIDATION.checkRegex("Input Book ID", "B\\d{5}", statusInput.input).toUpperCase());
        if (book != null && VALIDATION.checkYoN("Do you want to delete this book\n1. Yes\n2. No")) {
            MANAGER_BOOKS.delete(book);
        } else if (book == null) {
            System.out.println("ID does not existed !");
        } else {
            System.out.println("See you again !");
        }
    }

    @Override
    public void showAllBook(int num) {
        Books[] arr = MANAGER_BOOKS.toArrObj();
        if (num == 1) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].getStatus() == Status.active) {
                    System.out.println(arr[i].toString());
                }
            }
        }
        if (num == 2) {
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i].toString());
            }
        }
    }

    @Override
    public void addUsers() {
        do {
            User user = MANAGER_USERS.genUserID();
            String name = VALIDATION.checkString("Input User name: ", statusInput.input);
            LocalDate dateOfBirth = VALIDATION.checkDate("Input User birthday", statusInput.input);
            String phoneNum = VALIDATION.checkRegex("Input User phoneNumber", "(?i)^0\\d{9}$", statusInput.input);
            String email = VALIDATION.checkRegex("Input User email", ".*@fpt\\.edu\\.vn$", statusInput.input);
            user.setName(name);
            user.setDateOfBirth(dateOfBirth);
            user.setPhoneNum(phoneNum);
            user.setEmail(email);
            user.setStatus(Status.active);
            System.out.println(user.toString());
            MANAGER_USERS.add(user);
            System.out.println("Add success !!!!");
            if (!VALIDATION.checkYoN("Do you want to add more ?\n1. Yes\n2. No")) {
                break;
            }
        } while (true);
    }

    @Override
    public void updateUsers() {
        User user = MANAGER_USERS.checkExistID(VALIDATION.checkRegex("Input User ID", "U\\d{5}", statusInput.input).toUpperCase());
        if (user != null && VALIDATION.checkYoN("Do you want to update this user\n1. Yes\n2. No")) {
            String name = VALIDATION.checkString("Input User name: ", statusInput.update);
            LocalDate dateOfBirth = VALIDATION.checkDate("Input User birthday", statusInput.update);
            String phoneNum = VALIDATION.checkRegex("Input User phoneNumber", "0\\d{9}", statusInput.update);
            String email = VALIDATION.checkRegex("Input User email", "^[a-zA-Z0-9._%+-]+@fpt\\.edu\\.vn$", statusInput.update);
            Status status = VALIDATION.status("Input status", statusInput.update);
            MANAGER_USERS.update(user, name, dateOfBirth, phoneNum, email, status);
        } else if (user == null) {
            System.out.println("ID does not existed");
        } else {
            System.out.println("See you again");
        }
    }

    @Override
    public void deleteUses() {
        User user = MANAGER_USERS.checkExistID(VALIDATION.checkRegex("Input User ID", "U\\d{5}", statusInput.input).toUpperCase());
        if (user != null && VALIDATION.checkYoN("Do you want to delete this user\n1. Yes\n2. No")) {
            MANAGER_USERS.delete(user);
        } else if (user == null) {
            System.out.println("ID does not exit");
        } else {
            System.out.println("See you again");
        }
    }

    @Override
    public void showAllUsers(int num) {
        User[] arr = MANAGER_USERS.toArrObj();
        if (num == 1) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].getStatus() == Status.active) {
                    System.out.println(arr[i].toString());
                }
            }
        }
        if (num == 2) {
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i].toString());
            }
        }
    }

    @Override
    public void addLoans() {
        do {
            String loansID;
            do {
                loansID = VALIDATION.checkRegex("Input Loans ID: ", "T\\d{5}", statusInput.input).toUpperCase();
                if (MANAGER__LOANS.checkExistID(loansID) == null) {
                    break;
                }
                System.out.println("ID Existed");
            } while (true);

            Books book;
            do {
                book = MANAGER_BOOKS.checkExistID(VALIDATION.checkRegex("Input Book ID: ", "B\\d{5}", statusInput.input).toUpperCase());
                if (book != null && book.getStatus() == Status.active) {
                    break;
                }
                System.out.println("ID does not existed or unactive");
            } while (true);

            User user;
            do {
                user = MANAGER_USERS.checkExistID(VALIDATION.checkRegex("Input User ID: ", "U\\d{5}", statusInput.input).toUpperCase());
                if (user != null && user.getStatus() == Status.active) {
                    break;
                }
                System.out.println("ID does not existed or unactive");
            } while (true);

            LocalDate borrowDate = VALIDATION.checkDate("Input borrow date", statusInput.input);
            LocalDate returnDate;

            do {
                returnDate = VALIDATION.checkDate("Input return date", statusInput.input);
                if (returnDate.isAfter(borrowDate)) {
                    break;
                }
                System.out.println("Return date is invalid");
            } while (true);

            Loans loan = new Loans(loansID, book, user, borrowDate, returnDate);
            System.out.println(loan.toString());
            MANAGER__LOANS.add(loan);
            if (!VALIDATION.checkYoN("Do you want to add more ?\n1. Yes\n2. No")) {
                break;
            }
        } while (true);
    }

    @Override
    public void updateLoans() {
        Loans loan = MANAGER__LOANS.checkExistID(VALIDATION.checkRegex("Input Loan ID", "T\\d{5}", statusInput.input).toUpperCase());
        if (loan != null && VALIDATION.checkYoN("Do you want to update this book\n1. Yes\n2. No")) {
            Books book;
            do {
                book = MANAGER_BOOKS.checkExistID(VALIDATION.checkRegex("Input Book ID: ", "B\\d{5}", statusInput.update).toUpperCase());
                if (book != null && book.getStatus() == Status.active) {
                    break;
                }
                System.out.println("ID does not existed or unactive");
                if (!VALIDATION.checkYoN("Do you want to update book again ?\n1. Yes\n2. No")) {
                    book = null;
                    break;
                }
            } while (true);
            User user;
            do {
                user = MANAGER_USERS.checkExistID(VALIDATION.checkRegex("Input User ID: ", "U\\d{5}", statusInput.update).toUpperCase());
                if (user != null && user.getStatus() == Status.active) {
                    break;
                }
                System.out.println("ID does not existed or unactive");
                if (!VALIDATION.checkYoN("Do you want to update user again ?\n1. Yes\n2. No")) {
                    user = null;
                    break;
                }
            } while (true);
            LocalDate borrowDate = VALIDATION.checkDate("Input borrow date", statusInput.update);
            LocalDate returnDate;
            do {
                returnDate = VALIDATION.checkDate("Input return date", statusInput.update);
                if (borrowDate == null) {
                    if (returnDate == null) {
                        break;
                    } else {
                        if (returnDate.isAfter(borrowDate)) {
                            break;
                        }
                        System.out.println("Invalid return date");
                    }
                }
                if (borrowDate != null) {
                    if (returnDate == null) {
                        if (loan.getReturnDate().isAfter(borrowDate)) {
                            break;
                        }
                        System.out.println("Invalid return date");
                    }
                    if (returnDate != null) {
                        if (returnDate.isAfter(borrowDate)) {
                            break;
                        }
                        System.out.println("Invalid return date");
                    }
                }
            } while (true);
            MANAGER__LOANS.update(loan, book, user, borrowDate, returnDate);
        } else if (loan == null) {
            System.out.println("ID does not existed");
        } else {
            System.out.println("See you again");
        }
    }

    @Override
    public void showAllLoans() {
        Loans[] arr = MANAGER__LOANS.toArrObj();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i].toString());
        }
    }

}
