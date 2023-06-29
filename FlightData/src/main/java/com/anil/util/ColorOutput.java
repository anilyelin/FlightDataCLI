package com.anil.util;

import java.util.ArrayList;

public class ColorOutput {
	
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
