/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.jsonloaders;

import airport.models.database.Storage_Location;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;

/**
 *
 * @author USER
 */
public class D_Loader_Location {
    public static void load_Locations(){
        try{
            String texto = new String(Files.readAllBytes(Paths.get("json/locations.json")));
            JSONArray array = new JSONArray(texto);
            Storage_Location.getInstance().cargarJSON(array);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
