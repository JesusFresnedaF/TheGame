/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package localgame;

import thegamecontroller.objetos.Wall;
import thegamecontroller.objetos.Bola;
import thegamecontroller.objetos.Gate;
import thegamecontroller.dtos.CoordinatesDTO;
import thegamecontroller.dtos.GateState;
import thegamecontroller.dtos.WallLocation;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.swing.text.Position;

/**
 *
 * @author jesus
 */
public class GameRules {

    private List<Object> objetos;
    private Wall walls[];
    private Gate gates[];

    private Dimension dimensionFrame;

    public GameRules(Dimension defaultDimension) {
        this.dimensionFrame = defaultDimension;
        this.objetos = Collections.synchronizedList(new ArrayList<>());
        walls = new Wall[2];
        gates = new Gate[2];
        initWalls();
        initGates();

    }

    //inicializo las paredes
    public void initWalls() {
        walls[0] = new Wall();
        walls[1] = new Wall();
        walls[0].setLocation(WallLocation.NORTH);
        walls[1].setLocation(WallLocation.SOUTH);
        objetos.add(walls[0]);
        objetos.add(walls[1]);
    }

    //inicializo las puertas
    public void initGates() {
        gates[0] = new Gate();
        gates[1] = new Gate();
        gates[0].setLocation(WallLocation.EAST);
        gates[0].setState(GateState.OPEN);
        gates[1].setLocation(WallLocation.WEST);
        gates[1].setState(GateState.OPEN);
        objetos.add(gates[0]);
        objetos.add(gates[1]);
    }

    //compruebo los posibles casos de colisión
    public List<Boolean> collideDetector(List<Bola> bolas, CoordinatesDTO newPosition, Object obj) {
        ArrayList<Boolean> collide = new ArrayList<>();
        addBolasToList(bolas);
        boolean rebotes[] = collideWalls(newPosition, obj);
        boolean choque = collideObjectToBola(bolas, obj);
        collide.add(rebotes[0]);//sendBola
        collide.add(rebotes[1]);//rebotarX
        collide.add(rebotes[2]);//rebotarY
        collide.add(choque);//bolaCollideBola
        return collide;
    }

    //añado la lista de bolas a la lista de objetos
    private void addBolasToList(List<Bola> bolas) {
        Iterator<Bola> iterador = bolas.iterator();
        while (iterador.hasNext()) {
            Bola b = iterador.next();
            objetos.add(b);
        }
    }

    //compruebo las colisiones con las paredes
    public boolean[] collideWalls(CoordinatesDTO newPosition, Object obj) {
        boolean rebotar[] = new boolean[2];
        //si el objeto que toca las paredes es una bola
        Bola bola = null;
        if (obj instanceof Bola) {
            bola = (Bola) obj;
            rebotar = bolaCollideWalls(newPosition, bola);
        }
        return rebotar;
    }

    //compruebo la colisión de una bola con una pared
    public boolean[] bolaCollideWalls(CoordinatesDTO newPosition, Bola bola) {
        boolean sendBola = false;
        boolean rebotarX = false;
        boolean rebotarY = false;
        int rad = bola.getRadio();
        int windowWidth = (int) dimensionFrame.getWidth() - rad * 2;
        int windowHeight = (int) dimensionFrame.getHeight() - rad * 2;

        // Verificar límites horizontales
        if (newPosition.getX() < (rad)) {
            //compruebo si tiene que rebotar en la parte izquierda (west)
            for (int i = 0; i < walls.length; i++) {
                if (walls[i].getLocation() == WallLocation.WEST) {
                    rebotarX = true;
                }
            }
            //si no rebota, compruebo si puedo mandar la bola a otra ventana
            if (!rebotarX) {
                for (int i = 0; i < gates.length; i++) {
                    //si es una puerta
                    if (gates[i].getLocation() == WallLocation.WEST) {
                        //si la puerta está abierta la mando
                        if (gates[i].getState() == GateState.OPEN) {
                            sendBola = true;
                        } //si no, rebota
                        else {
                            rebotarX = true;
                        }
                    }
                }
            }

        } else if (newPosition.getX() > windowWidth) {
            //compruebo si tiene que rebotar en la parte derecha (east)
            for (int i = 0; i < walls.length; i++) {
                if (walls[i].getLocation() == WallLocation.EAST) {
                    rebotarX = true;
                }
            }
            //si no rebota, compruebo si puedo mandar la bola a otra ventana
            if (!rebotarX) {
                for (int i = 0; i < gates.length; i++) {
                    //si es una puerta
                    if (gates[i].getLocation() == WallLocation.EAST) {
                        //si la puerta está abierta la mando
                        if (gates[i].getState() == GateState.OPEN) {
                            sendBola = true;
                        } //si no, rebota
                        else {
                            rebotarX = true;
                        }
                    }
                }
            }
        }

        // Verificar límites verticales
        if (newPosition.getY() < (rad)) {
            //compruebo si tiene que rebotar en la parte arriba (north)
            for (int i = 0; i < walls.length; i++) {
                if (walls[i].getLocation() == WallLocation.NORTH) {
                    rebotarY = true;
                }
            }
            //si no rebota, compruebo si puedo mandar la bola a otra ventana
            if (!rebotarY) {
                for (int i = 0; i < gates.length; i++) {
                    //si es una puerta
                    if (gates[i].getLocation() == WallLocation.NORTH) {
                        //si la puerta está abierta la mando
                        if (gates[i].getState() == GateState.OPEN) {
                            sendBola = true;
                        } //si no, rebota
                        else {
                            rebotarY = true;
                        }
                    }
                }
            }

        } else if (newPosition.getY() > windowHeight) {
            //compruebo si tiene que rebotar en la parte abajo (south)
            for (int i = 0; i < walls.length; i++) {
                if (walls[i].getLocation() == WallLocation.SOUTH) {
                    rebotarY = true;
                }
            }
            //si no rebota, compruebo si puedo mandar la bola a otra ventana
            if (!rebotarY) {
                for (int i = 0; i < gates.length; i++) {
                    //si es una puerta
                    if (gates[i].getLocation() == WallLocation.SOUTH) {
                        //si la puerta está abierta la mando
                        if (gates[i].getState() == GateState.OPEN) {
                            sendBola = true;
                        } //si no, rebota
                        else {
                            rebotarY = true;
                        }
                    }
                }
            }
        }

        return new boolean[]{sendBola, rebotarX, rebotarY};
    }

    //compruebo la colisión de un objeto con una bola
    public boolean collideObjectToBola(List<Bola> bolas, Object object) {
        boolean choque = false;
        Bola bolaActual = null;
        //el objeto es una bola
        if (object instanceof Bola) {
            bolaActual = (Bola) object;
            choque = collideBolaToBola(bolas, bolaActual);
        }
        return choque;
    }

    //compruebo la colisión de una bola con otra bola
    public boolean collideBolaToBola(List<Bola> bolas, Bola bola) {
        boolean choque = false;
        Iterator<Bola> iterator = bolas.iterator();
        if (bolas.size() > 1) {
            while (iterator.hasNext()) {
                Bola b = iterator.next();
                if (b.getId() != bola.getId() && b.seSuperponeCon(bola)) {
                    choque = true;
                }
            }
        }
        return choque;
    }

    //cambio el tamaño de la ventana
    public void changeFrameDimension(Dimension newFrameDimension) {
        this.dimensionFrame = newFrameDimension;
    }

    public void invertirBola(Bola bola) {
        CoordinatesDTO newPosition = bola.getPosicion();

        if (newPosition.getX() <= bola.getDiam()) {
            System.out.println("IZQUIERDA");
            newPosition.setX((int) (dimensionFrame.getWidth() - bola.getDiam()));
        } else if (newPosition.getX() >= dimensionFrame.getWidth() - bola.getDiam() * 2) {
            System.out.println("DERECHA");
            newPosition.setX(bola.getDiam());
        }

        if (newPosition.getY() <= bola.getDiam()) {
            System.out.println("ARRIBA");
            newPosition.setY((int) (dimensionFrame.getHeight() - bola.getDiam()));
        } else if (newPosition.getY() >= dimensionFrame.getHeight() - bola.getDiam() * 2) {
            System.out.println("ABAJO");
            newPosition.setY(bola.getDiam());
        }
        bola.setPosicion(newPosition);
    }
}
