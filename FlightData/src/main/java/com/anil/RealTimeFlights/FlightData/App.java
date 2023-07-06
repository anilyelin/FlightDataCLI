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
    	//Entry point of this program starting with an ascii art title
    	String asciiArt = "    _________       __    __  ____        __                 ____   ___\n"
    			+ "   / ____/ (_)___ _/ /_  / /_/ __ \\____ _/ /_____ _   _   __/ __ \\ <  /\n"
    			+ "  / /_  / / / __ `/ __ \\/ __/ / / / __ `/ __/ __ `/  | | / / / / / / / \n"
    			+ " / __/ / / / /_/ / / / / /_/ /_/ / /_/ / /_/ /_/ /   | |/ / /_/ / / /  \n"
    			+ "/_/   /_/_/\\__, /_/ /_/\\__/_____/\\__,_/\\__/\\__,_/    |___/\\____(_)_/   \n";
    	//System.out.println(asciiArt);
    	ColorOutput.coloredTextOutput(asciiArt ,"purple");
    	ArrayList<String> countries = new ArrayList<>();
    	countries.add("Germany");
    	countries.add("Switzerland");
    	Options options = new Options();
    	
    	options.addOption("v", false, "display version number");
    	
    	
    	Option help = new Option("help", "print this message");
    	Option flight = new Option("flight", "Show specific flight e.g. LH 1234");
    	options.addOption(help);
    	options.addOption(flight);
    	CommandLineParser parser = new DefaultParser();
    	CommandLine cmd = parser.parse(options, args);
    	
    	if(cmd.hasOption("v")) {
    		System.out.println("FlightData Version v0.1");
    	}
    	
    	HelpFormatter formatter = new HelpFormatter();
    	String helpString = "FlightData <country> \nCurrenlty supported countries are [Germany, Switzerland]";
    	formatter.printHelp(helpString, options);
    	String userInput;
    	try {
    		userInput = args[0];
    		System.out.println("Entered country is: "+args[0]);}
    	catch(Exception e) {
    		userInput = "";
    		formatter.printHelp(helpString, options);
    	}
    	//String userInput = args[0];
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
