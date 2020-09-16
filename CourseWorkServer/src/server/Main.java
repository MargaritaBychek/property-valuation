package server;
import java.io.*;
import java.net.*;

public class Main {

    public static void main(String[] args) {
        MultiThread server = new MultiThread(8006);
        new Thread(server).start();
   }
}