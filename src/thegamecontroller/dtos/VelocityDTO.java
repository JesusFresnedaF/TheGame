/*
    DTO para la velocidad de los objetos
 */
package thegamecontroller.dtos;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author jesus
 */
public class VelocityDTO implements Serializable {

    private float velocidadX, velocidadY;
    public static final float MAX_VEL = 40;
    public static final float MIN_VEL = -40;

    public VelocityDTO() {
        Random rand = new Random();
        this.velocidadX = rand.nextFloat(11) - 5; // Genera valores entre -5 y 5
        this.velocidadY = rand.nextFloat(11) - 5; // Genera valores entre -5 y 5
    }

    public VelocityDTO(float velocidadX, float velocidadY) {
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
    }

    public float getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(float velocidadX) {
        boolean signo = true;
        if (velocidadX < 0) {
            signo = false;
        }
        if (Math.abs(velocidadX) > MAX_VEL) {
            this.velocidadX = velocidadX;
        } else {
            if (!signo) {
                this.velocidadX = MIN_VEL;
            } else {
                this.velocidadX = MAX_VEL;
            }
        }
    }

    public float getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(float velocidadY) {
        boolean signo = true;
        if (velocidadY < 0) {
            signo = false;
        }
        if (Math.abs(velocidadY) > MAX_VEL) {
            this.velocidadY = velocidadY;
        } else {
            if (!signo) {
                this.velocidadY = MIN_VEL;
            } else {
                this.velocidadY = MAX_VEL;
            }
        }
    }

    @Override
    public String toString() {
        return "VelocityDTO{" + "velocidadX=" + velocidadX + ", velocidadY=" + velocidadY + '}';
    }
}
