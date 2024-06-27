package com.github.evgenylizogubov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final static Logger LOGGER = LogManager.getLogger(Server.class);
    
    private int port;
    private List<ClientHandler> list;
    private AuthenticationProvider authenticationProvider;
    
    public Server(int port) {
        this.port = port;
        this.list = new ArrayList<>();
        this.authenticationProvider = new InMemoryAuthProvider();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            LOGGER.trace("The server is running on port 8189. Waiting for the client to connect...");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(this, socket);
            }
            
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    
    public void broadcastMessage(String msg) {
        for (ClientHandler clientHandler : list) {
            clientHandler.sendMessage(msg);
        }
    }
    
    public void subscribe(ClientHandler clientHandler) {
        list.add(clientHandler);
        sendClientList();
    }
    
    public void unsubscribe(ClientHandler clientHandler) {
        list.remove(clientHandler);
        sendClientList();
    }
    
    public boolean isUserOnline(String username) {
        LOGGER.trace("Check user is online");
        for (ClientHandler clientHandler : list) {
            if (clientHandler.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    
    public void sendPrivateMsg(ClientHandler sender, String receiver, String msg) {
        for (ClientHandler c : list) {
            if (c.getUsername().equals(receiver)) {
                c.sendMessage("From: " + sender.getUsername() + " Message: " + msg);
                sender.sendMessage("Receiver: " + receiver + " Message: " + msg);
                return;
            }
        }
        sender.sendMessage("Unable to send message to " + receiver);
    }
    
    public void sendClientList() {
        LOGGER.trace("Send clients list");
        StringBuilder builder = new StringBuilder("/clients_list ");
        for (ClientHandler c : list) {
            builder.append(c.getUsername()).append(" ");
        }
        builder.setLength(builder.length() - 1);
        // /clients_list Bob Alex John
        String clientList = builder.toString();
        for (ClientHandler c : list) {
            c.sendMessage(clientList);
        }
    }
    
    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }
}
