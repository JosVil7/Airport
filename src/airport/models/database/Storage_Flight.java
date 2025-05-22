/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.Flight;
import airport.models.Plane;


import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class Storage_Flight {
    private static Storage_Flight instance;
    private ArrayList<Flight> flights;

    private Storage_Flight() {
        this.flights = new ArrayList<>();
    }
    

    public static Storage_Flight getInstance(){
         if (instance == null) {
            instance = new Storage_Flight();
        }
        return instance;
    }

       
    
    public boolean addFlight(Flight flight){
        for (Flight f : this.flights) {
            if (f.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }
    public Flight getFlight(String id){
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        return null;
    }
    
    public boolean delFlight(String id){
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                this.flights.remove(flight);
                return true;
            }
        }
        return false;
    }
    
    public void cargarJSON(JSONArray array){
        for (int i = 0; i < array.length(); i++) {
            JSONObject objecto = array.getJSONObject(i);
            final String id = objecto.getString("id");
            String plane = objecto.getString("plane");
            //problemita con getplane
            Plane plane_real = Storage_Plane.getInstance().getPlane(plane);
            
        }
    }
    
}
