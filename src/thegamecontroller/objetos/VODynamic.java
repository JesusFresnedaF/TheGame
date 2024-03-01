/*
    Clase de dinamica de objetos visuales
 */
package thegamecontroller.objetos;

import thegamecontroller.dtos.AcelerationDTO;
import thegamecontroller.dtos.VODState;
import thegamecontroller.dtos.CoordinatesDTO;
import thegamecontroller.dtos.VectorDTO;
import thegamecontroller.dtos.VelocityDTO;

/**
 *
 * @author jesus
 */
public class VODynamic extends VisualObject{

    private VODState state;
    private VelocityDTO velocity;
    private AcelerationDTO acceleration;
    private CoordinatesDTO position;

    public VODynamic() {
        this.state = VODState.ALIVE;
        this.velocity = new VelocityDTO();
        this.acceleration = new AcelerationDTO();
        this.position = new CoordinatesDTO();
    }

    public VODynamic(VODState state, VelocityDTO velocity, AcelerationDTO acceleration, CoordinatesDTO position) {
        this.state = state;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.position = position;
    }
    
    //STATE
    public VODState getState() {
        return state;
    }

    public void setState(VODState state) {
        this.state = state;
    }

    //VELOCITY
    public VelocityDTO getVelocity() {
        return velocity;
    }

    public void setVelocity(VelocityDTO velocity) {
        this.velocity = velocity;
    }

    //ACCELERATION
    public AcelerationDTO getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(AcelerationDTO acceleration) {
        this.acceleration = acceleration;
    }

    //POSITION
    public CoordinatesDTO getPosition() {
        return position;
    }

    public void setPosition(CoordinatesDTO position) {
        System.out.println(position);
        this.position = position;
    }

    public void mover() {
    }

    public void damage(Bola bola) {
    }

    public void explode() {
    }

    public void rebotarX() {
    }

    public void rebotarY() {
    }

    public void sendBola() {
    }

}
