/*
    Clase interlocutor
 */
package thegamecontroller.objetos;

import java.io.Serializable;

/**
 *
 * @author jesus
 */
public class Interlocutor {
    private String IP = "localhost";
    private int port;
    
    public Interlocutor(String IP, int port) {
        this.IP = IP;
        this.port = port;
    }
    
    public String getIP() {
        return IP;
    }
    
    public int getPort(){
        return port;
    }
}
