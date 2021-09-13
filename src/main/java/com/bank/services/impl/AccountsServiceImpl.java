package com.bank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.beans.Accounts;
import com.bank.repositories.AccountRepository;
import com.bank.services.AccountService;

@Service
public class AccountsServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository ar;
	
	@Override
	public List<Accounts> getAccounts() {
		// TODO Auto-generated method stub
		return ar.findAll();
	}

	@Override
	public Accounts getAccountsByID(int id) {
		// TODO Auto-generated method stub
		return ar.getOne(id);
	}

	@Override
	public Accounts updateAccount(Accounts Accounts) {
		// TODO Auto-generated method stub
		return ar.save(Accounts);
	}

	@Override
	public Accounts createAccount(Accounts Accounts) {
		// TODO Auto-generated method stub
		return ar.save(Accounts);
	}

	@Override
	public String deleteAccountByID(int id) {
		// TODO Auto-generated method stub
		ar.deleteById(id);
		return "Account with the id " +id + " has been deleted!";
	}

	@Override
	public Accounts getUsernamePassword(String username, String password) {
		// TODO Auto-generated method stub
		
		List<Accounts> userAccount = ar.findByUsernameAndPassword(username, password);
		
		if(userAccount.size() != 0) {
			return userAccount.get(0);
		}
		
		return null;
	}

	@Override
	public Accounts addBalance(Accounts Accounts, double addBal) {
		Accounts.setBalance(Accounts.getBalance()+addBal);
		return ar.save(Accounts);
	}

	@Override
	public Accounts subBalance(Accounts Accounts, double subBal) {
		Accounts.setBalance(Accounts.getBalance()-subBal);
		return ar.save(Accounts);
	}

}
