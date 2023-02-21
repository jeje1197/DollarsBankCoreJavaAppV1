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

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", password=" + password + ", balance=" + balance + ", customer="
				+ customer + "]";
	}
}