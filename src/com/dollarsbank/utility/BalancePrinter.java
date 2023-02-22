package com.dollarsbank.utility;

import com.dollarsbank.model.Account;

public class BalancePrinter {
	public static void print(Account account) {
		ColorUtility.print(String.format("Current Balance: $%.2f", account.getBalance()));
	}
}
