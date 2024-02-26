/*
    Clase Gate
 */
package thegamecontroller.objetos;

import java.io.Serializable;
import thegamecontroller.dtos.WallLocation;
import thegamecontroller.dtos.GateState;

/**
 *
 * @author jesus
 */
public class Gate extends Wall{
    
    private GateState state;
    
    public Gate(){
        super();
        this.state = GateState.CLOSED;
    }
    
    public Gate(WallLocation location){
        super(location);
        this.state = GateState.CLOSED;
    }
    
    public WallLocation getLocation(){
        return super.getLocation();
    }
    
    public void setLocation(WallLocation location){
        super.setLocation(location);
    }

    public GateState getState() {
        return state;
    }

    public void setState(GateState state) {
        this.state = state;
    }
    
    
    
}
