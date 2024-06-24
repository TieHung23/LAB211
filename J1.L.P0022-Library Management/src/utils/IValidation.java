/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import libary_obj.Status;

/**
 *
 * @author ACER
 */
public interface IValidation {

    String checkString(String msg, statusInput stInput);

    int checkInt(String msg, int min, int max, statusInput stInput);

    boolean checkYoN(String msg);

    LocalDate checkDate(String msg, statusInput stInput);

    String checkRegex(String msg, String regex, statusInput stInput);

    Status status(String msg, statusInput stInput);
}
