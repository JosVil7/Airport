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


    private static IPlaneStorage instance;
    private ArrayList<Plane> planes;

    private Storage_Plane() {
        this.planes = new ArrayList<>();
    }

    public static IPlaneStorage getInstance() {
        if (instance == null) {
            instance = new Storage_Plane();
        }
        return instance;
    }

    

    @Override 
    public boolean addPlane(Plane plane) {
        for (Plane pl : this.planes) {
            if (pl.getId().equals(plane.getId())) {
                return false; 
            }
        }
        this.planes.add(plane);
        return true;
    }

    
    @Override
    public Plane getPlane(String id){
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
        
        // Collections.sort(sortedPlanes, Comparator.comparing(Plane::getId)); // Para Java 8+
        Collections.sort(sortedPlanes, new Comparator<Plane>() {
            @Override
            public int compare(Plane p1, Plane p2) {
                return p1.getId().compareTo(p2.getId());
            }
        });

        List<Plane> copiedAndSortedPlanes = new ArrayList<>();
        for (Plane p : sortedPlanes) {
            copiedAndSortedPlanes.add(p.clone());
        }
        return copiedAndSortedPlanes;
    }
    

    
    @Override
    public boolean delPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                this.planes.remove(plane);
                return true;
            }
        }
        return false;
    }

    @Override
    public void cargarJSON(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject objecto = array.getJSONObject(i);
            final String id = objecto.getString("id");
            String brand = objecto.getString("brand");
            String model = objecto.getString("model");
            final int maxCapacity = objecto.getInt("maxCapacity");
            String airline = objecto.getString("airline");

            // Check if plane already exists to avoid duplicates when loading
            if (getPlane(id) == null) { // Use getPlane through the interface (or direct method)
                Plane plane = new Plane(id, brand, model, maxCapacity, airline);
                this.addPlane(plane); // Use addPlane through the interface (or direct method)
            }
        }
    }

    
    @Override
    public List<Plane> getPlanes() {
    ArrayList<Plane> clonedPlanes = new ArrayList<>();
        for (Plane p : this.planes) {
        clonedPlanes.add(p.clone());
        }
        return clonedPlanes; 
    }
}
