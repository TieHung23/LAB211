/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import utils.Status;

/**
 *
 * @author ACER
 */
public class Menu {

    static final IService service = new Service();

    public static void show() {
        Service.event.readFile();
        boolean checked = true;
        do {
            System.out.println("---------------------------------------------------");
            System.out.println("                   Event Manager");
            System.out.println("---------------------------------------------------");
            System.out.println("            \033[1;34m 1. Add new event");
            System.out.println("            \033[1;34m 2. Search event by ID");
            System.out.println("            \033[1;34m 3. Search event by Location");
            System.out.println("            \033[1;34m 4. Update or Delete event");
            System.out.println("            \033[1;34m 5. Save file");
            System.out.println("            \033[1;34m 6. Show all list");
            System.out.println("            \033[1;34m 7. Quit");
            System.out.println("---------------------------------------------------");
            int choice = Service.validation.checkInt("Input your choice", 1, 7, Status.INPUT);
            switch (choice) {
                case 1:
                    service.showAllEvent();
                    do {
                        service.addEvent();
                        if (!Service.validation.checkYesorNo("Do you want to add again\n1. Yes\n2. No")) {
                            break;
                        }
                    } while (true);
                    break;
                case 2:
                    do {
                        service.searchByID();
                        if (!Service.validation.checkYesorNo("Do you want to search again\n1. Yes\n2. No")) {
                            break;
                        }
                    } while (true);
                    break;
                case 3:
                    do {
                        service.searchByLocation();
                        if (!Service.validation.checkYesorNo("Do you want to search again\n1. Yes\n2. No")) {
                            break;
                        }
                    } while (true);
                    break;
                case 4:
                    service.showAllEvent();
                    int option = Service.validation.checkInt("Input your choice\n1. Update\n2. Delete", 1, 2, Status.INPUT);
                    if (option == 1) {
                        do {
                            service.updateEvent();
                            if (!Service.validation.checkYesorNo("Do you want to update again\n1. Yes\n2. No")) {
                                break;
                            }
                        } while (checked);
                    } else {
                        do {
                            service.deleteEvent();
                            if (!Service.validation.checkYesorNo("Do you want to delete again\n1. Yes\n2. No")) {
                                break;
                            }
                        } while (checked);
                    }
                    break;
                case 5:
                    if(Service.event.writeFile() == true){
                        System.out.println("Save success");
                    }
                    else System.out.println("Cannot save");   
                    break;
                case 6:
                    System.out.println("Before sort: ");
                    service.showAllEvent();
                    System.out.println("After sort: ");
                    service.sortEvent();
                    break;
                default:
                    System.out.println("Good bye!");
                    checked = false;
                    break;
            }
        } while (checked);

    }
}
