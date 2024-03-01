/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thegamecontroller.dtos;

import java.io.Serializable;

/**
 *
 * @author jesus
 */
public class VectorDTO implements Serializable{
    private double x, y;
    public static final float MAX_VEL = 40;
    public static final float MIN_VEL = -40;
    
    public VectorDTO(){
        this.x = 2;
        this.y = 2;
    }

    public VectorDTO(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    
}
