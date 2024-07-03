package com.github.evgenylizogubov;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final static Logger LOGGER = LogManager.getLogger(Controller.class);
    
    @FXML
    private TextField msgField, loginField;
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private TextArea msgArea;
    
    @FXML
    private HBox loginBox, msgBox;
    
    @FXML
    ListView<String> clientsList;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String username;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String historyFilePath;
    
    // login: Bob@gmail.com password: 111 username: Bob
    public void setUsername(String username) {
        LOGGER.trace("set username - " + username);
        this.username = username;
        if (this.username == null) {
            loginBox.setVisible(true);
            loginBox.setManaged(true);
            msgBox.setVisible(false);
            msgBox.setManaged(false);
            clientsList.setVisible(false);
            clientsList.setManaged(false);
        } else {
            loginBox.setVisible(false);
            loginBox.setManaged(false);
            msgBox.setVisible(true);
            msgBox.setManaged(true);
            clientsList.setVisible(true);
            clientsList.setManaged(true);
        }
    }
    
    public void connect() {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            new Thread(() -> {
                try {
                    // цикл авторизации
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/login_ok ")) {
                            LOGGER.trace("Login OK");
                            // client -> server /login Bob
                            // server -> client /login_ok Bob
                            // server -> client /login_failed username already in use
                            setUsername(msg.split("\\s+")[1]);
                            historyFilePath = "client/src/main/resources/history/" + username + ".txt";
                            printHistory();
                            writer = new BufferedWriter(new FileWriter(historyFilePath, true));
                            break;
                        }
                        
                        if (msg.startsWith("/login_failed ")) {
                            LOGGER.info("Login failed");
                            String reason = msg.split("\\s+", 2)[1];
                            msgArea.appendText(reason + "\n");
                        }
                    }
                    // цикл общения
                    while (true) {
                        String msg = in.readUTF();
                        if (msg.startsWith("/clients_list ")) {
                            LOGGER.trace("Client list received");
                            Platform.runLater(() -> {
                                clientsList.getItems().clear();
                                String[] tokens = msg.split("\\s+");
                                Arrays.stream(tokens).skip(1).forEach(token -> clientsList.getItems().add(
                                        token.equals(username) ? token + " (You)" : token
                                ));
                            });
                            continue;
                        }
                        
                        if (msg.startsWith("/new_nickname ")) {
                            username = msg.split("\\s+")[1];
                            LOGGER.info("Change username to " + username);
                            continue;
                        }
                        
                        saveHistory(msg);
                        msgArea.appendText(msg + "\n");
                    }
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                } finally {
                    disconnect();
                }
            }).start();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException("Unable to connect to server");
        }
    }
    
    public void login() {
        if (socket == null || socket.isClosed()) {
            LOGGER.trace("Login");
            connect();
        }
        
        if (loginField.getText().isEmpty()) {
            LOGGER.info("Login failed - empty username");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Имя пользователя не может быть пустым", ButtonType.OK);
            alert.showAndWait();
            return;
            
        }
        
        try {
            out.writeUTF("/login " + loginField.getText() + " " + passwordField.getText());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
    
    public void disconnect() {
        LOGGER.trace("Disconnect");
        setUsername(null);
        if (socket != null) {
            try {
                socket.close();
                writer.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }
    
    public void sendMsg() {
        try {
            out.writeUTF(msgField.getText());
            msgField.clear();
        } catch (IOException e) {
            LOGGER.warn("Unable to sent message");
            Alert alert = new Alert(Alert.AlertType.ERROR, "Невозможно отправить сообщение");
            alert.showAndWait();
        }
    }
    
    private void printHistory() {
        if (Files.exists(Path.of(historyFilePath))) {
            LOGGER.trace("Print history");
            try {
                reader = new BufferedReader(new FileReader(historyFilePath));
                reader.lines().forEach(str -> msgArea.appendText(str + "\n"));
                reader.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
    
    private void saveHistory(String msg) {
        try {
            writer.write(msg);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUsername(null);
    }
}
