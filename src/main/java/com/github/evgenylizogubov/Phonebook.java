package com.github.evgenylizogubov;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Phonebook {
    private final Map<String, Set<String>> phonebook = new HashMap<>();
    
    public void add(String surname, String phoneNumber) {
        Set<String> value = phonebook.get(surname);
        if (value == null) {
            value = new HashSet<>();
        }
        value.add(phoneNumber);
        phonebook.put(surname, value);
    }
    
    public Set<String> get(String surname) {
        return phonebook.get(surname);
    }
}
