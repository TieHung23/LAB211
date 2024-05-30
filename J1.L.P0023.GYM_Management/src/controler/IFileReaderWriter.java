/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public interface IFileReaderWriter {

    void readFile();

    void addElementFromFile(String line, Object obj, ArrayList array);
    
    void saveFile();
}
