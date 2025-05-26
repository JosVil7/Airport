/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.service;

import airport.controller.interfaces.ILocationService;
import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.controller.validation.LocationValidator;
import airport.models.Location;
import airport.models.database.Storage_Location;
import airport.models.database.interfaces.ILocationStorage;
import java.util.List;

/**
 *
 * @author Jose
 */
public class LocationService implements ILocationService { 

    private ILocationStorage locationStorage;

    public LocationService() {
        this.locationStorage = Storage_Location.getInstance();
    }

    public LocationService(ILocationStorage locationStorage) {
        this.locationStorage = locationStorage;
    }

    @Override
    public Responses createLocation(String id, String name, String city, String country, String latitude, String longitude) {
        
        try {
            Responses validationResponse = LocationValidator.validateCreate(id, name, city, country, latitude, longitude);
            if (validationResponse != null) {
                return validationResponse;
            }

            if (locationStorage.getLocation(id) != null) {
                return new Responses("Location with this ID already exists", Status.BAD_REQUEST);
            }

            double lat = Double.parseDouble(latitude.trim().replace(",", "."));
            double lon = Double.parseDouble(longitude.trim().replace(",", "."));

            Location location = new Location(id, name, city, country, lat, lon);

            boolean added = locationStorage.addLocation(location);
            if (!added) {
                return new Responses("Failed to add location, possibly duplicate ID.", Status.BAD_REQUEST);
            }

            return new Responses("Location has been created successfully", Status.CREATED);
        } catch (NumberFormatException e) {
            return new Responses("Latitude and Longitude must be valid numbers.", Status.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Responses("An unexpected error occurred during location creation.", Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override 
    public Responses getAllLocations() {
        try {
            List<Location> locations = locationStorage.getAllLocations(); 
            if (locations.isEmpty()) {
                return new Responses("No locations found.", Status.NOT_FOUND);
            }
            return new Responses("Locations retrieved successfully", Status.OK, locations);
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses("An unexpected error occurred while retrieving all locations.", Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Responses getLocationById(String id) {
        try {
            Location location = locationStorage.getLocation(id);
            if (location == null) {
                return new Responses("Location not found with ID: " + id, Status.NOT_FOUND);
            }
            return new Responses("Location retrieved successfully", Status.OK, location);
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses("An unexpected error occurred while retrieving location.", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
