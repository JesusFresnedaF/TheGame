/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package localgame;

import thegamecontroller.objetos.Bola;
import thegamecontroller.dtos.CoordinatesDTO;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author jesus
 */
public class LocalViewer extends JFrame implements MouseListener, Runnable {

    private LocalController controller;
    private ControlPanel panel;
    private Viewer canvas;

    private volatile List<Bola> bolas;
    private volatile List<Thread> thBolas;
    private Dimension dimensionFrame;

    public LocalViewer(LocalController controller, Dimension dimension) {
        this.controller = controller;
        initAtributos(dimension);
        addPanel();
        configureFrame();
        addOnClick();
    }

    //inicializo los atributos de la ventana
    public void initAtributos(Dimension dimension) {
        this.dimensionFrame = dimension;
        this.bolas = Collections.synchronizedList(new ArrayList<>());
        this.thBolas = Collections.synchronizedList(new ArrayList<>());
    }

    public void addOnClick() {
        this.canvas.addMouseListener(this);
    }

    //configuramos la pantalla
    public void configureFrame() {
        setTitle("Bolas 2");
        setSize(this.dimensionFrame);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    //configuro el panel
    public void addPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        canvas = new Viewer(this.dimensionFrame);
        this.add(canvas, gbc);
    }

    public List<Bola> getBolas() {
        return this.canvas.getBolas();
    }

    //elimino una bola del arraylist
    public void removeBola(Bola b) {
        bolas.remove(b);
        canvas.setBolas(bolas);
    }

    //compruebo si se ha modificado el tamaño de la ventana
    public void checkFrameDimension() {
        if (this.getHeight() != dimensionFrame.getHeight() || this.getWidth() != dimensionFrame.getWidth()) {
            dimensionFrame = new Dimension(this.getWidth(), this.getHeight());
            canvas.changeFrameDimension(dimensionFrame);
            controller.changeFrameDimension(dimensionFrame);
        }
    }

    //creo una nueva bola cada vez que hago click
    @Override
    public void mouseClicked(MouseEvent e) {
        CoordinatesDTO newPosition = new CoordinatesDTO(e.getX(), e.getY());
        bolas = controller.addBola(newPosition);
        thBolas.add(new Thread(bolas.get(bolas.size() - 1)));
        thBolas.get(thBolas.size() - 1).start();
        canvas.setBolas(bolas);
        canvas.paint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.bolas = this.controller.getBolas();
                this.canvas.setBolas(bolas);
                this.canvas.paint();
                checkFrameDimension();
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(LocalViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addBola(Bola bola) {
        bolas.add(bola);
        thBolas.add(new Thread(bolas.get(bolas.size() - 1)));
        thBolas.get(thBolas.size() - 1).start();
        canvas.setBolas(bolas);
        canvas.paint();
    }

}
