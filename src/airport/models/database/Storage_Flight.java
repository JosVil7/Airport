/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.Flight;
import airport.models.Location;
import airport.models.Plane;
import airport.models.database.interfaces.IFlightStorage;
import airport.models.database.interfaces.ILocationStorage;
import airport.models.database.interfaces.IPlaneStorage;
import airport.models.database.interfaces.Observer;
import airport.models.database.interfaces.Subject;
import java.time.LocalDateTime;

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
public class Storage_Flight implements IFlightStorage, Subject {

    private static Storage_Flight instance;
    private ArrayList<Flight> flights;
    private final List<Observer> observers;

    private IPlaneStorage planeStorage;
    private ILocationStorage locationStorage;

    private Storage_Flight() {
        this.flights = new ArrayList<>();
        this.planeStorage = Storage_Plane.getInstance();
        this.locationStorage = Storage_Location.getInstance();
        this.observers = new ArrayList<>();
    }

    public static Storage_Flight getInstance() {
        if (instance == null) {
            instance = new Storage_Flight();
        }
        return instance;
    }

    @Override
    public boolean addFlight(Flight flight) {
        for (Flight f : this.flights) {
            if (f.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }

    @Override
    public Flight getFlight(String id) {
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                return flight.clone(); // Return a clone for Prototype pattern
            }
        }
        return null;
    }

    @Override
    public boolean delFlight(String id) {
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                this.flights.remove(flight);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateFlight(Flight updatedFlight) {
        for (int i = 0; i < this.flights.size(); i++) {
            if (this.flights.get(i).getId().equals(updatedFlight.getId())) {
                this.flights.set(i, updatedFlight);
                return true;
            }
        }
        return false;
    }

    @Override
    public void cargarJSON(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject objecto = array.getJSONObject(i);
            final String id = objecto.getString("id");
            String planeId = objecto.getString("plane");


            Plane plane_real = planeStorage.getPlane(planeId);
            if (plane_real == null) {
                System.err.println("Error: No se encontró el plane con id: " + planeId + " para el vuelo " + id);
                continue; // Skip this flight if plane not found
            }

            String departureLocationId = objecto.getString("departureLocation");
            Location departureLocation_real = locationStorage.getLocation(departureLocationId);

            Location scaleLocation_real = null;
            if (!objecto.isNull("scaleLocation")) {
                String scaleLocationId = objecto.getString("scaleLocation");
                scaleLocation_real = locationStorage.getLocation(scaleLocationId);
            }

            String arrivalLocationId = objecto.getString("arrivalLocation");
            Location arrivalLocation_real = locationStorage.getLocation(arrivalLocationId);

            if (departureLocation_real == null || arrivalLocation_real == null ||
               (!objecto.isNull("scaleLocation") && scaleLocation_real == null)) {
                System.err.println("Error: No se encontraron todas las localizaciones para el vuelo id: " + id);
                continue; // Skip if locations are missing
            }


            String departureDate_str = objecto.getString("departureDate");
            LocalDateTime departureDate = LocalDateTime.parse(departureDate_str);

            int hoursDurationArrival = objecto.getInt("hoursDurationArrival");
            int minutesDurationArrival = objecto.getInt("minutesDurationArrival");
            int hoursDurationScale = objecto.getInt("hoursDurationScale");
            int minutesDurationScale = objecto.getInt("minutesDurationScale");

            Flight flight;
            if (scaleLocation_real == null) {
                flight = new Flight(id, plane_real, departureLocation_real, arrivalLocation_real, departureDate, hoursDurationArrival, minutesDurationArrival);
            } else {
                flight = new Flight(id, plane_real, departureLocation_real, scaleLocation_real, arrivalLocation_real, departureDate, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
            }
            plane_real.addFlight(flight); // Crucial: Add flight to plane after creation
            this.addFlight(flight); // Add to flight storage
        }
    }


    @Override
    public List<Flight> getAllFlights() {
        List<Flight> sortedFlights = new ArrayList<>(this.flights);
        Collections.sort(sortedFlights, Comparator.comparing(Flight::getDepartureDate));

        List<Flight> copiedFlights = new ArrayList<>();
        for (Flight f : sortedFlights) {
            copiedFlights.add(f.clone());
        }
        return copiedFlights;
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

