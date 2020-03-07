package com.techelevator.parks.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Scanner;

public class Menu {
	
	private PrintWriter out;
	private Scanner in;
	
	private static final String DATE_FORMAT = "MM/dd/yyyy";

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	
	public LocalDate getDateFromUserInput(String prompt) {
		LocalDate output = null;
		
		System.out.println(prompt);
		
		String userInput = in.nextLine();
		
		try {
			output = new SimpleDateFormat(DATE_FORMAT).parse(userInput).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return output;
	}
	
	public String getStringFromUserInput(String prompt) {
		
		System.out.println(prompt);
		
		String output = in.nextLine();
		
		return output;
	}
	
	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println("\t"+optionNum+") "+options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
}