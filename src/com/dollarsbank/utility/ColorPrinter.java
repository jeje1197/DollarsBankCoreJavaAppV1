package com.dollarsbank.utility;

public class ColorPrinter {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_RED = "\u001B[31m";
	
	public static void print(String text) {
		System.out.println(text);
	}
	
	public static void print(String color, String text) throws Exception {
		if (color != ANSI_BLUE && color != ANSI_GREEN && color != ANSI_RED) {
			throw new Exception("Invalid color");
		}
		System.out.print(color + text + ANSI_RESET);
	}
}
