/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map_obj;

/**
 *
 * @author ACER
 */
public class Map<K, V> implements IMap<K, V> {

    private Node<K, V> head, tail;

    public Map() {
        head = tail = null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void add(K key, V value) {
        if (isEmpty()) {
            head = tail = new Node<>(key, value);
        } else {
            Node<K, V> newNode = new Node<>(key, value);
            tail.next = newNode;
            tail = newNode;
        }
    }

    @Override
    public V search(K key) {
        if (isEmpty()) {
            return null;
        }
        Node<K, V> curentNode = head;
        while (curentNode != null) {
            if (curentNode.getKey().equals(key)) {
                return curentNode.getValue();
            }
            curentNode = curentNode.next;
        }
        return null;
    }

    @Override
    public V[] arrOfNode() {
        Node<K, V> currentNode = head;
        int cnt = 0;
        while (currentNode != null) {
            cnt++;
            currentNode = currentNode.next;
        }
        V[] arr = (V[]) new Object[cnt];
        currentNode = head;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = currentNode.getValue();
            currentNode = currentNode.next;
        }
        return arr;
    }

    @Override
    public V searchByIndex(int note) {
        Node<K, V> currentNode = head;
        int cnt = 0;
        while (cnt != note) {
            currentNode = currentNode.next;
            cnt++;
        }
        return currentNode.getValue();
    }
}
