package com.nissan.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.Customer;
@Repository
public interface ICustomerRepo extends CrudRepository<Customer,Integer>  {
	//deposit
	
	@Modifying
	@Query("UPDATE com.nissan.model.Customer SET balance=balance+?2 WHERE accountNo=?1")
	public Customer deposit(long accNo,double amount);
	@Query("SELECT balance FROM com.nissan.model.Customer WHERE accountNo=?1")
	public double getBalance(long accNo);
	@Query("SELECT minBalance FROM com.nissan.model.Customer WHERE accountNo=?1")
	public double getMinBalance(long accNo);
	//withdraw
	@Modifying
	@Query("UPDATE com.nissan.model.Customer SET balance=balance-?2 WHERE accountNo=?1")
	public Customer withdraw(long accNo, double amount);
	
	
	

}
