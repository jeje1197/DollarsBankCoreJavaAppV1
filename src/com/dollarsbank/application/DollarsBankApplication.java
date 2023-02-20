package com.dollarsbank.application;

import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.exception.InvalidOptionException;
import com.dollarsbank.utility.ColorPrinter;
import com.dollarsbank.utility.OptionSelector;
import com.dollarsbank.utility.PrettyFormatter;

public class DollarsBankApplication {
	
	public static void main(String[] args) throws Exception {
		boolean applicationRunning = true;
		
		printWelcome();
		while (applicationRunning) {
			printOptions();

			int selectedOption = -1; 
			try {
				ColorPrinter.print(ColorPrinter.ANSI_GREEN,  "Enter choice (1, 2, or 3):\n");
				selectedOption = OptionSelector.pickOption(1, 3, "Invalid Option: try again.");
			} catch (InvalidOptionException e) {
				ColorPrinter.print(ColorPrinter.ANSI_RED, e.getMessage());
				continue;
			}
			
			switch(selectedOption) {
			case 1:
				DollarsBankController.createNewAccount();
				break;
			case 2:
				DollarsBankController.login();
				break;
			case 3:
				applicationRunning = false;
				break;
			}
		}
	}
	
	private static void printWelcome() throws Exception {
		ColorPrinter.print(ColorPrinter.ANSI_BLUE, PrettyFormatter.format("DOLLARSBANK WELCOMES YOU!"));
	}
	
	private static void printOptions() throws Exception {
		String[] menuOptions = new String[] {
			"1. Create New Account",
			"2. Login",
			"3. Exit"
		};
		System.out.println(String.join("\n", menuOptions));
	}
}
