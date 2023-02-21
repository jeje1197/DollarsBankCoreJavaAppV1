package com.dollarsbank.application;

import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.exception.InvalidOptionException;
import com.dollarsbank.utility.ColorUtility;
import com.dollarsbank.utility.OptionSelector;
import com.dollarsbank.utility.PrettyFormatter;

public class DollarsBankApplication {
	
	public static void main(String[] args){
		DollarsBankController controller = new DollarsBankController();
		boolean applicationRunning = true;

		while (applicationRunning) {
			printWelcome();
			printOptions();

			int selectedOption = -1; 
			try {
				ColorUtility.print(ColorUtility.ANSI_GREEN,  "Enter choice (1, 2, or 3):");
				selectedOption = OptionSelector.pickOption(1, 3, "Invalid Option: try again.");
			} catch (InvalidOptionException e) {
				ColorUtility.print(ColorUtility.ANSI_RED, (e.getMessage()));
			}

			switch(selectedOption) {
			case 1:
				controller.createNewAccount();
				break;
			case 2:
				controller.login();
				break;
			case 3:
				applicationRunning = false;
				break;
			default:
				continue;
			}
		}
		
		OptionSelector.close();
	}
	
	private static void printWelcome(){
		ColorUtility.print(ColorUtility.ANSI_BLUE, PrettyFormatter.format("DOLLARSBANK WELCOMES YOU!"));
	}
	
	private static void printOptions() {
		String[] menuOptions = new String[] {
			"1. Create New Account",
			"2. Login",
			"3. Exit"
		};
		System.out.println(String.join("\n", menuOptions) + "\n");
	}
}
