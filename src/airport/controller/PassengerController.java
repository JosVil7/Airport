/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.interfaces.IPassengerService;
import airport.controller.service.PassengerService;
import airport.controller.utils.Responses;

/**
 *
 * @author Jose
 */
public class PassengerController {

    /*
    Con esta forma en la que se organizan los controladores permite la opcion de open source, ya que en esta clase de Controller se podrian agregar nuevas 
    clases y paquetes para no cambiar el codigo que ya funciona. Ademas no se puede implementar de forma directa todo SOLID por falta de recursos como interfaces
    para todo tipo
    */
    

    private static IPassengerService passengerService = new PassengerService(); 

    // Setter for dependency injection (useful for testing)
    public static void setPassengerService(IPassengerService service) {
        passengerService = service;
    }

    /**
     * Handles the creation of a new passenger.
     * @param idp Passenger ID.
     * @param firstname Passenger's first name.
     * @param lastname Passenger's last name.
     * @param year Birth year.
     * @param month Birth month.
     * @param day Birth day.
     * @param phoneCode Phone country code.
     * @param phone Phone number.
     * @param country Passenger's country.
     * @return A Responses object indicating the outcome of the operation.
     */
    public static Responses createPassenger(String idp, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        return passengerService.createPassenger(idp, firstname, lastname, year, month, day, phoneCode, phone, country);
    }

    /**
     * Handles the update of an existing passenger's information.
     * @param idup Passenger ID to update.
     * @param firstname New first name.
     * @param lastname New last name.
     * @param year New birth year.
     * @param month New birth month.
     * @param day New birth day.
     * @param phoneCode New phone country code.
     * @param phone New phone number.
     * @param country New country.
     * @return A Responses object indicating the outcome of the operation.
     */
    public static Responses updatePassenger(String idup, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        return passengerService.updatePassenger(idup, firstname, lastname, year, month, day, phoneCode, phone, country);
    }

    /**
     * Retrieves a passenger by their ID.
     * @param id Passenger ID.
     * @return A Responses object containing the Passenger if found, or an error message.
     */
    public static Responses getPassengerById(String id) {
        return passengerService.getPassengerById(id);
    }

    /**
     * Retrieves a list of all passengers.
     * @return A Responses object containing a List of Passengers.
     */
    public static Responses getAllPassengers() {
        return passengerService.getAllPassengers();
    }

}
