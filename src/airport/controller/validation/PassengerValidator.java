/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.validation;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 *
 * @author Jose
 */
public class PassengerValidator {

  
    public static Responses validateCreate(String idp, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        // ID Validation
        if (idp == null || idp.trim().isEmpty()) {
            return new Responses("Passenger ID must not be empty.", Status.BAD_REQUEST);
        }
        if (!idp.matches("\\d+")) {
            return new Responses("Passenger ID must contain only numbers.", Status.BAD_REQUEST);
        }
        if (idp.length() > 15) {
            return new Responses("Passenger ID must have at most 15 digits.", Status.BAD_REQUEST);
        }
        try {
            long id = Long.parseLong(idp);
            if (id < 0) {
                return new Responses("Passenger ID must be greater than or equal to 0.", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            // This case is already covered by the regex, but good for robustness.
            return new Responses("Invalid Passenger ID format. Must be a valid number.", Status.BAD_REQUEST);
        }

        // Name Fields Validation
        if (firstname == null || firstname.trim().isEmpty()) {
            return new Responses("First name must not be empty.", Status.BAD_REQUEST);
        }
        if (lastname == null || lastname.trim().isEmpty()) {
            return new Responses("Last name must not be empty.", Status.BAD_REQUEST);
        }

        // Birthdate Fields Presence Validation
        if (year == null || year.trim().isEmpty() || month == null || month.trim().isEmpty() || day == null || day.trim().isEmpty()) {
            return new Responses("Birthdate fields (Year, Month, Day) must not be empty.", Status.BAD_REQUEST);
        }
        
        // Phone Code Validation
        if (phoneCode == null || phoneCode.trim().isEmpty()) {
            return new Responses("Phone code must not be empty.", Status.BAD_REQUEST);
        }
        if (!phoneCode.matches("\\d+")) {
            return new Responses("Phone code must contain only numbers.", Status.BAD_REQUEST);
        }
        if (phoneCode.length() > 3) {
            return new Responses("Phone code must have at most 3 digits.", Status.BAD_REQUEST);
        }
        try {
            int code = Integer.parseInt(phoneCode);
            if (code < 0) {
                return new Responses("Phone code must be greater than or equal to 0.", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Responses("Invalid Phone code format. Must be a valid number.", Status.BAD_REQUEST);
        }

        // Phone Number Validation
        if (phone == null || phone.trim().isEmpty()) {
            return new Responses("Phone number must not be empty.", Status.BAD_REQUEST);
        }
        if (!phone.matches("\\d{1,11}")) {
            return new Responses("Phone number must contain only digits and have at most 11 digits.", Status.BAD_REQUEST);
        }
        try {
            long number = Long.parseLong(phone);
            if (number < 0) {
                return new Responses("Phone number must be greater than or equal to 0.", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Responses("Invalid Phone number format. Must be a valid number.", Status.BAD_REQUEST);
        }

        // Country Validation
        if (country == null || country.trim().isEmpty()) {
            return new Responses("Country must not be empty.", Status.BAD_REQUEST);
        }

        return null; 
    }
    
    public static Responses validateBirthDate(int year, int month, int day) {
        try {
            LocalDate birthDate = LocalDate.of(year, month, day);
            LocalDate today = LocalDate.now();
            LocalDate minDate = LocalDate.of(1900, 1, 1);

            if (birthDate.isAfter(today)) {
                return new Responses("Birthdate cannot be in the future.", Status.BAD_REQUEST);
            }
            if (birthDate.isBefore(minDate)) {
                return new Responses("Birthdate must be after January 1, 1900.", Status.BAD_REQUEST);
            }
        } catch (DateTimeException e) {
            return new Responses("Invalid birthdate. Please ensure the year, month, and day form a valid date.", Status.BAD_REQUEST);
        }
        return null;
    }
}
