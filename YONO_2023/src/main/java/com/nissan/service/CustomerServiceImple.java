package com.nissan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.common.Validation;
import com.nissan.model.Customer;
import com.nissan.repo.ICustomerRepo;

import jakarta.transaction.Transactional;
@Service
public class CustomerServiceImple implements ICustomerService {
	@Autowired
	private ICustomerRepo custRepo;
	@Autowired
	private APIResponse apiResponse;
	@Autowired
	private Validation validation;
	
	
	
	//deposit
	@Transactional
	@Override	
	public int deposit(long accNo,double amount) {
		int flag=1;
		if(validation.isValidAccountNumber(String.valueOf(accNo))) {
			if(amount<50000) {
				
				custRepo.deposit(accNo,amount);
			}
			else {
				flag=0;
			}
		}
		else {
			flag=-1;
				
		}
		return flag;

	}
	//withdraw
	@Transactional
	@Override
	public Customer withdraw(long accNo,double amount) {
		// TODO Auto-generated method stub
		if(validation.isValidAccountNumber(String.valueOf(accNo))) {
		double availableBalance = custRepo.getBalance(accNo);
		double mininumBalance=custRepo.getMinBalance(accNo);
		if(amount <= availableBalance-mininumBalance) {
			 
			 
			 return custRepo.withdraw(accNo, amount);
		}
		
		}
		return null;
	}
	
	//transfer
	@Transactional
	@Override
	public int transfer(long fromAccNo, long toAccNo, double amount) {
		// TODO Auto-generated method stub
		int flag=1;
		if(validation.isValidAccountNumber(String.valueOf(fromAccNo))&&validation.isValidAccountNumber(String.valueOf(toAccNo))) {
			double availableBalance = custRepo.getBalance(fromAccNo);
			double mininumBalance=custRepo.getMinBalance(fromAccNo);
			if(amount <= availableBalance-mininumBalance) {
				
				custRepo.withdraw(fromAccNo,amount);
		
				custRepo.deposit(toAccNo,amount);
				}
			else {
				flag=0;
			}
		}
		else {
			flag=-1;
		}
		return flag;
	}
	
	//view balance
	@Override
	public double getBalance(long accountNo) {
		// TODO Auto-generated method stub
		if(validation.isValidAccountNumber(String.valueOf(accountNo))) {
		double balance=custRepo.getBalance(accountNo);
		return balance;}
		else {
			return -1;
		}
	}

}
