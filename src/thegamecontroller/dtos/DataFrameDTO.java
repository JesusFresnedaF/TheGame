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
public class DataFrameDTO implements Serializable {
    
    private Object object;
    private DataFrameType type;

    public DataFrameDTO(Object object, DataFrameType type) {
        this.object = object;
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public DataFrameType getType() {
        return type;
    }

    public void setType(DataFrameType type) {
        this.type = type;
    }

    
    
}
