package model.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class Client {
    private Socket clientSocket;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private String message;

    public Client() {
        try {
            clientSocket = new Socket("127.0.0.1", 9006);
            outStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMarker(String message) {
        try {
            outStream.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendObject(Object object) {
        try {
            outStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readMarker() throws IOException {
        try {
            message = (String) inStream.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public Object readObject() {
        Object object = new Object();
        try {
            object = inStream.readObject();
        } catch (ClassNotFoundException | IOException e) {

            e.printStackTrace();
        }
        return object;
    }

//    public void close() {
//        try {
//            clientSocket.close();
//            inStream.close();
//            outStream.close();
//        } catch (EOFException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
