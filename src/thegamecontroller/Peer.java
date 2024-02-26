/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thegamecontroller;

import thegamecontroller.dtos.PeerLocation;
import thegamecontroller.objetos.Interlocutor;

/**
 *
 * @author jesus
 */
public class Peer extends Interlocutor{
    
    private PeerLocation location;
    
    
    public Peer(String id, int port, PeerLocation location){
        super(id, port);
        this.location = location;
    }

    public PeerLocation getLocation() {
        return location;
    }

    public void setLocation(PeerLocation location) {
        this.location = location;
    }

}
