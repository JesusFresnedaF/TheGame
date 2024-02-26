/*
    Controlador principal
 */
package thegamecontroller;

import localgame.LocalController;
import comms.CommsController;
import java.io.Serializable;
import java.util.ArrayList;
import thegamecontroller.dtos.*;
import thegamecontroller.objetos.*;

/**
 *
 * @author jesus
 */
public class TheGamePController {

    private LocalController tgController;
    private CommsController comController;
    private ArrayList<Interlocutor> peerInterlocutors = new ArrayList();

    public TheGamePController() {
        init();
    }

    //envio un objeto al servidor
    public void sendObject(Object obj) {
        this.comController.recieveObject(obj);
    }

    //recibo un objeto del servidor
    public void recieveObject(Object obj) {
        if (obj instanceof Bola) {
            Bola b = (Bola) obj;
            b.setVida(100);
            b.setState(VODState.ALIVE);
            tgController.addBola(b);
        }
    }

    public void init() {
        int serverPort = 4000;
        int clientPort = 4040;
        this.peerInterlocutors.add(new Interlocutor("localhost", serverPort));
        this.peerInterlocutors.add(new Interlocutor("localhost", clientPort));
        this.tgController = new LocalController(this);
        this.comController = new CommsController(this, serverPort);
    }

    public void loadConfiguration(String fileName) {

    }

    public ArrayList<Interlocutor> createInterlocutors() {
        ArrayList<Interlocutor> interlocutores = new ArrayList<>();
        return interlocutores;
    }

    public void manageAppFrame(AppFrameDTO appframe) {

    }

    public ArrayList<Interlocutor> getPeerInterlocutors() {
        return peerInterlocutors;
    }

    public void setPeerInterlocutors(ArrayList<Interlocutor> peerInterlocutors) {
        this.peerInterlocutors = peerInterlocutors;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        TheGamePController tgc = new TheGamePController();
    }
}
