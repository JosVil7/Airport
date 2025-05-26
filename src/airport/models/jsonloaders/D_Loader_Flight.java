/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.jsonloaders;

import airport.models.database.Storage_Flight;
import airport.models.database.interfaces.IFlightStorage;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;

/**
 *
 * @author USER
 */
public class D_Loader_Flight {
    public static void load_Flights(){
        try{
            String texto = new String(Files.readAllBytes(Paths.get("json/flights.json")));
            JSONArray array = new JSONArray(texto);
            IFlightStorage flightStorage = Storage_Flight.getInstance();
            flightStorage.cargarJSON(array);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
