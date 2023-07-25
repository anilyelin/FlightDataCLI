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
			System.out.println(String.valueOf(i)+" "+arr.get(i));
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
	
	public ArrayList<Double> getLongitude(ArrayList<JSONArray> data){
		ArrayList<Double> results = new ArrayList<>();
		for(int i=0;i<data.size();i++) {
			JSONArray tmp = (JSONArray) data.get(i);
			Double longitude = (Double) tmp.get(5);
			results.add(longitude);
		}
		return results;
	}
	
	public ArrayList<Double> getLatitude(ArrayList<JSONArray> data){
		ArrayList<Double> results = new ArrayList<>();
		for(int i=0;i<data.size();i++) {
			JSONArray tmp = (JSONArray) data.get(i);
			Double latitude = (Double) tmp.get(6);
			results.add(latitude);
		}
		return results;
	}
	
	public void aggregateData(ArrayList<String> flightNumber, ArrayList<String> originCountry, ArrayList<Double> longitude, ArrayList<Double> lat) {
		int arrLength1 = flightNumber.size();
		int arrLength2 = originCountry.size();
		int arrLength3 = longitude.size();
		int arrLength4 = lat.size();
		if(arrLength1 != arrLength2  || arrLength1 != arrLength3 || arrLength1 != arrLength4 || arrLength2 != arrLength3 || arrLength2 != arrLength4 ){
			System.err.print("[ERROR] Arrays do not have the same size!");
		}
		//System.out.println("Record     Callsign     Origin Country     Longitude     Latitude");
		for(int i=0;i<arrLength1;i++) {
			
			//System.out.println("Record ["+Integer.valueOf(i)+"] "+flightNumber.get(i)+"     "+originCountry.get(i)+"                  "+longitude.get(i)+"     "+lat.get(i));
			System.out.format("Record %2d - Flight Number %8s - Origin Country %30s - Position (%f,%f)\n",i, flightNumber.get(i), originCountry.get(i), longitude.get(i), lat.get(i));
		}
		
	}
	
	public static void main(String[] args) throws IOException, ParseException {
		FlightAPIParser f = new FlightAPIParser();
		ArrayList<JSONArray> j = f.getFlightData();
		//System.out.println(j);
		ArrayList<String> cs = f.getCallSigns(j);
		//System.out.println(cs);
		ArrayList<String> countries = f.getCountries(j);
		//System.out.println(countries);
		ArrayList<Double> longitude = f.getLongitude(j);
		System.out.println(longitude);
		ArrayList<Double> latitude = f.getLatitude(j);
		System.out.println(latitude);
		f.aggregateData(cs, countries, longitude, latitude);
	}
	
	

}
