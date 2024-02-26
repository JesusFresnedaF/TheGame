/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package localgame;

import thegamecontroller.objetos.Bola;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jesus
 */
public class Viewer extends Canvas{

    private Dimension frameDimension;
    ArrayList<Bola> bolas;

    public Viewer(Dimension defaultDimension) {
        frameDimension = defaultDimension;
        this.bolas = new ArrayList<>();
        setSize(frameDimension);
    }

    public void setBolas(ArrayList<Bola> bolas) {
        this.bolas = bolas;
    }

    public ArrayList<Bola> getBolas() {
        return bolas;
    }

    //pinto las bolas en la pantalla
    public void paint() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null) {
            this.createBufferStrategy(3); // Crea tres buffers para la estrategia
            return;
        }

        Graphics g = bufferStrategy.getDrawGraphics();

        // Limpia la pantalla
        g.clearRect(0, 0, getWidth(), getHeight());

        // Dibuja las bolas
        Iterator<Bola> iterador = bolas.iterator();
        while (iterador.hasNext()) {
            Bola bola = iterador.next();
            bola.pintar(g);
        }

        // Muestra los gráficos
        bufferStrategy.show();

        // Libera los recursos gráficos
        g.dispose();
    }

    public void changeFrameDimension(Dimension dimensionFrame) {
        this.setSize(dimensionFrame);
    }

}
