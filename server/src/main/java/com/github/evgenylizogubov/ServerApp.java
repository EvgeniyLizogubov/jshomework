package com.github.evgenylizogubov;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 */
public class ServerApp {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запушен на порту 8189. Ожидаем подключение клиента...");
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Клиент подключился");
            
            String msg;
            int msgCount = 0;
            while (true) {
                msg = in.readUTF();
                System.out.print(msg + "\n");
                if (msg.equals("/stat")) {
                    out.writeUTF(msg);
                    out.writeUTF("Количество сообщений - " + msgCount);
                } else {
                    out.writeUTF("ECHO: " + msg);
                    msgCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
