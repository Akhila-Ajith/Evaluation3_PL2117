package com.nissan.service;

import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.model.Users;
@Service
public interface IUserService {
	public Users saveUser(Users user);
	
	public APIResponse findAdminByNameAndPassword(String userName, String password);
	
	public APIResponse findCustomerByNameAndPassword(String userName, String password);
}

