/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.jsonloaders;

import static airport.models.jsonloaders.D_Loader_Flight.load_Flights;
import static airport.models.jsonloaders.D_Loader_Location.load_Locations;
import static airport.models.jsonloaders.D_Loader_Passenger.load_Passengers;
import static airport.models.jsonloaders.D_Loader_Plane.load_Planes;


/**
 *
 * @author USER
 */
public class D_Loader_All {
    public static void loadALL(){
        load_Planes();
        load_Locations();
        load_Passengers();
        load_Flights();
    }
}
