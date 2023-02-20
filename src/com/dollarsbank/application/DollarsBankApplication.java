package com.dollarsbank.application;

import com.dollarsbank.utility.ColorPrinter;
import com.dollarsbank.utility.OptionSelector;

public class DollarsBankApplication {
	
	public static void main(String[] args) throws Exception {
		printWelcome();
		printOptions();
		ColorPrinter.print(ColorPrinter.ANSI_GREEN,  "Enter choice (1, 2, or 3):\n");
		int selectedOption = -1; 
		try {
			selectedOption = OptionSelector.pickOption(1, 3, "Invalid Option: try again.");
		} catch (Exception e) {
			ColorPrinter.print(ColorPrinter.ANSI_RED, null);
		}
	}
	
	private static void printWelcome() throws Exception {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("+---------------------------+\n");
		stringBuilder.append("| DOLLARSBANK WELCOMES YOU! |\n");
		stringBuilder.append("+---------------------------+\n");
		ColorPrinter.print(ColorPrinter.ANSI_BLUE, stringBuilder.toString());
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
