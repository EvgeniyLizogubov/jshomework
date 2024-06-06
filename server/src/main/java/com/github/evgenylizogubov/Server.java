package com.github.evgenylizogubov;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private int port;
    private List<ClientHandler> list;
    
    public Server(int port) {
        this.port = port;
        this.list = new ArrayList<>();
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен на порту 8189. Ожидаем подключение клиента...");
            while (true) {
                Socket socket = serverSocket.accept();
                subscribe(new ClientHandler(this, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void broadcastMessage(String msg) throws IOException {
        for (ClientHandler clientHandler : list) {
            clientHandler.sendMessage(msg);
        }
    }
    
    public boolean privateMessage(String recipient, String text) throws IOException {
        for (ClientHandler clientHandler : list) {
            if (recipient.equals(clientHandler.getUsername())) {
                clientHandler.sendMessage(text);
                return true;
            }
        }
        
        return false;
    }
    
    public void subscribe(ClientHandler clientHandler) {
        list.add(clientHandler);
    }
    
    public void unsubscribe(ClientHandler clientHandler) {
        list.remove(clientHandler);
    }
    
    public boolean isUniqUsername(String username) {
        for (ClientHandler clientHandler : list) {
            if (username.equals(clientHandler.getUsername())) {
                return false;
            }
        }
        return true;
    }
}
