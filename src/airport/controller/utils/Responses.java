/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.utils;

/**
 *
 * @author Jose
 */
public class Responses {
    
    private String message;
    private int status; 
    private Object data; 

    public Responses(String message, Status status) {
        this.message = message;
        this.status = status.getCode();
        this.data = null; // No data by default
    }

    public Responses(String message, Status status, Object data) {
        this.message = message;
        this.status = status.getCode();
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
    
}
