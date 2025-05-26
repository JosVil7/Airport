/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.jsonloaders;

import airport.models.database.Storage_Passenger;
import airport.models.database.interfaces.IPassengerStorage;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;

/**
 *
 * @author USER
 */
public class D_Loader_Passenger {
    public static void load_Passengers(){
        try{
            String texto = new String(Files.readAllBytes(Paths.get("json/passengers.json")));
            JSONArray array = new JSONArray(texto);
            IPassengerStorage passengerStorage = Storage_Passenger.getInstance();
            passengerStorage.cargarJSON(array);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
