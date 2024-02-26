/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comms;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import thegamecontroller.dtos.AppFrameDTO;
import thegamecontroller.dtos.AppFrameType;
import static thegamecontroller.dtos.AppFrameType.BALL;
import thegamecontroller.dtos.DataFrameDTO;
import thegamecontroller.dtos.DataFrameType;
import thegamecontroller.objetos.*;

/**
 *
 * @author jesus
 */
public class Channel implements Runnable {

    private CommsController tgComunications;
    private HealthChecker testChanel;

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket SOCKET;

    private int sendTime;
    private long recievedTime;

    private Interlocutor interlocutor;
    private boolean isClient;

    public Channel(CommsController tgComunications, Interlocutor interlocutor) {

        this.tgComunications = tgComunications;
        this.interlocutor = interlocutor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Leer mensajes entrantes
                if (SOCKET != null && SOCKET.isConnected()) {
                    this.dataIn();
                } else if (SOCKET != null) {
                    this.setDownChannel();
                    System.out.println("Conexión perdida, intentando reconectar...");
                    Thread.sleep(5000);
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

    public void setSocket(Socket SOCKET) {
        try {
            this.SOCKET = SOCKET;
            // Inicializar los objetos BufferedReader y PrintWriter
            OutputStream os = SOCKET.getOutputStream();
            this.out = new ObjectOutputStream(os);
            InputStream is = SOCKET.getInputStream();
            this.in = new ObjectInputStream(is);

            // Una vez se ha creado el socket creamos el test channel para asegurar que siga funcionando
            this.testChanel = new HealthChecker(this, 10000);
            new Thread(this.testChanel).start();
        } catch (IOException e) {

            System.out.println(e);
        }
    }

    public synchronized void setDownChannel() {
        // Si detectamos que la conexion no funciona como queremos eliminamos el socket 
        try {

            stopTestChannel();
            in.close();
            out.close();
            SOCKET.close();

        } catch (IOException e) {

            e.printStackTrace();
        } finally {

            SOCKET = null;
            System.err.println("Matando el socket...");
        }
    }

    // Metodo para recibir informacion
    public void dataIn() {
        try {
            DataFrameDTO data = (DataFrameDTO) in.readObject();
            if (data != null) {
                switch (data.getType()) {
                    case KEEP_ALIVE:
                        System.out.println("ping recibido...");
                        sendPingACK();
                        break;
                    case KEEP_ALIVE_ACK:
                        System.out.println("ping ACK recibido...");
                        recievedTime = System.currentTimeMillis();
                    case APLICATION_FRAME:
                        AppFrameDTO app = (AppFrameDTO) data.getObject();
                        switch (app.getType()) {
                            case BALL:
                                System.out.println("Bola recibida...");
                                Bola b = (Bola) app.getObject();
                                tgComunications.sendObject(b);
                                break;
                        }
                        break;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // Parar el hilo del test channel
    public void stopTestChannel() {
        if (testChanel != null) {
            testChanel.pararEjecucion();
            testChanel = null;
        }
    }

    // Getters And Setters
    public boolean isOk() {
        return false;
    }

    public CommsController getTgComunications() {
        return tgComunications;
    }

    public void setTgComunications(CommsController tgComunications) {
        this.tgComunications = tgComunications;
    }

    public HealthChecker getTestChanel() {
        return testChanel;
    }

    public void setTestChanel(HealthChecker testChanel) {
        this.testChanel = testChanel;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public Socket getSOCKET() {
        return SOCKET;
    }

    public int getSendTime() {
        return sendTime;
    }

    public void setSendTime(int sendTime) {
        this.sendTime = sendTime;
    }

    public long getRecievedTime() {
        return recievedTime;
    }

    public void setRecievedTime(long recievedTime) {
        this.recievedTime = recievedTime;
    }

    public Interlocutor getInterlocutor() {
        return interlocutor;
    }

    public void setInterlocutor(Interlocutor interlocutor) {
        this.interlocutor = interlocutor;
    }

    public boolean isClient() {
        return isClient;
    }

    public void setIsClient(boolean wasClient) {
        this.isClient = wasClient;
    }

    public void writeDataFrame(Object obj) {
        if (obj instanceof Bola) {
            try {
                Bola b = (Bola) obj;
                AppFrameDTO app = new AppFrameDTO(b, AppFrameType.BALL);
                DataFrameDTO data = new DataFrameDTO(app, DataFrameType.APLICATION_FRAME);
                out.writeObject(data);
                out.flush();
            } catch (IOException ex) {
                Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendPing() {
        try {
            DataFrameDTO data = new DataFrameDTO("ping", DataFrameType.KEEP_ALIVE);
            out.writeObject(data);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendPingACK() {
        try {
            DataFrameDTO data = new DataFrameDTO("ping_ack", DataFrameType.KEEP_ALIVE_ACK);
            out.writeObject(data);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Channel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
