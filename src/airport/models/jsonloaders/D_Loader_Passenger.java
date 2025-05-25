/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.jsonloaders;

import airport.models.database.Storage_Passenger;
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
            Storage_Passenger.getInstance().cargarJSON(array);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
