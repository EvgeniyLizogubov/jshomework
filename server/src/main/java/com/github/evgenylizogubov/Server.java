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
        list.forEach(clientHandler -> clientHandler.sendMessage(msg));
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
        ClientHandler clientHandler = list.stream().filter(ch -> ch.getUsername().equals(username)).findFirst().orElse(null);
        return clientHandler != null;
    }
    
    public void sendPrivateMsg(ClientHandler sender, String receiver, String msg) {
        ClientHandler clientHandler = list.stream().filter(ch -> ch.getUsername().equals(receiver)).findFirst().orElse(null);
        if (clientHandler == null) {
            sender.sendMessage("Unable to send message to " + receiver);
        } else {
            clientHandler.sendMessage("From: " + sender.getUsername() + " Message: " + msg);
            sender.sendMessage("Receiver: " + receiver + " Message: " + msg);
        }
    }
    
    public void sendClientList() {
        LOGGER.trace("Send clients list");
        StringBuilder builder = new StringBuilder("/clients_list ");
        
        list.forEach(ch -> builder.append(ch.getUsername()).append(" "));
        
        builder.setLength(builder.length() - 1);
        String clientList = builder.toString();
        
        list.forEach(ch -> ch.sendMessage(clientList));
    }
    
    public AuthenticationProvider getAuthenticationProvider() {
        return authenticationProvider;
    }
}
