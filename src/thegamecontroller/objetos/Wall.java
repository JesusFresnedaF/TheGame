/*
    Clase Wall
 */
package thegamecontroller.objetos;

import thegamecontroller.dtos.WallLocation;

/**
 *
 * @author jesus
 */
public class Wall extends VisualObject{
    
    private WallLocation location;
    
    public Wall(){
        this.location = WallLocation.NORTH;
    }
    
    public Wall(WallLocation location){
        this.location = location;
    }

    public WallLocation getLocation() {
        return location;
    }

    public void setLocation(WallLocation location) {
        this.location = location;
    }
    
}
