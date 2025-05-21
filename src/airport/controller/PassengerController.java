/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.models.Passenger;
import airport.models.database.Storage_Passenger;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 *
 * @author Jose
 */

public class PassengerController {

        public static Responses createPassenger(String idp, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
    try {
        // Validación básica de campos vacíos
        if (idp == null || idp.trim().isEmpty()) {
            return new Responses("Id must not be empty", Status.BAD_REQUEST);
        }
        if (year == null || year.trim().isEmpty() ||
            month == null || month.trim().isEmpty() ||
            day == null || day.trim().isEmpty()) {
            return new Responses("Birthdate fields must not be empty", Status.BAD_REQUEST);
        }
        if (firstname == null || firstname.trim().isEmpty()) {
            return new Responses("Firstname must not be empty", Status.BAD_REQUEST);
        }
        if (lastname == null || lastname.trim().isEmpty()) {
            return new Responses("Lastname must not be empty", Status.BAD_REQUEST);
        }
        if (phoneCode == null || phoneCode.trim().isEmpty()) {
            return new Responses("Phone code must not be empty", Status.BAD_REQUEST);
        }
        if (phone == null || phone.trim().isEmpty()) {
            return new Responses("Phone must not be empty", Status.BAD_REQUEST);
        }
        if (country == null || country.trim().isEmpty()) {
            return new Responses("Country must not be empty", Status.BAD_REQUEST);
        }

        // Parseos (ahora seguros)
        int id = Integer.parseInt(idp);
        if (id < 0) {
            return new Responses("Id must be positive", Status.BAD_REQUEST);
        }

        int yearr = Integer.parseInt(year);
        int monthr = Integer.parseInt(month);
        int dayr = Integer.parseInt(day);
        long idpp = Long.parseLong(idp);
        long phonep = Long.parseLong(phone);
        int PhoneCode = Integer.parseInt(phoneCode);

        // Validar fecha
        LocalDate birthDate;
        try {
            birthDate = LocalDate.of(yearr, monthr, dayr);
        } catch (DateTimeException ex) {
            return new Responses("Invalid birthdate", Status.BAD_REQUEST);
        }

        LocalDate now = LocalDate.now();
        if (birthDate.isAfter(now) || birthDate.isBefore(LocalDate.of(1900, 1, 1))) {
            return new Responses("Birthdate must be between 1900 and today", Status.BAD_REQUEST);
        }

        // Validar longitudes
        if (phoneCode.length() > 3) {
            return new Responses("Phone code must be max 3 digits", Status.BAD_REQUEST);
        }

        if (phone.length() > 11) {
            return new Responses("Phone must be max 11 digits", Status.BAD_REQUEST);
        }

        // Comprobar si el pasajero ya existe
        Storage_Passenger storage = Storage_Passenger.getInstance();
        if (storage.getPassenger(id) != null) {
            return new Responses("Passenger with this ID already exists", Status.BAD_REQUEST);
        }

        // Crear pasajero
        Passenger passenger = new Passenger(idpp, firstname, lastname, birthDate, PhoneCode, phonep, country);
        boolean added = storage.addPassenger(passenger);
        if (!added) {
            return new Responses("Passenger with this ID already exists", Status.BAD_REQUEST);
        }

        return new Responses("Person created successfully", Status.CREATED);

    } catch (NumberFormatException ex) {
        return new Responses("Numeric fields must be valid numbers", Status.BAD_REQUEST);
    } catch (Exception ex) {
        return new Responses("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
}

       
}

    
    
    
    



