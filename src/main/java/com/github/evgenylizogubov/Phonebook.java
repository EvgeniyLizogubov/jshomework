package com.github.evgenylizogubov;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Phonebook {
    private Map<String, String> phonebook = new HashMap<>();
    
    public void add(String phoneNumber, String surname) {
        phonebook.put(phoneNumber, surname);
    }
    
    public Set<String> get(String surname) {
        Set<String> result = new HashSet<>();
        
        for (Map.Entry<String, String> entry : phonebook.entrySet()) {
            if (entry.getValue().equals(surname)) {
                result.add(entry.getKey());
            }
        }
        
        return result;
    }
}
