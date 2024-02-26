/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package localgame;

import thegamecontroller.objetos.Bola;
import thegamecontroller.dtos.CoordinatesDTO;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import thegamecontroller.TheGamePController;

/**
 *
 * @author jesus
 */
public class LocalController{

    private TheGamePController tgpController;
    private LocalViewer tgv;
    private LocalModel tgm;
    private GameRules rules;

    private ArrayList<Bola> bolas;

    private Dimension defaultFrameDimension = new Dimension(900, 800);
    private Dimension frameDimension = defaultFrameDimension;

    public LocalController(TheGamePController tgpController) {
        this.bolas = new ArrayList<>();
        this.tgpController = tgpController;
        this.rules = new GameRules(this.frameDimension);
        this.tgv = new LocalViewer(this, this.frameDimension);
        this.tgm = new LocalModel(this, this.frameDimension);
        paint();
    }

    public void paint() {
        Thread hiloTGV = new Thread(tgv);
        hiloTGV.start();
    }

    //añado una bola
    public ArrayList<Bola> addBola(CoordinatesDTO newPosition) {
        bolas = tgm.addBola(newPosition);
        return bolas;
    }

    //añado una bola
    public ArrayList<Bola> addBola(Bola bola) {
        bolas = tgm.addBola(bola);
        tgv.addBola(bola);
        return bolas;
    }

    //compruebo las normas de colisión
    public ArrayList<Boolean> collideDetector(ArrayList<Bola> bolas, CoordinatesDTO newPosition, Bola bola) {
        ArrayList<Boolean> choques = rules.collideDetector(bolas, newPosition, bola);
        return choques;
    }
    
    public ArrayList<Bola> getBolas() {
        return this.bolas;
    }

    //elimino una bola del arraylist
    public void removeBola(Bola b) {
        this.bolas.remove(b);
        tgv.removeBola(b);
    }

    //redimensiono la ventana
    public void changeFrameDimension(Dimension dimensionFrame) {
        frameDimension = dimensionFrame;
        tgm.changeFrameDimension(frameDimension);
        rules.changeFrameDimension(frameDimension);
    }

    //mando una bola
    public void sendBola(Bola b) {
        tgpController.sendObject(b);
    }

}
