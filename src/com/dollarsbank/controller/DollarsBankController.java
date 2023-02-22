package com.dollarsbank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dollarsbank.exception.InvalidAccountIdException;
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

			Account newAccount = new Account(userId, password, initialDeposit, customer);
			newAccount.addTransaction(String.format("Initial Deposit: $%.2f", initialDeposit));
			accounts.add(new Account(userId, password, initialDeposit, customer));
			ColorUtility.print("Account created!\n");
		} catch (InvalidOptionException e) {
			ColorUtility.print(ColorUtility.ANSI_RED, e.getMessage() + "\n");
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
			ColorUtility.print("Successfully logged in!\n");
		} catch (Exception e) {
			ColorUtility.print(ColorUtility.ANSI_RED, e.getMessage() + "\n");
			return false;
		}
		return true;
	}

	public void depositAmount() {
		BalancePrinter.print(currentAccount);

		try {
			ColorUtility.print("Deposit Amount:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			double depositAmount = OptionSelector.pickOption(5.00, 999999.00, "Invalid deposit amount. Must be between $5.00"
					+ " and $999999.00");
			
			currentAccount.setBalance(currentAccount.getBalance() + depositAmount);
			currentAccount.addTransaction(String.format("Deposited: $%.2f" + depositAmount));
			BalancePrinter.print(currentAccount);
		} catch (Exception e) {
			ColorUtility.print(ColorUtility.ANSI_RED, e.getMessage() + "\n");
		}
		
		ColorUtility.print("");
	}

	public void withdrawAmount() {
		BalancePrinter.print(currentAccount);

		try {
			ColorUtility.print("Withdraw Amount:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			double withdrawAmount = OptionSelector.pickOption(0.01, currentAccount.getBalance(), 
					"Invalid withdraw amount. Must be between $0.01 and $" + currentAccount.getBalance());
			
			currentAccount.setBalance(currentAccount.getBalance() - withdrawAmount);
			currentAccount.addTransaction(String.format("Withdrew $%.2f", withdrawAmount));
			BalancePrinter.print(currentAccount);
		} catch (Exception e) {
			ColorUtility.print(ColorUtility.ANSI_RED, e.getMessage() + "\n");
		}

		ColorUtility.print("");
	}

	public void fundsTransfer() {
		BalancePrinter.print(currentAccount);

		try {
			// Look for account by userId
			ColorUtility.print(ColorUtility.ANSI_BLUE, PrettyFormatter.format("Enter the id of the account you "
					+ "want to transfer money to"));
			ColorUtility.print("Account Id:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			String accountId = OptionSelector.pickOption(".*", "Invalid account id. Try again!");
			
			// Get transfer amount
			ColorUtility.print("Amount to Transfer:");
			ColorUtility.setConsoleInputColor(ColorUtility.ANSI_GREEN);
			double transferAmount = OptionSelector.pickOption(0.01, currentAccount.getBalance(), 
					"Invalid transfer amount. Must be between $0.01 and $" + currentAccount.getBalance());
	
			// Find user account
			Optional<Account> userAccount = accounts.stream().filter((account) -> {
				return accountId.equals(account.getUserId());
			}).findFirst();
		
			if (userAccount.isEmpty()) {
				throw new InvalidAccountIdException("Could not find account id. Try again.");
			}
			
			// Add transaction to history
			currentAccount.addTransaction(String.format("Transfered $%.2f", transferAmount) + " to account [" + accountId + "]");
			
			// Display balance
			BalancePrinter.print(currentAccount);
		} catch (Exception e) {
			ColorUtility.print(ColorUtility.ANSI_RED, e.getMessage() + "\n");
		}
	}

	public void viewRecentTransactions() {
		TransactionPrinter.print(currentAccount);
	}

	public void displayCustomerInformation() {
		CustomerPrinter.print(currentAccount.getCustomer());
	}
	
	public void logout() {
		currentAccount = null;
		ColorUtility.print("\nSuccessfully logged out.\n");
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
}
