package com.github.evgenylizogubov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private final static Logger LOGGER = LogManager.getLogger(ClientHandler.class);
    
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
                        LOGGER.trace(msg);
                        // /login Bob@gmail.com 111
                        String[] tokens = msg.split("\\s+");
                        if (tokens.length != 3) {
                            LOGGER.info("Incorrect command");
                            sendMessage("Server: Incorrect command");
                            continue;
                        }
                        
                        String login = tokens[1];
                        String password = tokens[2];
                        String nick = server.getAuthenticationProvider()
                                .getUsernameByLoginAndPassword(login, password);
                        if (nick == null) {
                            LOGGER.info("Incorrect login/password");
                            sendMessage("/login_failed Incorrect login/password");
                            continue;
                        }
                        
                        if (server.isUserOnline(nick)) {
                            LOGGER.info(nick + " - username is already in use");
                            sendMessage("/login_failed this username is already in use");
                            continue;
                        }
                        
                        LOGGER.trace("Login OK");
                        username = nick;
                        sendMessage("/login_ok " + username);
                        server.subscribe(this);
                        break;
                    }
                }
                
                while (true) {
                    String msg = in.readUTF();
                    // /p Bob Hello
                    if (msg.startsWith("/")) {
                        executeCmd(msg);
                        continue;
                    }
                    
                    server.broadcastMessage(username + ": " + msg);
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            } finally {
                disconnect();
            }
        }).start();
    }
    
    public void executeCmd(String msg) throws IOException {
        LOGGER.trace(msg);
        if (msg.startsWith("/p ")) {
            String[] tokens = msg.split("\\s+", 3);
            server.sendPrivateMsg(this, tokens[1], tokens[2]);
        }
        
        if (msg.startsWith("/change_nick ")) {
            sendMessage(msg);
            String[] tokens = msg.split("\\s+", 3);
            String newUsername = tokens[1];
            String oldUsername = tokens[2];
            if (!oldUsername.equals(username)) {
                LOGGER.info("Change nickname failed");
                sendMessage("You can only change your nickname.");
                return;
            }
            
            if (server.getAuthenticationProvider().changeUsername(oldUsername, newUsername)) {
                LOGGER.trace("{} changed his nickname to {}", username, newUsername);
                server.broadcastMessage(username + " changed his nickname to " + newUsername);
                username = newUsername;
                sendMessage("/new_nickname " + newUsername);
                server.sendClientList();
            }
        }
    }
    
    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            disconnect();
        }
    }
    
    public void disconnect() {
        LOGGER.info("Disconnect");
        server.unsubscribe(this);
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
    
    public String getUsername() {
        return username;
    }
}
