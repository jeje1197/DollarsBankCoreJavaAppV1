package com.dollarsbank.utility;

import java.util.List;

import com.dollarsbank.model.Account;

public class TransactionPrinter {
	public static void print(Account account) {
		ColorUtility.print(ColorUtility.ANSI_BLUE, PrettyFormatter.format("5 Recent Transactions:"));
		List<String> transactionHistory = account.getTransactionHistory();
		for (int i = 0; i < transactionHistory.size(); i++) {
			ColorUtility.print(i+1 + ": " + transactionHistory.get(i));
		}
		ColorUtility.print("");
	}
}
