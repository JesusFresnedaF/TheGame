/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comms;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import thegamecontroller.TheGamePController;
import thegamecontroller.objetos.*;

/**
 *
 * @author jesus
 */
public class CommsController {

    private ArrayList<Channel> downChannels = new ArrayList<>();
    private ArrayList<Channel> channels = new ArrayList<>();
    private ClientConnector clientConection;
    private ServerConnector serverConection;
    
    private TheGamePController tgController;

    public CommsController(TheGamePController tgController, int port) {
        this.tgController = tgController;
        this.clientConection = new ClientConnector(this);
        this.serverConection = new ServerConnector(this, port);
        
        new Thread(this.clientConection).start();
        new Thread(this.serverConection).start();
        
        createChannels();
    }

    private synchronized void createChannels() {
        for (int i = 0; i < this.tgController.getPeerInterlocutors().size(); i++) {
            this.downChannels.add(new Channel(this, this.tgController.getPeerInterlocutors().get(i)));
        }
    }

    // Getters And Setters
    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    public ClientConnector getClientConection() {
        return clientConection;
    }

    public void setClientConection(ClientConnector clientConection) {
        this.clientConection = clientConection;
    }

    public ServerConnector getServerConection() {
        return serverConection;
    }

    public void setServerConection(ServerConnector serverConection) {
        this.serverConection = serverConection;
    }

    public TheGamePController getTgController() {
        return tgController;
    }

    public void setTgController(TheGamePController tgController) {
        this.tgController = tgController;
    }

    public ArrayList<Channel> getDownChannels() {
        return downChannels;
    }

    public void setDownChannels(ArrayList<Channel> downChannels) {
        this.downChannels = downChannels;
    }

    public void recieveObject(Object obj) {
        System.out.println("Channels size: "+channels.size());
        this.channels.get(0).writeDataFrame(obj);
    }

    void sendObject(Bola b) {
        tgController.recieveObject(b);
    }
}
