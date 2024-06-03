package com.github.evgenylizogubov;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private static final String WHO_AM_I_COMMAND = "/who_am_i";
    private static final String PRIVATE_MESSAGE_COMMAND = "/p";
    
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private String username;
    
    
    public ClientHandler(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        
        new Thread(() -> {
            try {
                while (true) {
                    String msg = in.readUTF();
                    if (msg.startsWith("/login ")) {
                        String username = msg.split("\\s+")[1];
                        if (server.isUniqUsername(username)) {
                            this.username = username;
                            sendMessage("/login_ok " + username);
                            break;
                        } else {
                            sendMessage("/login_failed " + username);
                        }
                    }
                    
                }
                
                while (true) {
                    String msg = in.readUTF();
                    if (msg.equals(WHO_AM_I_COMMAND)) {
                        sendMessage(msg + "\n" + username);
                    } else if (msg.startsWith(PRIVATE_MESSAGE_COMMAND)) {
                        String recipient = msg.split(" ", 3)[1];
                        String text = msg.split(" ", 3)[2];
                        if (server.privateMessage(recipient, "from " + username + ": " + text)) {
                            sendMessage("to " + recipient + ": " + text);
                        }
                    } else {
                        server.broadcastMessage(username + ": " + msg);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                disconnect();
            }
        }).start();
        
    }
    
    public void sendMessage(String msg) throws IOException {
        out.writeUTF(msg);
    }
    
    public void disconnect() {
        server.unsubscribe(this);
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getUsername() {
        return username;
    }
}
