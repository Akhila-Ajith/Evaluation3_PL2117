package com.nissan.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.Validation;
import com.nissan.model.Customer;
import com.nissan.repo.IAdministratorRepo;

import jakarta.transaction.Transactional;

@Service
public class AdministratorServiceImple implements IAdministratorService {
	@Autowired
	private IAdministratorRepo adminRepo;
	@Autowired
	private Validation validation;
	
	
	//list
	@Override
	public List<Customer> getCustomer() {
		// TODO Auto-generated method stub
		return (List<Customer>)adminRepo.findAll();
	}
	//add
	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if(validation.isValidCustomerName(customer.getCustomerName())) {
			customer.setAtmPin(generateATMPin());
			customer.setAccountNo((generateAccountNo()));
			return adminRepo.save(customer);
		
	}
		return null;
	}
	
	//update customer
	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		if(validation.isValidAccountNumber(String.valueOf(customer.getAccountNo()))) {
			return adminRepo.save(customer);
			}
		
	return null;	
	}
	
	//search
	@Override
	public Customer getCustomer(long Accno) {
		// TODO Auto-generated method stub
		if(validation.isValidAccountNumber(String.valueOf(Accno))) {
		 return adminRepo.findById((int) (Accno)).orElseThrow(()->new RuntimeException("Employee not found for Account number "+Accno));
		}
		return null;
	}
	 //delete customer
	   @Transactional
		@Override
		public Customer deleteCustomer(long Accno) {
		   if(validation.isValidAccountNumber(String.valueOf(Accno))) {
				return adminRepo.deleteCustomer(Accno);
		   }
		return null;


//adminRepo.deleteCustomer(Accno);
		}
	//	
	//random account number generation
	public int generateAccountNo() {
        // Generate a 9-digit account number (you can use a random generator)
        Random random = new Random();
        return 100000000 + random.nextInt(900000000);
    }
	//random pin generation 
    public int generateATMPin() {
        // Generate a 4-digit ATM pin (you can use a random generator)
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }
   

	


	
	
	//@Override
	/*public Customer updateCustomerByAccountNo(String Accno) {
		// TODO Auto-generated method stub
		return adminRepo.UpdateCustByAccNo;
	}*/

}
