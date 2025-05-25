/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;


import airport.models.Plane;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;


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
    
    public static Storage_Plane getInstance(){
        if (instance == null) {
            instance = new Storage_Plane();
        }
        return instance;
    }
    
    public boolean addPlane(Plane plane){
        for (Plane pl : this.planes) {
            if (pl.getId().equals(plane.getId())) {
                return false;
            }
        }
        this.planes.add(plane);
        return true;
    }
    
    public Plane getPlane(String id){
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                return plane;
            }
        }
        return null;
    }
    
    public boolean delPlane(String id){
        for (Plane plane : this.planes) {
            if (plane.getId().equals(id)) {
                this.planes.remove(plane);
                return true;
            }
        }
        return false;
    }
    
    public void cargarJSON(JSONArray array){
        for (int i = 0; i < array.length(); i++) {
            JSONObject objecto = array.getJSONObject(i);
            final String id = objecto.getString("id");
            String brand = objecto.getString("brand");
            String model = objecto.getString("model");
            final int maxCapacity = objecto.getInt("maxCapacity");
            String airline = objecto.getString("airline");
            
            Plane plane = new Plane(id, brand, model, maxCapacity, airline);
            this.addPlane(plane);
        }
    }
}
