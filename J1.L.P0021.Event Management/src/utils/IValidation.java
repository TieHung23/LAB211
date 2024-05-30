/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import event_obj.StatusEvent;
import java.time.LocalDate;

/**
 *
 * @author ACER
 */
public interface IValidation {

    String checkString(String msg, Status status);

    int checkInt(String msg, int min, int max, Status status);

    LocalDate checkDate(String msg, Status status);

    boolean checkYesorNo(String msg);

    String checkRegex(String msg, String regex);

    StatusEvent checkEventTus(String msg, Status status);

}
