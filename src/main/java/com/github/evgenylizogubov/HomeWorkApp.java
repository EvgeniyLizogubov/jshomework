package com.github.evgenylizogubov;

import java.util.HashMap;
import java.util.Map;

public class HomeWorkApp {
    public static void main(String[] args) {
        String[] array = {"apple", "banana", "orange", "mango", "watermelon", "cherry", "strawberry",
                "plum", "apricot", "apple", "orange", "cherry"};
        
        printUniqOrWithCountsStrings(array);
        
        System.out.println();
        System.out.println();
        
        Phonebook phonebook = new Phonebook();
        phonebook.add("+7(747)111-11-11", "Иванов");
        phonebook.add("+7(747)222-22-22", "Сидорова");
        phonebook.add("+7(747)333-33-33", "Петров");
        phonebook.add("+7(747)444-44-44", "Иванов");
        phonebook.add("+7(747)555-55-55", "Спиридонов");
        
        System.out.println(phonebook.get("Иванов"));
        System.out.println(phonebook.get("Сидорова"));
        System.out.println(phonebook.get("Задов"));
    }
    
    private static void printUniqOrWithCountsStrings(String[] array) {
        Map<String, Integer> map = new HashMap<>();
        
        for (String str : array) {
            map.put(str, map.containsKey(str) ? map.get(str) + 1 : 1);
        }
        
        System.out.println("Uniq elements:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                System.out.print(entry.getKey() + " ");
            }
        }
        System.out.println();
        System.out.println();
        
        System.out.println("Elements with counts:");
        map.forEach((str, count) -> System.out.print(str + ":" + count + " "));
        System.out.println();
    }
}
