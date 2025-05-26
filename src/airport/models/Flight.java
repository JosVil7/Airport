/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public class Flight {
    
    private final String id;
    private ArrayList<Passenger> passengers;
    private Plane plane;
    private Location departureLocation;
    private Location scaleLocation;
    private Location arrivalLocation;
    private LocalDateTime departureDate;
    private int hoursDurationArrival;
    private int minutesDurationArrival;
    private int hoursDurationScale;
    private int minutesDurationScale;
    

    public Flight(String id, Plane plane, Location departureLocation, Location arrivalLocation, LocalDateTime departureDate, int hoursDurationArrival, int minutesDurationArrival) {
        this.id = id;
        this.passengers = new ArrayList<>();
        this.plane = plane;
        this.departureLocation = departureLocation;
        this.arrivalLocation = arrivalLocation;
        this.departureDate = departureDate;
        this.hoursDurationArrival = hoursDurationArrival;
        this.minutesDurationArrival = minutesDurationArrival;
        
        this.plane.addFlight(this);
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
        
        this.plane.addFlight(this);
    }
    
    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }
    
    public String getId() {
        return id;
    }

    public Location getDepartureLocation() {
        return departureLocation;
    }

    public Location getScaleLocation() {
        return scaleLocation;
    }

    public Location getArrivalLocation() {
        return arrivalLocation;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public int getHoursDurationArrival() {
        return hoursDurationArrival;
    }

    public int getMinutesDurationArrival() {
        return minutesDurationArrival;
    }

    public int getHoursDurationScale() {
        return hoursDurationScale;
    }

    public int getMinutesDurationScale() {
        return minutesDurationScale;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }
    
    public LocalDateTime calculateArrivalDate() {
        return departureDate.plusHours(hoursDurationScale).plusHours(hoursDurationArrival).plusMinutes(minutesDurationScale).plusMinutes(minutesDurationArrival);
    }
    
    public void delay(int hours, int minutes) {
        this.departureDate = this.departureDate.plusHours(hours).plusMinutes(minutes);
    }
    
    public int getNumPassengers() {
        return passengers.size();
    }
    
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
    
    public Flight clone(){
        return new Flight(this);
    }
}
