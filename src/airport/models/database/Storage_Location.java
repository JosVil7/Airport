/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.Location;
import airport.models.database.interfaces.ILocationStorage;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class Storage_Location implements ILocationStorage {
    public static Storage_Location instance;
    private ArrayList<Location> locations;
    private Storage_Location() {
        this.locations = new ArrayList<>();
    }
    

    public static Storage_Location getInstance(){

    
        if (instance == null) {
            instance = new Storage_Location();
        }
        return instance;
    }
    @Override
    public boolean addLocation(Location location){
        for (Location l : this.locations) {
            if (l.getAirportId().equals(location.getAirportId())) {
                return false;
            }
        }
        this.locations.add(location);
        return true;
    }
    @Override
    public Location getLocation(String A_id){
        for (Location location : this.locations) {
            if (location.getAirportId().equals(A_id)) {
            return location.clone(); 
            }
        }
        return null;
    }
    @Override
    public boolean delLocation(String A_id){
        for (Location location : this.locations) {
            if (location.getAirportId().equals(A_id)) {
                this.locations.remove(location);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void cargarJSON(JSONArray array){
        for (int i = 0; i < array.length(); i++) {
            JSONObject objecto = array.getJSONObject(i);
            final String airportId = objecto.getString("airportId");
            String airportName = objecto.getString("airportName");
            String airportCity = objecto.getString("airportCity");
            String airportCountry = objecto.getString("airportCountry");
            double airportLatitude = objecto.getDouble("airportLatitude");
            double airportLongitude = objecto.getDouble("airportLongitude");
            
            Location location = new Location(airportId, airportName, airportCity, airportCountry, airportLatitude, airportLongitude);
            this.addLocation(location);
        }
    }
    
    @Override
    public List<Location> getLocations() {
    ArrayList<Location> clonedLocations = new ArrayList<>();
        for (Location loc : this.locations) {
            clonedLocations.add(loc.clone());
        }
        return clonedLocations; 
    }
}
