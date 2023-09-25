package com.itismeucci.stefanelli;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    protected ServerSocket serverSocket;
    protected Socket clientSocket;
    protected BufferedReader input;
    protected DataOutputStream output;

    public void start() {

        start(0);
    }

    public void start(int port) {

        try {

            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            serverSocket.close();

            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new DataOutputStream(clientSocket.getOutputStream());

        } catch (Exception e) {
            
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void send(String message) {

        try {

            output.writeBytes(message + "\n");
            
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    public String receive() {
        
        try {

            return input.readLine();

        } catch (IOException e) {
            
            e.printStackTrace();
            return "ERROR";
        }
    }
}