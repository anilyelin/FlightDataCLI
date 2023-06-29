package com.anil.RealTimeFlights.FlightData;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSONSimpleWriter {
	
	@SuppressWarnings("unchecked")
	public static JSONObject createData(String name, String surname, int age) {
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		obj.put("surname", surname);
		obj.put("age", Integer.valueOf(age));
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONObject obj = new JSONObject();
		
		obj.put("name", "Anil Yelin");
		obj.put("age", Integer.valueOf(32));
		
		JSONArray cities = new JSONArray();
		cities.add("New York");
		cities.add("Bangalore");
		cities.add("Frankfurt");
		obj.put("cities", cities);
		
		try {
			FileWriter file = new FileWriter("test_data.json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(obj.toJSONString());
	}

}
