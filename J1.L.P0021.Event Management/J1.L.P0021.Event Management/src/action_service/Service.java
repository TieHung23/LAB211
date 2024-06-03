/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import data_obj.DAOEvent;
import data_obj.DAOManager;
import data_obj.IDAOEvent;
import data_obj.IDAOManager;
import event_obj.Event;
import event_obj.StatusEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import main.color;
import utils.IValidation;
import utils.Status;

/**
 *
 * @author ACER
 */
public class Service implements IService {

    static final IDAOManager manager = new DAOManager();
    static final IValidation validation = manager.validator();
    static final IDAOEvent event = manager.event();

    @Override
    public void showAllEvent() {
        System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-30s|%-30s|%-25s|%-40s|%-20S|%n" + color.RESET, "ID event", "Name", "Date", "Location", "Attendencees", "Status");
        for (Event event : event.getAllEvent()) {
            System.out.printf(event.toPrint());
        }
    }

    @Override
    public void addEvent() {
        String EventID = "";
        do {
            EventID = validation.checkRegex("Input event ID", "^ID\\d{4}$");
            if (!event.checkExistID(EventID)) {
                break;
            }
            System.out.println("Event ID is Existed");
        } while (true);
        String name = validation.checkString("Input event name", Status.INPUT);
        LocalDate date = validation.checkDate("Input date", Status.INPUT);
        String location = validation.checkString("Input event location", Status.INPUT);
        int attendees = validation.checkInt("Input number of attendees", 0, Integer.MAX_VALUE, Status.INPUT);
        StatusEvent statusEv = validation.checkEventTus("Input event status", Status.INPUT);
        Event eventInput = new Event(EventID.toUpperCase(), name, date, location, attendees, statusEv);
        event.addEvent(eventInput);
        System.out.printf("Added success !\n");
        System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-30s|%-30s|%-25s|%-40s|%-20S|%n" + color.RESET, "ID event", "Name", "Date", "Location", "Attendencees", "Status");
        System.out.printf(eventInput.toPrint());
    }

    @Override
    public Event searchByID() {
        String msg = validation.checkRegex("Input event ID", "^ID\\d{4}$");
        Event eventSearch = event.getEvent(msg);
        if (eventSearch != null) {
            System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-30s|%-30s|%-25s|%-40s|%-20S|%n" + color.RESET, "ID event", "Name", "Date", "Location", "Attendencees", "Status");
            System.out.printf(eventSearch.toPrint());
            return eventSearch;
        }
        System.out.println("Cannot find event");
        return null;
    }

    @Override
    public void searchByLocation() {
        String msg = validation.checkString("Input location", Status.INPUT);
        event.getEventByLocation(msg);
    }

    @Override
    public void updateEvent() {
        Event eventSearch = searchByID();
        if (eventSearch != null) {
            String name = validation.checkString("Input event name", Status.UPDATE);
            LocalDate date = validation.checkDate("Input date", Status.UPDATE);
            String location = validation.checkString("Input event location", Status.UPDATE);
            int attendees = validation.checkInt("Input number of attendees", 0, Integer.MAX_VALUE, Status.UPDATE);
            StatusEvent statusEv = validation.checkEventTus("Input event status", Status.UPDATE);

            if (eventSearch.setEventName(name)) {
                System.out.println("Change name event success to: " + eventSearch.getEventName());
            }
            if (eventSearch.setDate(date)) {
                System.out.println("Change date event success to: " + eventSearch.getDate());
            }
            if (eventSearch.setEventLocation(location)) {
                System.out.println("Change location event success to: " + eventSearch.getEventLocation());
            }
            if (eventSearch.setEventAttendees(attendees)) {
                System.out.println("Change attendees event success to: " + eventSearch.getEventAttendees());
            }
            if (eventSearch.setStatus(statusEv)) {
                System.out.println("Change status event success to: " + eventSearch.getStatus());
            }
            System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-30s|%-30s|%-25s|%-40s|%-20S|%n" + color.RESET, "ID event", "Name", "Date", "Location", "Attendencees", "Status");
            System.out.printf(eventSearch.toPrint());
        } else {
            System.out.println("Cannot find");
        }
    }

    @Override
    public void deleteEvent() {
        Event eventSearch = searchByID();
        if (eventSearch != null) {
            event.deleteEvent(eventSearch);
            System.out.println("Delete success");
        } else {
            System.out.println("Cannot find");
        }
    }

    @Override
    public void sortEvent() {
        List<Event> subList = new ArrayList<>(event.getAllEvent());
        Event swapEvent = new Event();
        for (int i = 0; i < subList.size() - 1; i++) {
            boolean swaped = false;
            for (int j = 0; j < subList.size() - 1 - i; j++) {
                if (subList.get(j).getDate().isAfter(subList.get(j + 1).getDate())) {
                    Collections.swap(subList, j, j + 1);
                    swaped = true;
                }
                if (subList.get(j).getDate().isEqual(subList.get(j + 1).getDate()) && sortName(subList.get(j), subList.get(j + 1)) == 1) {
                    Collections.swap(subList, j, j + 1);
                    swaped = true;
                }
            }
            if (swaped = false) {
                break;
            }
        }
        System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-30s|%-30s|%-25s|%-40s|%-20S|%n" + color.RESET, "ID event", "Name", "Date", "Location", "Attendencees", "Status");
        for (Event event : subList) {
            System.out.printf(event.toPrint());
        }
    }

    @Override
    public int sortName(Event o1, Event o2) {
        String[] nameField0 = o1.getEventName().split("\\s+");
        String[] nameField1 = o2.getEventName().split("\\s+");
        String lastName0 = nameField0[nameField0.length - 1];
        String lastName1 = nameField1[nameField1.length - 1];
        return lastName0.compareTo(lastName1);
    }
}
