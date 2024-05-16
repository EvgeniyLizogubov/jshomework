package com.github.evgenylizogubov;

import java.util.Arrays;

public class HomeWorkApp {
    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();
        list.print();
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.isEmpty());
        
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.print();
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.isEmpty());
        
        list.add(2, 13);
        list.print();
        
        list.removeByIndex(2);
        list.print();
        
        list.remove(2);
        list.print();
        
        list.removeFirst();
        list.print();
        
        list.removeLast();
        list.print();
        
        System.out.println(list.getFirst());
        System.out.println(list.get(1));
        System.out.println(list.getLast());
        
        list.print();
        System.out.println("Contains 99? - " + list.contains(99));
        System.out.println("Contains 3? - " + list.contains(3));
        
        System.out.println("Index of 4 - " + list.indexOf(4));
        
        list.set(1, 77);
        list.print();
        
        System.out.println("Size - " + list.size());
        
        System.out.println("Is empty? - " + list.isEmpty());
        list.clear();
        System.out.println("Is empty? - " + list.isEmpty());
    }
}
