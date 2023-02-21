package com.dollarsbank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dollarsbank.exception.InvalidCredentialsException;
import com.dollarsbank.exception.InvalidOptionException;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.BalancePrinter;
import com.dollarsbank.utility.ColorUtility;
import com.dollarsbank.utility.CustomerPrinter;
import com.dollarsbank.utility.OptionSelector;
import com.dollarsbank.utility.PrettyFormatter;
import com.dollarsbank.utility.TransactionPrinter;

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
			ColorUtility.print("Account created!");
		} catch (InvalidOptionException e) {
			ColorUtility.print(ColorUtility.ANSI_RED, e.getMessage());
		}
	}

	public boolean login() {
		try {
			ColorUtility.print(ColorUtility.ANSI_BLUE, PrettyFormatter.format("Enter Login Details"));
			ColorUtility.print("UserId:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			String userId = OptionSelector.pickOption(".*", "Invalid Credentials. Try again!");
			
			ColorUtility.print("Password:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			String password = OptionSelector.pickOption(".*", "Invalid Credentials. Try again!");
			
			Optional<Account> userAccount = accounts.stream().filter((account) -> {
				return userId.equals(account.getUserId()) && password.equals(account.getPassword());
			}).findFirst();
			
			if (userAccount.isEmpty()) {
				throw new InvalidCredentialsException("Invalid Credentials. Try again!");
			}
			
			currentAccount = userAccount.get();
			ColorUtility.print("Successfully logged in!");
		} catch (Exception e) {
			ColorUtility.print(ColorUtility.ANSI_RED, e.getMessage());
			return false;
		}
		return true;
	}

	public void depositAmount() {
		try {
			ColorUtility.print("Deposit Amount:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			double depositAmount = OptionSelector.pickOption(5.00, 999999.00, "Invalid deposit amount. Must be between $5.00"
					+ " and $999999.00");
			
			currentAccount.setBalance(currentAccount.getBalance() + depositAmount);
			BalancePrinter.print(currentAccount);
		} catch (Exception e) {
			ColorUtility.print(ColorUtility.ANSI_RED, e.getMessage());
		}
	}

	public void withdrawAmount() {
		try {
			ColorUtility.print("Withdraw Amount:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			double withdrawAmount = OptionSelector.pickOption(1.00, currentAccount.getBalance(), 
					"Invalid withdraw amount. Must be between $1.00 and $" + currentAccount.getBalance());
			
			currentAccount.setBalance(currentAccount.getBalance() - withdrawAmount);
			BalancePrinter.print(currentAccount);
		} catch (Exception e) {
			ColorUtility.print(ColorUtility.ANSI_RED, e.getMessage());
		}
	}

	public void fundsTransfer() {
		
	}

	public void viewRecentTransactions() {
		TransactionPrinter.print(currentAccount);
	}

	public void displayCustomerInformation() {
		CustomerPrinter.print(currentAccount.getCustomer());
	}
	
	public void logout() {
		currentAccount = null;
		ColorUtility.print("Successfully logged out.");
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
}
