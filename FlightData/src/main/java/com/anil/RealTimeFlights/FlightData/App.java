package com.anil.RealTimeFlights.FlightData;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import com.anil.util.ColorOutput;



public class App {
	

	
    public static void main( String[] args ) throws IOException, ParseException, org.apache.commons.cli.ParseException
    {
    	
    	
    	ArrayList<String> countries = new ArrayList<>();
    	countries.add("Germany");
    	countries.add("Switzerland");
    	Options options = new Options();
    	
    	options.addOption("v", false, "display version number");
    	
    	
    	Option help = new Option("help", "print this message");
    	Option version = new Option("version", "Show version number");
    	options.addOption(help);
    	options.addOption(version);
    	CommandLineParser parser = new DefaultParser();
    	CommandLine cmd = parser.parse(options, args);
    	
    	if(cmd.hasOption("v")) {
    		System.out.println("FlightData Version v0.1");
    	}
    	
    	HelpFormatter formatter = new HelpFormatter();
    	formatter.printHelp("FlightData <country>", options);
    	System.out.println("Entered country is: "+args[0]);
    	String userInput = args[0];
    	if(!countries.contains(userInput)) {
    		ColorOutput.errorMessage("Country not supported yet");
    	}
    	else {
    		FlightAPIParser f = new FlightAPIParser();
    		ArrayList<JSONArray> j = f.getFlightData();
    		//System.out.println(j);
    		ArrayList<String> cs = f.getCallSigns(j);
    		System.out.println(cs);
    	}
    	
    
    	
    	
        
}
    
}
