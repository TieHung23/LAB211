/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

/**
 *
 * @author ACER
 */
public interface IValidate {

    public Object checkIDExist(String ID, Object obj);

    int checkChoice(String msg, int min, int max, String error);

    boolean checkYesorNo(String msg, String error);

    String checkRegex(String msg, String regex, String errorRegex);
}
