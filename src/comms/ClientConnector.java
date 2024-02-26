/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comms;

import java.io.Serializable;
import java.net.Socket;
import thegamecontroller.objetos.Interlocutor;

/**
 *
 * @author jesus
 */
public class ClientConnector implements Runnable {
    private CommsController tgComunications;
    private Socket SOCKET;

    public ClientConnector(CommsController tgComunications) {
        this.tgComunications = tgComunications;
    }

    @Override
    public void run() {
        while (true) {
            this.SOCKET = new Socket();
            createConnection();
        }
    }

    public void createConnection() {
        if (this.tgComunications.getDownChannels() != null) {
            for (int i = 0; i < this.tgComunications.getDownChannels().size(); i++) {
                try {

                    System.out.println("Conectando como cliente...");
                    this.SOCKET = new Socket(this.tgComunications.getDownChannels().get(i).getInterlocutor().getIP(), 4040);
                    System.out.println("Conexion como cliente realizada con exito");
                    this.tgComunications.getDownChannels().get(i).setSocket(this.SOCKET);
                    this.tgComunications.getChannels().add(this.tgComunications.getDownChannels().get(i));
                    new Thread(this.tgComunications.getChannels().get(i)).start();
                    this.tgComunications.getDownChannels().remove(i);

                } catch (Exception e) {
                
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {

                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }

    // Getters And Setters
    public CommsController getTgComunications() {
        return tgComunications;
    }

    public void setTgComunications(CommsController tgComunications) {
        this.tgComunications = tgComunications;
    }

    public Socket getSOCKET() {
        return SOCKET;
    }

    public void setSOCKET(Socket sOCKET) {
        SOCKET = sOCKET;
    }
}
