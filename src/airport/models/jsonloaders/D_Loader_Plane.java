/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.models.jsonloaders;

import airport.models.database.Storage_Flight;
import airport.models.database.Storage_Location;
import airport.models.database.Storage_Passenger;
import airport.models.database.Storage_Plane;
import airport.models.database.interfaces.IPlaneStorage;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;

/**
 *
 * @author USER
 */
public class D_Loader_Plane {
    public static void load_Planes(){
        try{
            String texto = new String(Files.readAllBytes(Paths.get("json/planes.json")));
            JSONArray array = new JSONArray(texto);
            IPlaneStorage planeStorage = Storage_Plane.getInstance();
            planeStorage.cargarJSON(array);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
