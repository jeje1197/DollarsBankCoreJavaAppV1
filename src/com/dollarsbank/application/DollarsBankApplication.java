package com.dollarsbank.application;

import java.util.List;

import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.exception.InvalidOptionException;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorUtility;
import com.dollarsbank.utility.OptionSelector;
import com.dollarsbank.utility.PrettyFormatter;

public class DollarsBankApplication {
	private static DollarsBankController controller = new DollarsBankController();
	
	public static void main(String[] args){
		loadTemplateData();
		landingPage();
	}
	
	private static void loadTemplateData() {
		List<Account> accounts = controller.getAccounts();
		accounts.add(new Account("sean", "password1", 3000.00, new Customer("Sean", "123 Jump St.", "9621238809")));
		accounts.add(new Account("user2", "veryhardtoguesspassword", 50.00, new Customer("joseph", "123 Jump St.", "9621238809")));
	}
	
	private static void landingPage() {
		boolean applicationRunning = true;

		while (applicationRunning) {
			ColorUtility.print(ColorUtility.ANSI_BLUE, PrettyFormatter.format("DOLLARSBANK WELCOMES YOU!"));
			String[] menuOptions = new String[] {
					"1. Create New Account",
					"2. Login",
					"3. Exit"
				};
			System.out.println(String.join("\n", menuOptions) + "\n");


			int selectedOption = -1; 
			try {
				ColorUtility.print(ColorUtility.ANSI_GREEN,  "Enter choice (1, 2 or 3):");
				selectedOption = OptionSelector.pickOption(1, 3, "Invalid Option: try again.");
			} catch (InvalidOptionException e) {
				ColorUtility.print(ColorUtility.ANSI_RED, (e.getMessage()));
			}
			ColorUtility.print("");

			switch(selectedOption) {
			case 1:
				controller.createNewAccount();
				break;
			case 2:
				if (controller.login()) {
					accountPage();
				}
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
	
	private static void accountPage() {
		boolean loggedIn = true;

		while (loggedIn) {
			ColorUtility.print(ColorUtility.ANSI_BLUE, PrettyFormatter.format("WELCOME Customer!!!"));
			String[] menuOptions = new String[] {
					"1. Deposit Amount",
					"2. Withdraw Amount",
					"3. Funds Transfer",
					"4. View 5 Recent Transactions",
					"5. Display Customer Information",
					"6. Sign Out"
				};
			System.out.println(String.join("\n", menuOptions) + "\n");


			int selectedOption = -1; 
			try {
				ColorUtility.print(ColorUtility.ANSI_GREEN,  "Enter choice (1, 2, 3, 4, 5 or 6):");
				selectedOption = OptionSelector.pickOption(1, 6, "Invalid Option: try again.");
			} catch (InvalidOptionException e) {
				ColorUtility.print(ColorUtility.ANSI_RED, (e.getMessage()));
			}

			switch(selectedOption) {
			case 1:
				controller.depositAmount();
				break;
			case 2:
				controller.withdrawAmount();
				break;
			case 3:
				controller.fundsTransfer();
				break;
			case 4:
				controller.viewRecentTransactions();
				break;
			case 5:
				controller.displayCustomerInformation();
				break;
			case 6:
				controller.logout();
				loggedIn = false;
				break;
			default:
				continue;
			}
		}
	}
	
}
