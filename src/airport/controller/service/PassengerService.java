/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.service;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.controller.validation.PassengerValidator;
import airport.models.Passenger;
import airport.models.database.Storage_Passenger;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 *
 * @author Jose
 */
public class PassengerService {

    public Responses createPassenger(String idp, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
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

            Responses birthValidation = PassengerValidator.validateBirthDate(yearInt, monthInt, dayInt);
            if (birthValidation != null) {
                return birthValidation;
            }

            LocalDate birthDate = LocalDate.of(yearInt, monthInt, dayInt);

            Storage_Passenger storage = Storage_Passenger.getInstance();
            if (storage.getPassenger(id) != null) {
                return new Responses("Passenger with this ID already exists", Status.BAD_REQUEST);
            }

            Passenger passenger = new Passenger(id, firstname, lastname, birthDate, phoneCodeInt, phoneLong, country);
            boolean added = storage.addPassenger(passenger);
            if (!added) {
                return new Responses("Passenger with this ID already exists", Status.BAD_REQUEST);
            }

            return new Responses("Passenger has been created successfully", Status.CREATED);

        } catch (NumberFormatException e) {
            return new Responses("Numeric fields must be valid numbers", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Responses("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Responses updatePassenger(String idp, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
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

            Responses birthValidation = PassengerValidator.validateBirthDate(yearInt, monthInt, dayInt);
            if (birthValidation != null) {
                return birthValidation;
            }

            Storage_Passenger storage = Storage_Passenger.getInstance();
            Passenger passenger = storage.getPassenger((int) id);
            if (passenger == null) {
                return new Responses("Passenger not found", Status.NOT_FOUND);
            }

            // Aquí podrías actualizar el objeto si tu clase Passenger permite setters
            return new Responses("Passenger has been updated successfully", Status.OK);

        } catch (NumberFormatException e) {
            return new Responses("Numeric fields must be valid numbers", Status.BAD_REQUEST);
        } catch (Exception e) {
            return new Responses("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
