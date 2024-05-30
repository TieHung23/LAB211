/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_obj;

import event_obj.Event;
import java.util.List;

/**
 *
 * @author ACER
 */
public interface IDAOEvent {

    List<Event> getAllEvent();

    Event getEvent(String EventID);

    boolean checkExistID(String EventID);

    boolean addEvent(Event event);

    boolean deleteEvent(Event event);
    
    void getEventByLocation(String location);
    
    boolean readFile();
    
    boolean writeFile();
}
