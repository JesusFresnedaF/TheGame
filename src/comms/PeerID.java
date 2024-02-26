/*
 */
package comms;

import java.net.Socket;

/**
 *
 * @author jesus
 */
public class PeerID implements Runnable{
    private Socket CLSOCK;
    private ServerConnector serverConection;
    
    public PeerID(ServerConnector serverConection, Socket CLSOCK) {
        this.serverConection = serverConection;
        this.CLSOCK = CLSOCK;
    }

    @Override
    public void run() {
        if(CLSOCK != null){
        
            System.out.println("Direccion de la Conexion: " + CLSOCK.getInetAddress());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
