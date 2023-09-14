package com.nissan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	@Column(name="accountNo")
	private long accountNo;
	
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@Column(name="customerId")
//	private Integer customerId;
	
	
	
	@Column(name="accountType",nullable=false,length=60)
	private String accountType;
	
	@Column(name="customerName",nullable=false,length=60)
	private String customerName;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Column(name="balance")
	private Integer balance;
	
	@Column(name="minBalance")
	private Integer minBalance;
	
	@Column(name="mobileNo",nullable=false,length=60)
	private String mobileNo;
	
	@Column(name="emailId",nullable=false,length=60)
	private String emailId;
	
	@Column(name="atmPin")
	private Integer atmPin;
	private boolean isActive=true;
//	public Integer getCustomerId() {
//		return customerId;
//	}
//	public void setCustomerId(Integer customerId) {
//		this.customerId = customerId;
//	}




	public String getAccountType() {
		return accountType;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getMinBalance() {
		return minBalance;
	}
	public void setMinBalance(Integer minBalance) {
		this.minBalance = minBalance;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Integer getAtmPin() {
		return atmPin;
	}
	public void setAtmPin(Integer atmPin) {
		this.atmPin = atmPin;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Customer(long accountNo, String accountType, String customerName, Integer balance,
			Integer minBalance, String mobileNo, String emailId, Integer atmPin, boolean isActive) {
		super();
		this.accountNo = accountNo;
		//this.customerId = customerId;
		this.accountType = accountType;
		this.customerName = customerName;
		this.balance = balance;
		this.minBalance = minBalance;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.atmPin = atmPin;
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "Customer [accountNo=" + accountNo +  ", accountType=" + accountType
				+ ", customerName=" + customerName + ", balance=" + balance + ", minBalance=" + minBalance
				+ ", mobileNo=" + mobileNo + ", emailId=" + emailId + ", atmPin=" + atmPin + ", isActive=" + isActive
				+ "]";
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
