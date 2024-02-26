/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comms;

import java.io.Serializable;

/**
 *
 * @author jesus
 */
public class Message implements Serializable{
        //ATRIBUTOS
    String message;
    String sender;
    
    
    
    //METODOS CONSTRUCTORES
    public Message(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }
    
    public Message(String message) {
        this.message = message;
        this.sender = "unknown";
    }
    
    
    //METODOS 
     public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public static Message ping() {
        return new Message("PING", "PING");
    }

    public boolean isPing() {
        return message.equals("PING") && sender.equals("PING");
    }

    @Override
    public String toString() {
        return "message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
