/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map_obj;

import libary_obj.Books;

/**
 *
 * @author ACER
 */
public interface IMap<K, V> {

    boolean isEmpty();

    void add(K key, V value);

    V search(K key);
    
    V[] arrOfNode();
    
    V searchByIndex(int note);
}
