package com.techelevator.parks.view;

import java.util.List;


import com.techelevator.parks.model.Park;

public class Display {

	private static String divider = "------------------------------------------------------------";
	
	public static void printParkInfo(Park selectedPark) {
		System.out.println(selectedPark.getName());
		System.out.println(selectedPark.getLocation());
		System.out.println(selectedPark.getEstablishedDate());
		System.out.println(selectedPark.getArea());
		System.out.println(selectedPark.getAnnualVisitors());
		System.out.println(selectedPark.getDescription());
	}
	
} 
