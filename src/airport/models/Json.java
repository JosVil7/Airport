///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package airport.models;
//
//import java.io.File;
//import  com.fasterxml.jackson.databind.ObjectMapper;
//import java.awt.List;
//import java.util.Map;
//public class Json{
//
//    
//    
//    ObjectMapper mapper = new ObjectMapper();
//    File file = new File("passengers.json");
//    
//    List<Map<String, Object>> lista = mapper.readValue(file, List.class);
//    public static ArrayList<Passenger> readPassengers(String path) throws IOException {
//        String content = Files.readString(Paths.get(path), StandardCharsets.UTF_8);
//        JSONArray array = new JSONArray(content);
//
//        ArrayList<Passenger> list = new ArrayList<>();
//        for (int i = 0; i < array.length(); i++) {
//            JSONObject obj = array.getJSONObject(i);
//
//            long id = obj.getLong("id");
//            String firstname = obj.getString("firstname");
//            String lastname = obj.getString("lastname");
//            LocalDate birthDate = LocalDate.parse(obj.getString("birthDate"));
//            int countryPhoneCode = obj.getInt("countryPhoneCode");
//            int phone = obj.getInt("phone");
//            String country = obj.getString("country");
//
//            crear objeto tipo passenger y guardar en la lista para devolverla
//            Passenger p = new Passenger(id, firstname, lastname, birthDate, countryPhoneCode, phone, country);
//            list.add(p);
//        }
//
//        return list;
//    }
//}
