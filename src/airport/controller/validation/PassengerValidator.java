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

    //validamos primero para luego crear
    public static Responses validateCreate(String idp, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        if (idp == null || idp.trim().isEmpty()) {
            return new Responses("Id must not be empty", Status.BAD_REQUEST);
        }
        if (!idp.matches("\\d+")) {
            return new Responses("ID must contain only numbers", Status.BAD_REQUEST);
        }
        if (year == null || year.trim().isEmpty() || month == null || month.trim().isEmpty() || day == null || day.trim().isEmpty()) {
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
        if (!phone.matches("\\d{1,11}")) {
            return new Responses("Phone must contain only digits and max 11 digits", Status.BAD_REQUEST);
        }
        if (country == null || country.trim().isEmpty()) {
            return new Responses("Country must not be empty", Status.BAD_REQUEST);
        }

        return null;
    }

    public static Responses validateBirthDate(int year, int month, int day) {
        try {
            LocalDate birthDate = LocalDate.of(year, month, day);
            if (birthDate.isAfter(LocalDate.now()) || birthDate.isBefore(LocalDate.of(1900, 1, 1))) {
                return new Responses("Birthdate must be between 1900 and today", Status.BAD_REQUEST);
            }
        } catch (DateTimeException e) {
            return new Responses("Invalid birthdate", Status.BAD_REQUEST);
        }
        return null;
    }
}
