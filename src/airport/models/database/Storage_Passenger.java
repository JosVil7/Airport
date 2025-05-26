/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.database.interfaces.IPassengerStorage;
import airport.models.Passenger;
import airport.models.database.interfaces.Observer;
import airport.models.database.interfaces.Subject;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author USER
 */
public class Storage_Passenger implements IPassengerStorage, Subject {

    private static Storage_Passenger instance;
    private ArrayList<Passenger> passengers;
    private final List<Observer> observers;
    

    private Storage_Passenger() {
        this.passengers = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public static Storage_Passenger getInstance() {
        if (instance == null) {
            instance = new Storage_Passenger();
        }
        return instance;
    }

    @Override
    public boolean addPassenger(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false; 
            }
        }
        this.passengers.add(passenger);
        return true;
    }

    @Override
    public Passenger getPassenger(long id) {
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == id) {

            return passenger.clone(); 

            }
        }
        return null;
    }

    @Override
    public List<Passenger> getAllPassengers() {
        List<Passenger> sortedPassengers = new ArrayList<>(this.passengers);
        Collections.sort(sortedPassengers, Comparator.comparingLong(Passenger::getId));
        
        List<Passenger> copiedPassengers = new ArrayList<>();
        for (Passenger p : sortedPassengers) {
            copiedPassengers.add(p.clone());
        }
        return copiedPassengers;
    }

    @Override
    public boolean updatePassenger(Passenger updatedPassenger) {
        for (int i = 0; i < this.passengers.size(); i++) {
            if (this.passengers.get(i).getId() == updatedPassenger.getId()) {
                this.passengers.set(i, updatedPassenger);
                return true;
            }
        }
        return false; 
    }

    @Override
    public void cargarJSON(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject objecto = array.getJSONObject(i);
            final long id = objecto.getLong("id");
            String firstname = objecto.getString("firstname");
            String lastname = objecto.getString("lastname");
            String birthDatestr = objecto.getString("birthDate");
            LocalDate birthDate = LocalDate.parse(birthDatestr);
            int countryPhoneCode = objecto.getInt("countryPhoneCode");
            long phone = objecto.getLong("phone");
            String country = objecto.getString("country");

            if (getPassenger(id) == null) {
                Passenger passenger = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
                this.addPassenger(passenger);
            }
        }
    }

    
    @Override
    public List<Passenger> getPassengers() {
    ArrayList<Passenger> clonedPassengers = new ArrayList<>();
        for (Passenger p : this.passengers) {
            clonedPassengers.add(p.clone());
        }
        return clonedPassengers; 
    }
    
    @Override
    public void notificarOb(){
        for (Observer observer : this.observers) {
            observer.actualizar();
        }
    }
    
    @Override
    public void quitarOb(Observer o){
        this.observers.remove(o);
    }
    
    @Override
    public void registrarOb(Observer o){
        this.observers.add(o);
    }
}
