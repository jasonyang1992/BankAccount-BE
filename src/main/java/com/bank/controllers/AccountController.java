package com.bank.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.beans.Accounts;
import com.bank.services.impl.AccountsServiceImpl;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {
	
	@Autowired
	private AccountsServiceImpl asi;
	
	@GetMapping("/{id}")
	public Accounts getAccountByID(@PathVariable("id") int id) {
		return asi.getAccountsByID(id);
	}
	
	@PostMapping
	public ResponseEntity<Accounts> createAccount(@RequestBody Accounts Accounts) {
		return new ResponseEntity<>(asi.createAccount(Accounts), HttpStatus.CREATED);
	}
	
	@PostMapping("/addBal")
	public Accounts addBalance(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("addBalance") Double addBal) {
		
		Accounts userAcc = asi.getUsernamePassword(username, password);
		asi.addBalance(userAcc, addBal);
		
		return asi.updateAccount(userAcc);
	}
	
	@PostMapping("/subBal")
	public Accounts subtractBalance(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("subBalance") Double subBal) {
		
		Accounts userAcc = asi.getUsernamePassword(username, password);
		asi.subBalance(userAcc, subBal);
		
		return asi.updateAccount(userAcc);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Accounts> verifyAccount(@RequestParam("username") String username, @RequestParam("password") String password) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("access-control-expose-headers", "Auth"); //Exposes Auth in the headers
		responseHeaders.set("access-control-expose-headers", "BankID"); //Exposes BankID in the headers
		responseHeaders.set("access-control-expose-headers", "Username"); //Exposes Username in the headers
		
		// Checks if the account exist in the database
		if (asi.getUsernamePassword(username, password) == null) {
			responseHeaders.set("Auth", "False");
			return new ResponseEntity<>(responseHeaders,HttpStatus.UNAUTHORIZED);
		}	
		
		Accounts userAcc = asi.getUsernamePassword(username, password);
		
		responseHeaders.set("BankID", String.valueOf(userAcc.getBankId()));
		responseHeaders.set("Username", userAcc.getUsername());
		
		return new ResponseEntity<>(responseHeaders,HttpStatus.OK);
	}
}
