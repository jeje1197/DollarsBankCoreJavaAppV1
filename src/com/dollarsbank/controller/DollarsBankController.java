package com.dollarsbank.controller;

import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.exception.InvalidOptionException;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorUtility;
import com.dollarsbank.utility.OptionSelector;
import com.dollarsbank.utility.PrettyFormatter;

public class DollarsBankController {
	private List<Account> accounts = new ArrayList<>();
	private Account currentAccount;

	public void createNewAccount() {
		ColorUtility.print(ColorUtility.ANSI_BLUE, PrettyFormatter.format("Enter Details For New Account"));
		try {
			// Get customer information
			ColorUtility.print("Customer Name:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			String customerName = OptionSelector.pickOption("[a-zA-Z]*(\s[a-zA-Z]*)*", "Invalid name entry.");
			
			ColorUtility.print("Customer Address:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			String customerAddress = OptionSelector.pickOption("[0-9]+(\\s[a-zA-Z]*)*\\.?", "Invalid address entry.");
			
			ColorUtility.print("Customer Contact Number:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			String customerPhoneNumber = OptionSelector.pickOption("[0-9\\-]{10,13}", "Invalid phone number entry.");
			Customer customer = new Customer(customerName, customerAddress, customerPhoneNumber);
			
			// Get account information
			ColorUtility.print("User Id:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			String userId = OptionSelector.pickOption("[a-zA-Z_][a-zA-Z_0-9]{5,20}", "Username must be at least 6 characters long and cannot contain any spaces"
					+ " or special characters.");
			
			ColorUtility.print("Password:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			String password = OptionSelector.pickOption("[a-zA-Z_0-9!\\.]{8,20}", "Password must be at least 8 characters long and cannot"
					+ " contain any spaces or special characters.");
			
			ColorUtility.print("Initial Deposit:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			double initialDeposit = OptionSelector.pickOption(5.00, 999999.00, "Invalid initial deposit amount. Must be between $5.00"
					+ " and $999999.00");

			accounts.add(new Account(userId, password, initialDeposit, customer));
		} catch (InvalidOptionException e) {
			ColorUtility.print(ColorUtility.ANSI_RED, e.getMessage());
		}
	}

	public void login() {
		accounts.forEach((account) -> {
			System.out.println(account);
		});
		ColorUtility.print(ColorUtility.ANSI_BLUE, PrettyFormatter.format("Enter Details For New Account"));
	}

	public void depositAmount() {

	}

	public void withdrawAmount() {

	}

	public void fundsTransfer() {

	}

	public void viewRecentTransactions() {

	}

	public void displayCustomerInformation() {

	}
}
