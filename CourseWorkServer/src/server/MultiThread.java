package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThread implements Runnable{
    protected int serverPort ;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;

    public MultiThread(int port){
        this.serverPort = port;
    }

    @Override
    public void run(){
        System.out.println("ServerWork is ready.") ;
        openServerSocket();

        while(! isStopped()){
            Socket clientAccepted = null;
            try {
                //serverSocket = new ServerSocket(this.serverPort);
                clientAccepted  = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("ServerWork Stopped.") ;
                    return;
                }
                throw new RuntimeException("Error accepting client connection", e);
            }
            new Thread(new ServerWork(clientAccepted )).start();
        }
        System.out.println("ServerWork Stopped.") ;
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        System.out.println("Opening server socket...");
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
           throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }
    }
}
