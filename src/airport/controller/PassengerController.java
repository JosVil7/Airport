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

    

    private static IPassengerService passengerService = new PassengerService(); 

    
    public static void setPassengerService(IPassengerService service) {
        passengerService = service;
    }

    
    public static Responses createPassenger(String idp, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        return passengerService.createPassenger(idp, firstname, lastname, year, month, day, phoneCode, phone, country);
    }

   
    public static Responses updatePassenger(String idup, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country) {
        return passengerService.updatePassenger(idup, firstname, lastname, year, month, day, phoneCode, phone, country);
    }

    
    public static Responses getPassengerById(String id) {
        return passengerService.getPassengerById(id);
    }

    
    public static Responses getAllPassengers() {
        return passengerService.getAllPassengers();
    }

}
