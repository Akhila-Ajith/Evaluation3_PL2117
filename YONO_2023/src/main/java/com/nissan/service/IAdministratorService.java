package com.nissan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nissan.model.Customer;
@Service
public interface IAdministratorService {
	//list
		public List<Customer>getCustomer();
		//insert
		public Customer saveCustomer(Customer customer);
		//update
		public Customer updateCustomer(Customer customer);
		//search
		public Customer getCustomer(long Accno);
	
		public Customer deleteCustomer(long Accno);
//		//search by name
		//public List<Customer> getCustomerByAccno(String Accno);
}
