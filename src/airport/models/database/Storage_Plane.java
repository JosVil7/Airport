/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;


import airport.models.Flight;
import airport.models.Plane;
import airport.models.database.interfaces.IPlaneStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author USER
 */

public class Storage_Plane implements IPlaneStorage { 

    public static Storage_Plane instance;
    private ArrayList<Plane> planes;

    private Storage_Plane() {
        this.planes = new ArrayList<>();
    }

    public static Storage_Plane getInstance() {
        if (instance == null) {
            instance = new Storage_Plane();
        }
        return instance;
    }

    @Override // Add @Override
    public boolean addPlane(Plane plane) {
        for (Plane pl : this.planes) {
            if (pl.getId().equals(plane.getId())) {
                return false; 
            }
        }
        this.planes.add(plane);
        return true;
    }

    @Override // Add @Override
    public Plane getPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                return plane.clone(); 
            }
        }
        return null;
    }

    
    @Override 
    public List<Plane> getAllPlanes() { 
        List<Plane> sortedPlanes = new ArrayList<>(this.planes);
        Collections.sort(sortedPlanes, Comparator.comparing(Plane::getId));

        List<Plane> copiedPlanes = new ArrayList<>();
        for (Plane p : sortedPlanes) {
            copiedPlanes.add(p.clone());
        }
        return copiedPlanes;
    }
    
    public boolean delPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                this.planes.remove(plane);
                return true;
            }
        }
        return false;
    }

    public void cargarJSON(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject objecto = array.getJSONObject(i);
            final String id = objecto.getString("id");
            String brand = objecto.getString("brand");
            String model = objecto.getString("model");
            final int maxCapacity = objecto.getInt("maxCapacity");
            String airline = objecto.getString("airline");

            
            if (getPlane(id) == null) { 
                Plane plane = new Plane(id, brand, model, maxCapacity, airline);
                this.addPlane(plane); 
            }
        }
    }
}
