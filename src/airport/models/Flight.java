/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edangulo
 */
public class Flight {

    private final String id;
    private List<Passenger> passengers;
    private Plane plane;
    private Location departureLocation;
    private Location scaleLocation;
    private Location arrivalLocation;
    private LocalDateTime departureDate;
    private int hoursDurationArrival;
    private int minutesDurationArrival;
    private int hoursDurationScale;
    private int minutesDurationScale;

    // Constructor without scale location
    public Flight(String id, Plane plane, Location departureLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        this.hoursDurationScale = 0;
        this.minutesDurationScale = 0;
        // Removed: this.plane.addFlight(this); // This should be handled by the service after creation
    }

    public Flight(String id, Plane plane, Location departureLocation, Location scaleLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival, int hoursDurationScale, int minutesDurationScale) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.scaleLocation = scaleLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        this.hoursDurationScale = hoursDurationScale;
        this.minutesDurationScale = minutesDurationScale;
        // Removed: this.plane.addFlight(this); // This should be handled by the service after creation
    }

    public void addPassenger(Passenger passenger) {
        if (!this.passengers.contains(passenger)) {
            this.passengers.add(passenger);
        }
    }

    // Getters and Setters
    public String getId() { return id; }
    public List<Passenger> getPassengers() { return new ArrayList<>(passengers); }
    public Plane getPlane() { return plane.clone(); }
    public Location getDepartureLocation() { return departureLocation.clone(); }
    public Location getScaleLocation() { return scaleLocation != null ? scaleLocation.clone() : null; }
    public Location getArrivalLocation() { return arrivalLocation.clone(); }
    public LocalDateTime getDepartureDate() { return departureDate; }
    public int getHoursDurationArrival() { return hoursDurationArrival; }
    public int getMinutesDurationArrival() { return minutesDurationArrival; }
    public int getHoursDurationScale() { return hoursDurationScale; }
    public int getMinutesDurationScale() { return minutesDurationScale; }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime calculateArrivalDate() {
        LocalDateTime finalArrival = departureDate;
        if (scaleLocation != null) {
            finalArrival = finalArrival.plusHours(hoursDurationScale).plusMinutes(minutesDurationScale);
        }
        finalArrival = finalArrival.plusHours(hoursDurationArrival).plusMinutes(minutesDurationArrival);
        return finalArrival;
    }

    public void delay(int hours, int minutes) {
        this.departureDate = this.departureDate.plusHours(hours).plusMinutes(minutes);
    }

    public int getNumPassengers() {
        return passengers.size();
    }

    public boolean hasPassenger(long passengerId) {
        for (Passenger p : this.passengers) {
            if (p.getId() == passengerId) {
                return true;
            }
        }
        return false;
    }

    // Copy constructor for Prototype pattern
    public Flight(Flight other) {
        this.id = other.id;
        this.passengers = new ArrayList<>();
        for (Passenger p : other.passengers) {
            this.passengers.add(p.clone());
        }
        if (other.plane != null) {
            this.plane = other.plane.clone();
        } else {
            this.plane = null;
        }

        if (other.departureLocation != null) {
            this.departureLocation = other.departureLocation.clone();
        } else {
            this.departureLocation = null;
        }

        if (other.scaleLocation != null) {
            this.scaleLocation = other.scaleLocation.clone();
        } else {
            this.scaleLocation = null;
        }

        if (other.arrivalLocation != null) {
            this.arrivalLocation = other.arrivalLocation.clone();
        } else {
            this.arrivalLocation = null;
        }
        this.departureDate = other.departureDate;
        this.hoursDurationArrival = other.hoursDurationArrival;
        this.minutesDurationArrival = other.minutesDurationArrival;
        this.hoursDurationScale = other.hoursDurationScale;
        this.minutesDurationScale = other.minutesDurationScale;
    }

    @Override
    public Flight clone() {
        return new Flight(this);
    }
}
