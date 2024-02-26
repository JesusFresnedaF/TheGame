/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comms;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jesus
 */
public class ServerConnector implements Runnable {

    private CommsController tgComunications;
    private ServerSocket SOCKET;
    private Socket CLSOCK;
    private int PORT;

    public ServerConnector(CommsController tgComunications, int port) {
        this.tgComunications = tgComunications;
        this.PORT = port;

        try {
            this.SOCKET = new ServerSocket(this.PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Servidor escuchando en: " + this.PORT);
                createConnection();
                Thread.sleep(10000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void createConnection() {
        try {

            System.out.println("Conectando como servidor...");
            this.CLSOCK = SOCKET.accept();
            this.tgComunications.getDownChannels().get(0).setSocket(CLSOCK);
            this.tgComunications.getChannels().add(this.tgComunications.getDownChannels().get(0));
            this.tgComunications.getDownChannels().remove(0);
            new Thread(new PeerID(this, this.CLSOCK)).start();
        } catch (Exception e) {

            System.out.println("ServerConnector error: " + e);
        }
    }

    // Getters And Setters
    public boolean isSocketClosed() {
        return SOCKET.isClosed();
    }

    public CommsController getTgComunications() {
        return tgComunications;
    }

    public void setTgComunications(CommsController tgComunications) {
        this.tgComunications = tgComunications;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int port) {
        this.PORT = port;
    }

    public ServerSocket getSOCKET() {
        return SOCKET;
    }

    public void setSOCKET(ServerSocket socket) {
        this.SOCKET = socket;
    }

    public Socket getCLSOCK() {
        return CLSOCK;
    }

    public void setCLSOCK(Socket clsock) {
        this.CLSOCK = clsock;
    }
}
