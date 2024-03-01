/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thegamecontroller.dtos;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author jesus
 */
public class VelocityDTO extends VectorDTO {

    public static final float MAX_VEL = 40;
    public static final float MIN_VEL = -40;

    public VelocityDTO() {
        super();
        Random rand = new Random();
        super.setX(rand.nextDouble(11) - 5); // Genera valores entre -5 y 5
        super.setY(rand.nextDouble(11) - 5); // Genera valores entre -5 y 5
    }

    public VelocityDTO(float velocidadX, float velocidadY) {
        super.setX(velocidadX);
        super.setY(velocidadY);
    }

    public double getVelocidadX() {
        return super.getX();
    }

    public void setVelocidadX(float velocidadX) {
        boolean signo = true;
        if (velocidadX < 0) {
            signo = false;
        }
        if (Math.abs(velocidadX) > MAX_VEL) {
            super.setX(velocidadX);
        } else {
            if (!signo) {
                super.setX(velocidadX);
            } else {
                super.setX(velocidadX);
            }
        }
    }

    public double getVelocidadY() {
        return super.getY();
    }

    public void setVelocidadY(float velocidadY) {
        boolean signo = true;
        if (velocidadY < 0) {
            signo = false;
        }
        if (Math.abs(velocidadY) > MAX_VEL) {
            super.setY(velocidadY);
        } else {
            if (!signo) {
            super.setY(velocidadY);
            } else {
            super.setY(velocidadY);
            }
        }
    }

}
