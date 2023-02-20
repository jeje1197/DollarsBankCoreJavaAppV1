package com.dollarsbank.utility;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.dollarsbank.exception.InvalidOptionException;

public class OptionSelector {
	private static Scanner sc;
	
	public static int pickOption(int min, int max, String errorMessage) throws InvalidOptionException {
		sc = new Scanner(System.in);
		int selectedOption = sc.nextInt();
		sc.nextLine();
		sc.close();
		if (selectedOption < min || selectedOption > max) {
			throw new InvalidOptionException(errorMessage);
		}
		return selectedOption;
	}

	public static String pickOption(String regex, String errorMessage) throws InvalidOptionException {
		sc = new Scanner(System.in);
		String selectedOption = sc.nextLine().trim();
		Matcher matcher = Pattern.compile(regex)
								.matcher(selectedOption);
		sc.close();
		if (!matcher.matches()) {
			throw new InvalidOptionException(errorMessage);
		}
		return selectedOption;
	}
}
