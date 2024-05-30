/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package event_obj;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author ACER
 */
public class Event implements Serializable {

    private String EventID, EventName;
    private LocalDate date;
    private String EventLocation;
    private int EventAttendees;
    private StatusEvent status;

    public String getEventID() {
        return EventID;
    }

    public String getEventName() {
        return EventName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getEventLocation() {
        return EventLocation;
    }

    public int getEventAttendees() {
        return EventAttendees;
    }

    public StatusEvent getStatus() {
        return status;
    }

    public Event(String EventID, String EventName, LocalDate date, String EventLocation, int EventAttendees, StatusEvent status) {
        this.EventID = EventID;
        this.EventName = EventName;
        this.date = date;
        this.EventLocation = EventLocation;
        this.EventAttendees = EventAttendees;
        this.status = status;
    }

    public boolean setEventName(String EventName) {
        if (!EventName.isEmpty()) {
            this.EventName = EventName;
            return true;
        }
        return false;
    }

    public boolean setDate(LocalDate date) {
        if (date != null) {
            this.date = date;
            return true;
        }
        return false;
    }

    public boolean setEventLocation(String EventLocation) {
        if (!EventLocation.isEmpty()) {
            this.EventLocation = EventLocation;
            return true;
        }
        return false;
    }

    public boolean setEventAttendees(int EventAttendees) {
        if (EventAttendees != -1) {
            this.EventAttendees = EventAttendees;
            return true;
        }
        return false;
    }

    public boolean setStatus(StatusEvent status) {
        if (status != null) {
            this.status = status;
            return true;
        }
        return false;
    }

    public String dateToString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return getDate().format(dtf);
    }

    @Override
    public String toString() {
        return "Event{" + "EventID=" + EventID + ", EventName=" + EventName + ", date=" + date + ", EventLocation=" + EventLocation + ", EventAttendees=" + EventAttendees + ", status=" + status + '}';
    }

    public String toPrint() {
        String line = String.format("|%-15s|%-30s|%-30s|%-25s|%-40s|%-20S|%n", getEventID(), getEventName(), dateToString(), getEventLocation(), getEventAttendees(), getStatus());
        return line;
    }
}
