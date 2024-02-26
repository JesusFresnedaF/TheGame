/*
    DTO para la aceleración de los objetos
 */
package thegamecontroller.dtos;

import java.io.Serializable;

/**
 *
 * @author jesus
 */
public class AcelerationDTO implements Serializable {

    private double aceleracionX, aceleracionY;

    public AcelerationDTO() {
        this.aceleracionX = 2;
        this.aceleracionY = 2;
    }

    public AcelerationDTO(int aceleracionX, int aceleracionY) {
        this.aceleracionX = aceleracionX;
        this.aceleracionY = aceleracionY;
    }

    public double getAceleracionX() {
        return aceleracionX;
    }

    public void setAceleracionX(double aceleracionX) {
        this.aceleracionX = aceleracionX;
    }

    public double getAceleracionY() {
        return aceleracionY;
    }

    public void setAceleracionY(double aceleracionY) {
        this.aceleracionY = aceleracionY;
    }

    @Override
    public String toString() {
        return "AcelerationDTO{" + "aceleracionX=" + aceleracionX + ", aceleracionY=" + aceleracionY + '}';
    }
}
