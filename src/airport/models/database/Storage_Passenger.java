/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.Passenger;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public static Storage_Passenger getInstance() {
        if (instance == null) {
            instance = new Storage_Passenger();
        }
        return instance;
    }

    public boolean addPassenger(Passenger passenger) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passenger.getId()) {
                return false;
            }
        }
        this.passengers.add(passenger);
        return true;
    }

    public Passenger getPassenger(long id) {
        for (Passenger passenger : this.passengers) {
            if (passenger.getId() == id) {
                return passenger;
            }
        }
        return null;
    }

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

            Passenger passenger = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
            this.addPassenger(passenger);
        }
    }
    
    public List<Passenger> getPassengers() {
        return this.passengers;
    }


// quitar
//    public boolean delPassenger(long id){
//        for (Passenger passenger : this.passengers) {
//            if (passenger.getId() == id) {
//                this.passengers.remove(passenger);
//                return true;
//            }
//        }
//        return false;
//    }
}
