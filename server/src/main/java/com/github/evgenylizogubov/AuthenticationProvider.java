package com.github.evgenylizogubov;

public interface AuthenticationProvider {
    String getUsernameByLoginAndPassword(String login, String password);
}