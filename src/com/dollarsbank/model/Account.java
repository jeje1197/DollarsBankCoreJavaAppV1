package com.dollarsbank.model;

public class Account {
	String userId;
	String password;
	double balance;
	Customer customer;

	public Account(String userId, String password, double balance, Customer customer) {
		this.userId = userId;
		this.password = password;
		this.balance = balance;
		this.customer = customer;
	}
	
}