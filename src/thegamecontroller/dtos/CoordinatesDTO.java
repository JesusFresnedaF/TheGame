/*
    DTO para la posición de los objetos
 */
package thegamecontroller.dtos;

import java.awt.Dimension;
import java.io.Serializable;

/**
 *
 * @author jesus
 */
public class CoordinatesDTO implements Serializable {

    private float x, y;

    public CoordinatesDTO(Dimension dimensionFrame, int ballSize) {
        int maxX = (int) (dimensionFrame.getWidth() - ballSize);
        int maxY = (int) (dimensionFrame.getHeight() - ballSize);

        this.x = (float) (Math.random() * (maxX - ballSize)) + ballSize;
        this.y = (float) (Math.random() * (maxY - ballSize)) + ballSize;
    }

    public CoordinatesDTO() {
        this.x = 0;
        this.y = 0;
    }

    public CoordinatesDTO(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isEqualTo(CoordinatesDTO position2) {
        return this.getX() == position2.getX() && this.getY() == position2.getY();
    }

    @Override
    public String toString() {
        return "PositionDTO{" + "x=" + x + ", y=" + y + '}';
    }

}
