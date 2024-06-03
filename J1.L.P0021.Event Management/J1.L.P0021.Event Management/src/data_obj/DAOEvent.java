/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_obj;

import event_obj.Event;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import main.color;

/**
 *
 * @author ACER
 */
public class DAOEvent implements IDAOEvent {

    private List<Event> EventList;
    private final String EVENT_FILE = "events.dat";

    public DAOEvent() {
        EventList = new ArrayList<>();
    }

    @Override
    public List<Event> getAllEvent() {
        return EventList;
    }

    @Override
    public Event getEvent(String EventID) {
        for (Event event : EventList) {
            if (event.getEventID().equalsIgnoreCase(EventID)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public boolean checkExistID(String EventID) {
        if (getEvent(EventID) == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addEvent(Event event) {
        return EventList.add(event);
    }

    @Override
    public boolean deleteEvent(Event event) {
        return EventList.remove(event);
    }

    @Override
    public void getEventByLocation(String location) {
        System.out.printf(color.BLACK + color.CYAN_BACKGROUND + "|%-15s|%-30s|%-30s|%-25s|%-40s|%-20S|%n" + color.RESET, "ID event", "Name", "Date", "Location", "Attendencees", "Status");
        for (Event event : EventList) {
            if (event.getEventLocation().toUpperCase().contains(location.toUpperCase())) {
                System.out.printf(event.toPrint());
            }
        }
    }

    @Override
    public boolean readFile() {
        File file = new File(EVENT_FILE);
        try {
            FileInputStream readData = new FileInputStream(file);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            boolean more = true;
            while (more) {
                Event event = (Event) readStream.readObject();
                if (event != null) {
                    EventList.add(event);
                } else {
                    more = false;
                }
            }
            readStream.close();
            readData.close();
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean writeFile() {
        File file = new File(EVENT_FILE);
        try {
            FileOutputStream writeData = new FileOutputStream(file);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            for (Event event : EventList) {
                writeStream.writeObject(event);
            }
            writeStream.flush();
            writeStream.close();
            writeData.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
