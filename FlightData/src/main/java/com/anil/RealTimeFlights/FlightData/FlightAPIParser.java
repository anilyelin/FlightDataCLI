package com.anil.RealTimeFlights.FlightData;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** this class will parse the flight data from the opensky JSON API**/

public class FlightAPIParser {
	
	private final String API_URL = "https://opensky-network.org/api/states/all?lamin=45.8389&lomin=5.9962&lamax=47.8229&lomax=10.5226";
	private final URL url;
	
	public FlightAPIParser() throws IOException {
		
		url = new URL(API_URL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		int responseCode = conn.getResponseCode();
		if(responseCode != 200){
			System.err.println("Error with response code: "+responseCode);
		}
	}
	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public ArrayList<JSONArray> getFlightData() throws IOException, ParseException {
		String inline = "";
		ArrayList<JSONArray> resultList = new ArrayList<>();
		Scanner scanner = new Scanner(this.url.openStream());
		while(scanner.hasNext()) {
			inline += scanner.nextLine();
		}
		scanner.close();
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(inline);
		JSONArray arr = (JSONArray) obj.get("states");
		for(int i=0;i<arr.size();i++) {
			System.out.println(String.valueOf(i)+"  "+arr.get(i));
			resultList.add((JSONArray) arr.get(i));
		}
		return resultList;
		
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public ArrayList<String> getCallSigns(ArrayList<JSONArray> data) {
		ArrayList<String> results = new ArrayList<>();
		for(int i=0;i<data.size();i++) {
			JSONArray tmp = (JSONArray) data.get(i);
			String callSign = (String) tmp.get(1);
			results.add(callSign);
		}
		return results;
		
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public ArrayList<String> getCountries(ArrayList<JSONArray> data) {
		ArrayList<String> results = new ArrayList<>();
		for(int i=0;i<data.size();i++) {
			JSONArray tmp = (JSONArray) data.get(i);
			String country = (String) tmp.get(2);
			results.add(country);
			
		}
		
		return results;
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		FlightAPIParser f = new FlightAPIParser();
		ArrayList<JSONArray> j = f.getFlightData();
		//System.out.println(j);
		ArrayList<String> cs = f.getCallSigns(j);
		//System.out.println(cs);
		ArrayList<String> countries = f.getCountries(j);
		System.out.println(countries);
	}
	
	

}
