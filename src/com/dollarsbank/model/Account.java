package com.dollarsbank.model;

import java.util.ArrayList;
import java.util.List;

public class Account {
	private String userId;
	private String password;
	private double balance;
	private Customer customer;
	private List<String> transactionHistory = new ArrayList<>();

	public Account(String userId, String password, double balance, Customer customer) {
		this.userId = userId;
		this.password = password;
		this.balance = balance;
		this.customer = customer;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<String> getTransactionHistory() {
		return transactionHistory;
	}

	public void setTransactionHistory(List<String> transactionHistory) {
		this.transactionHistory = transactionHistory;
	}

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", password=" + password + ", balance=" + balance + ", customer="
				+ customer + ", transactionHistory=" + transactionHistory + "]";
	}
}