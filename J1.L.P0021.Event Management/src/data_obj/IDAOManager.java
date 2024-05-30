/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_obj;

import utils.IValidation;

/**
 *
 * @author ACER
 */
public interface IDAOManager {

    IValidation validator();

    IDAOEvent event();
}
