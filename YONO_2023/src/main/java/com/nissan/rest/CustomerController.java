package com.nissan.rest;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.model.Customer;
import com.nissan.service.ICustomerService;
import com.nissan.util.JwtUtil;

@RestController // combination of @Controller+@Configuration
@RequestMapping("/api")
public class CustomerController {
	@Autowired
	private ICustomerService custService;
	@Autowired
	private APIResponse apiResponse;
	@Autowired
	private JwtUtil jwtUtil;

	// deposit
	@GetMapping("/customers/deposit/{accountNo}&{amount}")
	public ResponseEntity<APIResponse> deposit(@PathVariable long accountNo, @PathVariable double amount,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyCustomer(auth);
		int flag = custService.deposit(accountNo, amount);
		if (flag == -1) {
			apiResponse.setData("ACCOUNT NUMBER SHOULD BE NUMERIC");
			apiResponse.setStatus(500);
			apiResponse.setError("INVALID ACCOUNT NUMBER");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		} else if (flag == 0) {
			apiResponse.setData("KINDLY PROVIDE PAN NUMBER OR TRANSFER AMOUNT<50K");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

		} else {
			apiResponse.setData("DEPOSIT SUCCESFUL");
			apiResponse.setStatus(200);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

		}
	}

	// withdraw
	@GetMapping("/customers/withdraw/{accountNo}&{amount}")
	public ResponseEntity<APIResponse> withdraw(@PathVariable long accountNo, @PathVariable double amount,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyCustomer(auth);

		if (custService.withdraw(accountNo, amount) == null) {
			apiResponse.setData("ACCOUNT NUMBER SHOULD BE NUMERIC");
			apiResponse.setStatus(500);
			apiResponse.setError("INVALID ACCOUNT NUMBER");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		apiResponse.setData("WITHDRAWAL SUCCESFUL");
		apiResponse.setStatus(200);
		return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);

	}

	// transfer
	@GetMapping("/customers/transfer/{accountNo1}&{accountNo2}&{amount}")
	public ResponseEntity<APIResponse> transfer(@PathVariable long accountNo1, @PathVariable long accountNo2,
			@PathVariable double amount, @RequestHeader(value = "authorization", defaultValue = "") String auth)
			throws AccessDeniedException {
		jwtUtil.verifyCustomer(auth);
		int flag = custService.transfer(accountNo1, accountNo2, amount);
		if (flag == 1) {
			apiResponse.setData("TRANSFER SUCCESFUL");
			apiResponse.setStatus(200);
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		} else if (flag == 0) {
			apiResponse.setData("INSUFFICIENT FUNDS");
			apiResponse.setStatus(500);
			apiResponse.setError("INSUFFICENT BALANCE");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		} else {
			apiResponse.setData("ACCOUNT NUMBER SHOULD BE NUMERIC");
			apiResponse.setStatus(500);
			apiResponse.setError("INVALID ACCOUNT NUMBER");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}

	}

	// view balance
	@GetMapping("/customers/balance/{accountNo}")
	public ResponseEntity<APIResponse> checkBalance(@PathVariable long accountNo, @PathVariable double amount,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyCustomer(auth);
		double balance = custService.getBalance(accountNo);
		if(balance==-1) {
			apiResponse.setData("ACCOUNT NUMBER SHOULD BE NUMERIC");
			apiResponse.setStatus(500);
			apiResponse.setError("INVALID ACCOUNT NUMBER");
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		else {
			apiResponse.setData("Balance Amount is "+custService.getBalance(accountNo));
			apiResponse.setStatus(200);
			
			return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
		}
		

	}

}
