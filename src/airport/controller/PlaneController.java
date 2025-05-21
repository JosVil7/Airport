/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airport.controller;

import airport.controller.utils.Responses;
import airport.controller.utils.Status;
import airport.models.Plane;
import airport.models.database.Storage_Plane;

/**
 *
 * @author Jose
 */
public class PlaneController {
    public static Responses createPlane(String id, String brand, String model, int maxCapacity, String airline) {
        try {

            try {
                if (id == null || id.length() != 7) {
                    return new Responses("Id debe tener exactamente 7 caracteres (2 letras mayúsculas y 5 dígitos)", Status.BAD_REQUEST);
                }

                // Convertimos el string en arreglo de caracteres
                String[] chars = id.split("");

                // Validar las dos primeras letras mayúsculas
                for (int i = 0; i < 2; i++) {
                    if (!chars[i].matches("[A-Z]")) {
                        return new Responses("Los primeros dos caracteres deben ser letras mayúsculas", Status.BAD_REQUEST);
                    }
                }

                // Validar los últimos cinco dígitos numéricos
                for (int i = 2; i < 7; i++) {
                    if (!chars[i].matches("[0-9]")) {
                        return new Responses("Los últimos cinco caracteres deben ser dígitos numéricos", Status.BAD_REQUEST);
                    }
                }
            } catch (NumberFormatException ex) {
                return new Responses("Id must be numeric", Status.BAD_REQUEST);
            }

            Storage_Plane storage = Storage_Plane.getInstance();

            if (storage.getPlane(id) != null) {
                return new Responses("Plane with this ID already exists", Status.BAD_REQUEST);
            }

            if (brand == null) {
                return new Responses("Brand must be not empty", Status.BAD_REQUEST);
            }

            if (model == null || model.trim().isEmpty()) {
                return new Responses("Model must be not empty", Status.BAD_REQUEST);
            }

            if (maxCapacity < 0) {
                return new Responses("Max capacity must be not empty.", Status.BAD_REQUEST);
            }

            if (airline == null) {
                return new Responses("Airline must be not empty", Status.BAD_REQUEST);
            }

            Plane plane = new Plane(id, brand, model, maxCapacity, airline);
            boolean added = storage.addPlane(plane);
            if (!added) {
                return new Responses("Plane with this ID already exists", Status.BAD_REQUEST);
            }

            return new Responses("Plane created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Responses("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
