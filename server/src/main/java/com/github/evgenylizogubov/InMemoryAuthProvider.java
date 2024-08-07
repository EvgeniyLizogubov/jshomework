package com.github.evgenylizogubov;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryAuthProvider implements AuthenticationProvider {
    private class User {
        private String login;
        private String password;
        private String username;
        
        public User(String login, String password, String username) {
            this.login = login;
            this.password = password;
            this.username = username;
        }
    }
    
    private List<User> list;
    
    public InMemoryAuthProvider() {
        this.list = new ArrayList<>(Arrays.asList(
                new User("Alex@gmail.com", "111", "Alex"),
                new User("Ben@gmail.com", "111", "Ben"),
                new User("John@gmail.com", "111", "John")
        ));
    }
    
    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        User user = list.stream().filter(u -> u.login.equals(login) && u.password.equals(password)).findFirst().orElse(null);
        return user == null ? null : user.username;
    }
    
    @Override
    public boolean changeUsername(String oldUsername, String newUsername) {
        User user = list.stream().filter(u -> u.username.equals(oldUsername)).findFirst().orElse(null);
        if (user == null) {
            return false;
        } else {
            user.username = newUsername;
            return true;
        }
    }
}