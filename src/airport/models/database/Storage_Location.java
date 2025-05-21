/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.Location;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Storage_Location {
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
    public boolean addLocation(Location location){
        for (Location l : this.locations) {
            if (l.getAirportId()== location.getAirportId()) {
                return false;
            }
        }
        this.locations.add(location);
        return true;
    }
    public Location getLocation(String A_id){
        for (Location location : this.locations) {
            if (location.getAirportId()== A_id) {
                return location;
            }
        }
        return null;
    }
    public boolean delLocation(String A_id){
        for (Location location : this.locations) {
            if (location.getAirportId()== A_id) {
                this.locations.remove(location);
                return true;
            }
        }
        return false;
    }
}
