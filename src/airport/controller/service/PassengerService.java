/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.service;

import airport.controller.interfaces.IPassengerService;
import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.controller.validation.PassengerValidator;
import airport.models.Passenger;
import airport.models.database.IPassengerStorage;
import airport.models.database.Storage_Passenger;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author Jose
 */
public class PassengerService implements IPassengerService {

    private IPassengerStorage passengerStorage; // Dependency Injected

    public PassengerService() {
        this.passengerStorage = Storage_Passenger.getInstance(); // Default concrete implementation
    }

    // Constructor for dependency injection (useful for testing)
    public PassengerService(IPassengerStorage passengerStorage) {
        this.passengerStorage = passengerStorage;
    }

    @Override
    public Responses createPassenger(String idp, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        // Validate input data
        Responses validationResponse = PassengerValidator.validateCreate(idp, firstname, lastname, year, month, day, phoneCode, phone, country);
        if (validationResponse != null) {
            return validationResponse;
        }

        try {
            long id = Long.parseLong(idp);
            int yearInt = Integer.parseInt(year);
            int monthInt = Integer.parseInt(month);
            int dayInt = Integer.parseInt(day);
            int phoneCodeInt = Integer.parseInt(phoneCode);
            long phoneLong = Long.parseLong(phone);

            // Validate birth date validity and range
            Responses birthValidation = PassengerValidator.validateBirthDate(yearInt, monthInt, dayInt);
            if (birthValidation != null) {
                return birthValidation;
            }

            LocalDate birthDate = LocalDate.of(yearInt, monthInt, dayInt);

            // Check if passenger with this ID already exists
            if (passengerStorage.getPassenger(id) != null) {
                return new Responses("Passenger with this ID already exists", Status.BAD_REQUEST);
            }

            Passenger passenger = new Passenger(id, firstname, lastname, birthDate, phoneCodeInt, phoneLong, country);
            boolean added = passengerStorage.addPassenger(passenger);

            if (!added) {
                // This case should ideally be caught by the prior getPassenger check, but good for robustness
                return new Responses("Failed to add passenger, possibly duplicate ID.", Status.BAD_REQUEST);
            }

            return new Responses("Passenger has been created successfully", Status.CREATED);

        } catch (NumberFormatException e) {
            return new Responses("Numeric fields (ID, Year, Month, Day, Phone Code, Phone Number) must be valid numbers.", Status.BAD_REQUEST);
        } catch (DateTimeParseException e) {
            return new Responses("Invalid date format provided for birthdate.", Status.BAD_REQUEST);
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return new Responses("An unexpected error occurred during passenger creation.", Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Responses updatePassenger(String idp, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        // Validate input data using the same validation as create, as per requirements
        Responses validationResponse = PassengerValidator.validateCreate(idp, firstname, lastname, year, month, day, phoneCode, phone, country);
        if (validationResponse != null) {
            return validationResponse;
        }

        try {
            long id = Long.parseLong(idp);
            int yearInt = Integer.parseInt(year);
            int monthInt = Integer.parseInt(month);
            int dayInt = Integer.parseInt(day);
            int phoneCodeInt = Integer.parseInt(phoneCode);
            long phoneLong = Long.parseLong(phone);

            // Validate birth date validity and range
            Responses birthValidation = PassengerValidator.validateBirthDate(yearInt, monthInt, dayInt);
            if (birthValidation != null) {
                return birthValidation;
            }

            Passenger passengerToUpdate = passengerStorage.getPassenger(id);
            if (passengerToUpdate == null) {
                return new Responses("Passenger with ID " + id + " not found for update.", Status.NOT_FOUND);
            }

            // Update passenger object fields
            passengerToUpdate.setFirstname(firstname);
            passengerToUpdate.setLastname(lastname);
            passengerToUpdate.setBirthDate(LocalDate.of(yearInt, monthInt, dayInt));
            passengerToUpdate.setCountryPhoneCode(phoneCodeInt);
            passengerToUpdate.setPhone(phoneLong);
            passengerToUpdate.setCountry(country);

            boolean updated = passengerStorage.updatePassenger(passengerToUpdate);
            if (!updated) {
                return new Responses("Failed to update passenger information.", Status.INTERNAL_SERVER_ERROR);
            }

            return new Responses("Passenger information has been updated successfully", Status.OK);

        } catch (NumberFormatException e) {
            return new Responses("Numeric fields (ID, Year, Month, Day, Phone Code, Phone Number) must be valid numbers.", Status.BAD_REQUEST);
        } catch (DateTimeParseException e) {
            return new Responses("Invalid date format provided for birthdate.", Status.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses("An unexpected error occurred during passenger update.", Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Responses getPassengerById(String idp) {
        try {
            long id = Long.parseLong(idp);
            Passenger passenger = passengerStorage.getPassenger(id);
            if (passenger == null) {
                return new Responses("Passenger not found with ID: " + id, Status.NOT_FOUND);
            }
            return new Responses("Passenger retrieved successfully", Status.OK, passenger); // Return the passenger object
        } catch (NumberFormatException e) {
            return new Responses("Invalid Passenger ID format.", Status.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses("An unexpected error occurred while retrieving passenger.", Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Responses getAllPassengers() {
        try {
            List<Passenger> passengers = passengerStorage.getAllPassengers();
            if (passengers.isEmpty()) {
                return new Responses("No passengers found.", Status.NOT_FOUND);
            }
            return new Responses("Passengers retrieved successfully", Status.OK, passengers); // Return the list of passengers
        } catch (Exception e) {
            e.printStackTrace();
            return new Responses("An unexpected error occurred while retrieving all passengers.", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
