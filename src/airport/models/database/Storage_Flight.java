/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import airport.models.Flight;
import airport.models.Location;
import airport.models.Plane;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
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

    public static Storage_Flight getInstance() {
        if (instance == null) {
            instance = new Storage_Flight();
        }
        return instance;
    }

    public boolean addFlight(Flight flight) {
        for (Flight f : this.flights) {
            if (f.getId().equals(flight.getId())) {
                return false;
            }
        }
        this.flights.add(flight);
        return true;
    }

    public Flight getFlight(String id) {
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                return flight;
            }
        }
        return null;
    }

    public boolean delFlight(String id) {
        for (Flight flight : this.flights) {
            if (flight.getId().equals(id)) {
                this.flights.remove(flight);
                return true;
            }
        }
        return false;
    }

    public void cargarJSON(JSONArray array) {
    for (int i = 0; i < array.length(); i++) {
        JSONObject objecto = array.getJSONObject(i);
        final String id = objecto.getString("id");
        String plane = objecto.getString("plane");

        Plane plane_real = Storage_Plane.getInstance().getPlane(plane);
        if (plane_real == null) {
            System.err.println("Error: No se encontró el plane con id: " + plane);
            continue; // Saltar esta iteración y no crear el Flight
        }

        String departureLocation = objecto.getString("departureLocation");
        Location departureLocation_real = Storage_Location.getInstance().getLocation(departureLocation);

        Location scaleLocation_real = null;
        if (!objecto.isNull("scaleLocation")) {
            String scaleLocation = objecto.getString("scaleLocation");
            scaleLocation_real = Storage_Location.getInstance().getLocation(scaleLocation);
        }

        String arrivalLocation = objecto.getString("arrivalLocation");
        Location arrivalLocation_real = Storage_Location.getInstance().getLocation(arrivalLocation);

        String departureDate_str = objecto.getString("departureDate");
        LocalDateTime departureDate = LocalDateTime.parse(departureDate_str);

        int hoursDurationArrival = objecto.getInt("hoursDurationArrival");
        int minutesDurationArrival = objecto.getInt("minutesDurationArrival");
        int hoursDurationScale = objecto.getInt("hoursDurationScale");
        int minutesDurationScale = objecto.getInt("minutesDurationScale");

        Flight flight = new Flight(id, plane_real, departureLocation_real, scaleLocation_real, arrivalLocation_real, departureDate, hoursDurationArrival, minutesDurationArrival, hoursDurationScale, minutesDurationScale);
        this.addFlight(flight);
    }
}


    public List<Flight> getFlightss() {
        return this.flights;
    }

}
