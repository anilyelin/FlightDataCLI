package com.anil.util;

import java.util.ArrayList;

public class ColorOutput {
	
	public static void coloredTextOutput(String str, String color) {
		switch(color) {
		case "red":
			System.out.println(Constants.ANSI_RED+str+Constants.ANSI_RESET);
			break;
		
		case "green":
			System.out.println(Constants.ANSI_GREEN+str+Constants.ANSI_RESET);
			break;
			
		case "yellow":
			System.out.println(Constants.ANSI_YELLOW+str+Constants.ANSI_RESET);
			break;
			
		case "purple":
			System.out.println(Constants.ANSI_PURPLE+str+Constants.ANSI_RESET);
			break;
			
		case "blue":
			System.out.println(Constants.ANSI_BLUE+str+Constants.ANSI_RESET);
			break;
			
		case "cyan":
			System.out.println(Constants.ANSI_CYAN+str+Constants.ANSI_RESET);
			break;
		default:
			System.err.print("Color choice not supported");
			
		}
		
	}
	
	public static void outputData(ArrayList<String> arr) {
		for(int i=0;i<arr.size();i++) {
			System.out.println(Constants.ANSI_GREEN+" hLLO "+Constants.ANSI_RESET);
		}
	}
	
	public static void errorMessage(String msg) {
		String tmp = Constants.ANSI_RED+msg+Constants.ANSI_RESET;
		System.out.println(tmp);
	}
	
	
	public static void main(String[] args) {
		ArrayList<String> test = new ArrayList<>();
		test.add("Hallo");
		test.add("Welt");
		test.add("Ciao");
		outputData(test);
	}

}
