package com.dollarsbank.utility;

import com.dollarsbank.model.Account;

public class BalancePrinter {
	public static void print(Account account) {
		ColorUtility.print("Balance: $" + account.getBalance());
	}
}
