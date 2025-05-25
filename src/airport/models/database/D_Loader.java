/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.database;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;

/**
 *
 * @author USER
 */
public class D_Loader {
    public static void load_Planes(){
        try{
            String texto = new String(Files.readAllBytes(Paths.get("json/planes.json")));
            JSONArray array = new JSONArray(texto);
            Storage_Plane.getInstance().cargarJSON(array);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void load_Locations(){
        try{
            String texto = new String(Files.readAllBytes(Paths.get("json/locations.json")));
            JSONArray array = new JSONArray(texto);
            Storage_Location.getInstance().cargarJSON(array);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void load_Passengers(){
        try{
            String texto = new String(Files.readAllBytes(Paths.get("json/passengers.json")));
            JSONArray array = new JSONArray(texto);
            Storage_Passenger.getInstance().cargarJSON(array);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void load_Flights(){
        try{
            String texto = new String(Files.readAllBytes(Paths.get("json/flights.json")));
            JSONArray array = new JSONArray(texto);
            Storage_Flight.getInstance().cargarJSON(array);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void loadALL(){
        load_Planes();
        load_Locations();
        load_Passengers();
        load_Flights();
    }
}
