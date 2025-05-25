/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller.interfaces;

import airport.controller.utils.Responses;

/**
 *
 * @author Jose
 */
public interface IPassengerService {
    Responses createPassenger(String idp, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country);
    Responses updatePassenger(String idup, String firstname, String lastname, String year, String month, String day, String phoneCode, String phone, String country);
    Responses getPassengerById(String id); 
    Responses getAllPassengers(); 
}
