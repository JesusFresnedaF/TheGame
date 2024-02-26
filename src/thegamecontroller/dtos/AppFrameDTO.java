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
public class AppFrameDTO implements Serializable {
    
    private Object object;
    private AppFrameType type;

    public AppFrameDTO(Object object, AppFrameType type) {
        this.object = object;
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public AppFrameType getType() {
        return type;
    }

    public void setType(AppFrameType type) {
        this.type = type;
    }

}
