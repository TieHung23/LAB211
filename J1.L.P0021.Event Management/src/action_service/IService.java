/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action_service;

import event_obj.Event;

/**
 *
 * @author ACER
 */
public interface IService {

    void showAllEvent();

    void addEvent();

    Event searchByID();

    void searchByLocation();

    void updateEvent();

    void deleteEvent();
}
