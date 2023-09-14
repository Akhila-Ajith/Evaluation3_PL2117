package com.nissan.service;

import org.springframework.stereotype.Service;

import com.nissan.model.Customer;

@Service
public interface ICustomerService {
	public int deposit(long accNo,double amount);
	public Customer withdraw(long accNo,double amount);
	public int transfer(long fromAccNo,long toAccNo,double amount);
	public double getBalance(long accountNo);
	
}
