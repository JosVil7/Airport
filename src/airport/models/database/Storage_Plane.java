/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.Plane;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Storage_Plane {
    public static Storage_Plane instance;
    private ArrayList<Plane> planes;
    private Storage_Plane() {
        this.planes = new ArrayList<>();
    }
    
    public static Storage_Plane getIstance(){
        if (instance == null) {
            instance = new Storage_Plane();
        }
        return instance;
    }
    
    public boolean addPlane(Plane plane){
        for (Plane pl : this.planes) {
            if (pl.getId() == plane.getId()) {
                return false;
            }
        }
        this.planes.add(plane);
        return true;
    }
    
    public Plane getPlane(String id){
        for (Plane plane : this.planes) {
            if (plane.getId() == id) {
                return plane;
            }
        }
        return null;
    }
    
    public boolean delPlane(String id){
        for (Plane plane : this.planes) {
            if (plane.getId() == id) {
                this.planes.remove(plane);
                return true;
            }
        }
        return false;
    }
}
