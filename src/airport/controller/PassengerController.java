/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.models.Passenger;
import airport.models.database.Storage_Passenger;
import java.time.LocalDate;

/**
 *
 * @author Jose
 */
public class PassengerController {
//
//    public static Responses createPassenger(long idp, String firstname, String lastname, LocalDate birthDate, int phoneCode, long phone, String country) {
//        try {
//            
//            String ids = String.valueOf(idp);
//            String pc = String.valueOf(phoneCode);
//            String p = String.valueOf(phone);
//            
//            
//            try {
//                if (idp<0 || String.valueOf(idp).length()>15) {
//                    return new Responses("Id must be positive and less than 15", Status.BAD_REQUEST);
//                }
//            } catch (NumberFormatException ex) {
//                return new Responses("Id must be numeric", Status.BAD_REQUEST);
//            }
//
//            Storage_Passenger storage = Storage_Passenger.getInstance();
//
//            if (storage.getPassenger(idp) != null) {
//                return new Responses("Passenger with this ID already exists", Status.BAD_REQUEST);
//            }
//
//            if (firstname==null || firstname.trim().isEmpty()) {
//                return new Responses("Firstname must be not empty", Status.BAD_REQUEST);
//            }
//
//            if (lastname==null || lastname.trim().isEmpty()) {
//                return new Responses("Lastname must be not empty", Status.BAD_REQUEST);
//            }
//
//            LocalDate now = LocalDate.now();
//            if (birthDate == null || birthDate.isAfter(now) || birthDate.isBefore(LocalDate.of(1900, 1, 1))) {
//                return new Responses("Birthdate must be valid and between (1900 - today)", Status.BAD_REQUEST);
//            }
//
//            //Consulte y los aeropuertos tiene como fecha limite el inicio del siglo 20, incluso algunos tienen minimo hasta 1950. Interesante.
//            // I consulted and the airports have as deadline the beginning of the 20th century, even some have at least until 1950. Interesting.
//            if (phoneCode < 0 || String.valueOf(phoneCode).length() > 3 || pc==null) {
//                return new Responses("The telephone code must be a minimum of 0 and a maximum of 3 digits.", Status.BAD_REQUEST);
//            }
//
//            if (phone < 0 || String.valueOf(phone).length() > 11 || p == null) {
//                return new Responses("Phone must be a minimun of 0 and maximun of 11 digits", Status.BAD_REQUEST);
//            }
//            
//            Passenger passenger = new Passenger(idp, firstname, lastname, birthDate, phoneCode, phone, country);
//            boolean added = storage.addPassenger(passenger);
//            if (!added) {
//                return new Responses("Passenger with this ID already exists", Status.BAD_REQUEST);
//            }
//
//            return new Responses("Person created successfully", Status.CREATED);
//        } catch (Exception ex) {
//            return new Responses("Unexpected error", Status.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    public static Responses createPassenger(Long idp, String firstname, String lastname, LocalDate birthDate, Integer phoneCode, Long phone, String country) {
        try {
            // Validación del ID (debe ser positivo y menor de 15 caracteres)
            if (idp == null || idp <= 0 || String.valueOf(idp).length() > 15) {
                return new Responses("ID must be a positive number and less than 15 characters", Status.BAD_REQUEST);
            }

            // Comprobar si el pasajero ya existe
            Storage_Passenger storage = Storage_Passenger.getInstance();
            if (storage.getPassenger(idp) != null) {
                return new Responses("Passenger with this ID already exists", Status.BAD_REQUEST);
            }

            // Validación de nombre y apellido
            if (firstname == null || firstname.trim().isEmpty()) {
                return new Responses("Firstname must not be empty", Status.BAD_REQUEST);
            }
            if (lastname == null || lastname.trim().isEmpty()) {
                return new Responses("Lastname must not be empty", Status.BAD_REQUEST);
            }

            // Validación de fecha de nacimiento
            LocalDate now = LocalDate.now();
            if (birthDate == null || birthDate.isAfter(now) || birthDate.isBefore(LocalDate.of(1900, 1, 1))) {
                return new Responses("Birthdate must be valid and between 1900 - today", Status.BAD_REQUEST);
            }

            // Validación de código de teléfono (1-3 dígitos)
            if (phoneCode == null || phoneCode < 0 || String.valueOf(phoneCode).length() > 3) {
                return new Responses("The telephone code must be between 1 and 3 digits.", Status.BAD_REQUEST);
            }

            // Validación del número de teléfono (1-11 dígitos)
            if (phone == null || phone < 0 || String.valueOf(phone).length() > 11) {
                return new Responses("Phone must be a minimum of 0 and a maximum of 11 digits", Status.BAD_REQUEST);
            }

            // Crear el nuevo pasajero
            Passenger passenger = new Passenger(idp, firstname, lastname, birthDate, phoneCode, phone, country);
            boolean added = storage.addPassenger(passenger);
            
            if (!added) {
                return new Responses("Passenger with this ID already exists", Status.BAD_REQUEST);
            }

            return new Responses("Passenger created successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Responses("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}


