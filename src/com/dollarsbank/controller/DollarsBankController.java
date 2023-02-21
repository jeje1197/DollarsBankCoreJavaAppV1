package com.dollarsbank.controller;

import com.dollarsbank.exception.InvalidOptionException;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorPrinter;
import com.dollarsbank.utility.OptionSelector;
import com.dollarsbank.utility.PrettyFormatter;

public class DollarsBankController {
	Account userAccount;

	public void createNewAccount() {
		ColorPrinter.print(ColorPrinter.ANSI_BLUE, PrettyFormatter.format("Enter Details For New Account"));
		try {
			// Get customer information
			ColorPrinter.print("Customer Name:\n");
			String customerName = OptionSelector.pickOption("[a-zA-Z]*(\s[a-zA-Z])", "Invalid name entry.");
			
//			ColorPrinter.print("Customer Address:\n");
//			String customerAddress = OptionSelector.pickOption("[a-zA-Z]*(\s[a-zA-Z])", "Invalid name entry.");
//			
//			ColorPrinter.print("Customer Contact Number:\n");
//			String customerPhoneNumber = OptionSelector.pickOption("[0-9]{10-13}", "Invalid phone number entry.");
//			Customer customer = new Customer(customerName, customerAddress, customerPhoneNumber);
//			
//			// Get account information
//			ColorPrinter.print("User Id:\n");
//			String userId = OptionSelector.pickOption("[a-zA-Z_][a-zA-Z_0-9]", "Username must be at least 6 characters long and cannot contain any spaces"
//					+ " or special characters.");
//			
//			ColorPrinter.print("Password:\n");
//			String password = OptionSelector.pickOption(userId, "Password must ");
//			double initialDeposit = OptionSelector.pickOption(5.00, 999999.00, "Invalid initial deposit amount. Must be between $5.00"
//					+ " and $999999.00");
//
//			userAccount = new Account(userId, password, initialDeposit, customer);
		} catch (InvalidOptionException e) {
			ColorPrinter.print(ColorPrinter.ANSI_RED, e.getMessage());
		}
	}

	public void login() {

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
