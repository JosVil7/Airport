/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.Passenger;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Storage_Passenger {
    public static Storage_Passenger instance;
    private ArrayList<Passenger> passengers;
    private Storage_Passenger() {
        this.passengers = new ArrayList<>();
    }
    
    public static Storage_Passenger getIstance(){
        if (instance == null) {
            instance = new Storage_Passenger();
        }
        return instance;
    }
    
    public boolean addPassenger(Passenger passenger){
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
    }
    
    public Passenger getPassenger(long id){
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == id) {
                return passenger;
            }
        }
        return null;
    }
    
    public boolean delPassenger(long id){
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == id) {
                this.passengers.remove(passenger);
                return true;
            }
        }
        return false;
    }
}
