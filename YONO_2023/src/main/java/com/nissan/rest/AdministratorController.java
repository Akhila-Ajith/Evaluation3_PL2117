package com.nissan.rest;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.model.Customer;
import com.nissan.service.IAdministratorService;
import com.nissan.util.JwtUtil;

@RestController // combination of @Controller+@Configuration
@RequestMapping("/api")
public class AdministratorController {
	@Autowired
	private IAdministratorService adminService;
	@Autowired
	private APIResponse apiResponse;
	@Autowired
	private JwtUtil jwtUtil;

	// list
	@GetMapping("/administrator")
	public List<Customer> getCustomer(@RequestHeader(value="authorization",defaultValue="")String auth) throws AccessDeniedException{
		jwtUtil.verifyAdmin(auth);
		return adminService.getCustomer();
	}

	// add customer
	@PostMapping("/administrator")
	public ResponseEntity<APIResponse> addCustomer(@RequestBody Customer customer,@RequestHeader(value="authorization",defaultValue="")String auth) throws AccessDeniedException{
		jwtUtil.verifyAdmin(auth); 

		if (adminService.saveCustomer(customer) == null) {
			apiResponse.setData("Name too short");
			apiResponse.setStatus(500);
			apiResponse.setError("INVALID NAME");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("EMPLOYEE ADDED SUCCESFULLY");
		apiResponse.setStatus(200);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	// update customer
	@PutMapping("/administrator/{accountNo}")
		public ResponseEntity<APIResponse>  updateCustomer(@RequestBody Customer customer,@RequestHeader(value="authorization",defaultValue="")String auth) throws AccessDeniedException{
			jwtUtil.verifyAdmin(auth);
			if(adminService.updateCustomer(customer) == null) {
				apiResponse.setData("ACCOUNT NUMBER SHOULD BE NUMERIC");
				apiResponse.setStatus(500);
				apiResponse.setError("INVALID ACCOUNT NUMBER");
				return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
			}
			apiResponse.setData("CUSTOMER UPDATED SUCCESFULLY");
			apiResponse.setStatus(200);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
	
	}
	//search
	@GetMapping("/administrator/search/{accountNo}")
		public ResponseEntity<APIResponse> getAllCustomer(@PathVariable long accountNo,@RequestHeader(value="authorization",defaultValue="")String auth) throws AccessDeniedException{
			jwtUtil.verifyAdmin(auth);
			if(adminService.getCustomer(accountNo)==null) {
				apiResponse.setData("ACCOUNT NUMBER SHOULD BE NUMERIC");
				apiResponse.setStatus(500);
				apiResponse.setError("INVALID ACCOUNT NUMBER");
				
				return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
			}
			apiResponse.setData("CUSTOMER DELETED SUCCESFULLY");
			apiResponse.setStatus(200);
			
			return ResponseEntity
					.status(apiResponse.getStatus()).body(apiResponse);
		}
			
	//delete
	@GetMapping("/administrator/delete/{accountNo}")
	public ResponseEntity<APIResponse> deleteCustomer(@PathVariable long accno, @RequestHeader(value="authorization",defaultValue="")String auth)
			throws AccessDeniedException {
		jwtUtil.verifyAdmin(auth);
		if(adminService.deleteCustomer(accno)==null) {
			apiResponse.setData("ACCOUNT NUMBER SHOULD BE NUMERIC");
			apiResponse.setStatus(500);
			apiResponse.setError("INVALID ACCOUNT NUMBER");
			
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("CUSTOMER DELETED SUCCESFULLY");
		apiResponse.setStatus(200);
		
		return ResponseEntity
				.status(apiResponse.getStatus()).body(apiResponse);
	}
			
			
		}

