/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thegamecontroller.dtos;

import java.util.Random;

/**
 *
 * @author jesus
 */
public class AcelerationDTO extends VectorDTO {

    public AcelerationDTO() {
        super();
        Random rand = new Random();
        super.setX(rand.nextDouble(11) - 5); // Genera valores entre -5 y 5
        super.setY(rand.nextDouble(11) - 5); // Genera valores entre -5 y 5
    }

    public AcelerationDTO(int aceleracionX, int aceleracionY) {
        super.setX(aceleracionX);
        super.setY(aceleracionY);
    }

    public double getAceleracionX() {
        return super.getX();
    }

    public void setAceleracionX(double aceleracionX) {
        super.setX(aceleracionX);
    }

    public double getAceleracionY() {
        return super.getX();
    }

    public void setAceleracionY(double aceleracionY) {
        super.setY(aceleracionY);
    }
}
