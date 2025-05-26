/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;


import airport.models.Flight;
import airport.models.Plane;
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
public class Storage_Plane implements IPlaneStorage{

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
                return false; // Plane with this ID already exists
            }
        }
        this.planes.add(plane);
        return true;
    }

    @Override // Add @Override
    public Plane getPlane(String id) {
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                return plane.clone(); // Return a copy (Prototype Pattern)
            }
        }
        return null;
    }

    // This method was already named getPlanes(), but we'll use getAllPlanes() as per the interface
    // and sort the list before returning.
    @Override // Add @Override
    public List<Plane> getAllPlanes() { // Renamed from getPlanes() for consistency
        List<Plane> sortedPlanes = new ArrayList<>(this.planes);
        // Sort planes by ID (String comparison)
        Collections.sort(sortedPlanes, Comparator.comparing(Plane::getId));

        // Return copies of planes in the list to avoid external modification (Prototype Pattern)
        List<Plane> copiedPlanes = new ArrayList<>();
        for (Plane p : sortedPlanes) {
            copiedPlanes.add(p.clone());
        }
        return copiedPlanes;
    }

    // Keep delPlane if it's used elsewhere, otherwise consider removing if not part of requirements.
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

            // Check if plane already exists to avoid duplicates when loading
            if (getPlane(id) == null) { // Use getPlane through the interface (or direct method)
                Plane plane = new Plane(id, brand, model, maxCapacity, airline);
                this.addPlane(plane); // Use addPlane through the interface (or direct method)
            }
        }
    }
    
}
