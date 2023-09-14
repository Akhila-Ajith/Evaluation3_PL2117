package com.nissan.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import org.springframework.stereotype.Component;

@Component
public class Validation {
	public boolean isValidCustomerName(String customerName) {
		// Customer name should not be more than 30 characters
		boolean bool = false;
		try {

			if (customerName.length() <= 30)
				bool = true;
			else {
				throw new InvalidNameException("INVALID NUMBER");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean isValidMobileNumber(String mobileNumber) {
		// Mobile number should be a 10-digit number
		boolean bool = false;

		try {

			if (mobileNumber.matches("\\d{10}")) {
				bool = true;
			} else {
				throw new InvalidNameException("INVALID NUMBER");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;

	}

	public boolean isValidAccountNumber(String accountNumber) {
		// Account number should be a 9-digit number
		boolean bool = false;
		try {

			if (accountNumber.matches("\\d{9}")) {
				bool = true;
			} else {
				throw new InvalidNameException("INVALID NUMBER");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
}
