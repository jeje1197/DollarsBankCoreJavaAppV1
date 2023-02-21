package com.dollarsbank.utility;

import com.dollarsbank.model.Customer;

public class CustomerPrinter {
	public static void print(Customer customer) {
		ColorUtility.print(ColorUtility.ANSI_BLUE, PrettyFormatter.format("Customer Information:"));
		ColorUtility.print("Name: " + customer.getName());
		ColorUtility.print("Address: " + customer.getAddress());
		ColorUtility.print("Phone Number: " + customer.getPhoneNumber());
	}
}
