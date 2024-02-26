/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package localgame;

import thegamecontroller.objetos.Bola;
import thegamecontroller.dtos.VelocityDTO;
import thegamecontroller.dtos.AcelerationDTO;
import thegamecontroller.dtos.CoordinatesDTO;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jesus
 */
public class LocalModel {

    private LocalController controller;
    private volatile ArrayList<Bola> bolas;

    private Dimension dimensionFrame;

    public LocalModel(LocalController controller, Dimension dimensionFrame) {
        this.dimensionFrame = dimensionFrame;
        this.controller = controller;
        this.bolas = new ArrayList<>();
    }

    //calculo la siguiente posición de la bola
    public CoordinatesDTO calcNewPositions(Bola bola) {
        float time = (float) 0.4;
        VelocityDTO velocidad = bola.getVelocidad();
        AcelerationDTO aceleracion = bola.getAceleracion();
        CoordinatesDTO prevPosicion = bola.getPosicion();

        // Cálculo de la nueva velocidad en cada eje
        if (velocidad.getVelocidadX() == VelocityDTO.MAX_VEL || velocidad.getVelocidadX() == VelocityDTO.MIN_VEL) {
            aceleracion.setAceleracionX(1);
        }

        if (velocidad.getVelocidadY() == VelocityDTO.MAX_VEL || velocidad.getVelocidadY() == VelocityDTO.MIN_VEL) {
            aceleracion.setAceleracionY(1);
        }

        velocidad.setVelocidadX((int) (velocidad.getVelocidadX() + aceleracion.getAceleracionX() * time));
        velocidad.setVelocidadY((int) (velocidad.getVelocidadY() + aceleracion.getAceleracionY() * time));

        // Cálculo de la nueva posición en cada eje
        int nuevaPosX = (int) (prevPosicion.getX() + velocidad.getVelocidadX() * time);
        int nuevaPosY = (int) (prevPosicion.getY() + velocidad.getVelocidadY() * time);

        // Crear un nuevo objeto CoordinatesDTO con las nuevas coordenadas
        CoordinatesDTO nuevaPosicion = new CoordinatesDTO(nuevaPosX, nuevaPosY);

        //compruebo los posibles casos de collide
        ArrayList<Boolean> collide = controller.collideDetector(bolas, nuevaPosicion, bola);

        //colisión horizontal
        //mandar bola
        if (collide.get(0)) {
            bola.sendBola();
        }
        //rebotar horizontal
        if (collide.get(1)) {
            bola.cambiarColor();
            bola.rebotarX();
        }

        //colisión vertical
        if (collide.get(2)) {
            bola.rebotarY();
        }

        //colisión con bola
        if (collide.get(3)) {
            Bola bolaImpacto = getBolaImpacto(bola);
            bola.damage(bolaImpacto);
        }

        killBolas();
        return nuevaPosicion;
    }

    //obtengo la bola con la que choca la bola actual
    public Bola getBolaImpacto(Bola bolaActual) {
        Bola bolaImpacto = new Bola(this);
        Iterator<Bola> iterator = bolas.iterator();
        boolean bolaEncontrada = false;
        while (iterator.hasNext() && !bolaEncontrada) {
            Bola b = iterator.next();
            if (b != bolaActual && bolaActual.seSuperponeCon(b)) {
                bolaImpacto = b;
                bolaEncontrada = true;
            }
        }

        return bolaImpacto;
    }

    public void setBolas(ArrayList<Bola> bolas) {
        this.bolas = bolas;
    }

    //añado una bola
    public ArrayList<Bola> addBola(CoordinatesDTO newPositionDTO) {
        Bola b = new Bola(this);
        b.setPosicion(newPositionDTO);
        bolas.add(b);
        return bolas;
    }

    //añado una bola
    public ArrayList<Bola> addBola(Bola bola) {
        if (bola.getModel() == null) {
            bola.setModel(this);
        }
        bolas.add(bola);
        return bolas;
    }

    //compruebo si debo eliminar una bola del arraylist
    public void killBolas() {
        Iterator<Bola> iterador = bolas.iterator();
        while (iterador.hasNext()) {
            Bola b = iterador.next();
            if (!b.isViva()) {
                iterador.remove();
                controller.removeBola(b);
            }
        }

    }

    //modifico el tamaño de la ventana
    public void changeFrameDimension(Dimension frameDimension) {
        this.dimensionFrame = frameDimension;
    }

    public void sendBola(Bola b) {
        this.controller.sendBola(b);
    }
}
