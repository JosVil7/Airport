/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database.interfaces;

import airport.models.Passenger;
import java.util.List;
import org.json.JSONArray;

/**
 *
 * @author Jose
 */
public interface IPassengerStorage {
    void cargarJSON(JSONArray array);
    boolean addPassenger(Passenger passenger);
    Passenger getPassenger(long id);
    List<Passenger> getAllPassengers();
    boolean updatePassenger(Passenger passenger);
    List<Passenger> getPassengers();
}
