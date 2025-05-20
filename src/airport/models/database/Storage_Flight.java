/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.Flight;
import airport.models.Location;
import airport.models.Passenger;
import airport.models.Plane;
import java.util.ArrayList;

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
    
    public static Storage_Flight getIstance(){
        if (instance == null) {
            instance = new Storage_Flight();
        }
        return instance;
    }
    
    public boolean addFlight(Flight flight){
        for (Flight f : this.flights) {
            if (f.getId() == flight.getId()) {
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }
    public Flight getFlight(String id){
        for (Flight flight : this.flights) {
            if (flight.getId() == id) {
                return flight;
            }
        }
        return null;
    }
    
    public boolean delFlight(String id){
        for (Flight flight : this.flights) {
            if (flight.getId() == id) {
                this.flights.remove(flight);
                return true;
            }
        }
        return false;
    }
    
}
