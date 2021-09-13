package com.bank.services;

import java.util.List;

import com.bank.beans.Accounts;

public interface AccountService {

	public List<Accounts> getAccounts();
	public Accounts getAccountsByID(int id);
	public Accounts updateAccount(Accounts Accounts);
	public Accounts createAccount(Accounts Accounts);
	public String deleteAccountByID(int id);
	public Accounts getUsernamePassword(String username, String password);
	public Accounts addBalance(Accounts Accounts, double addBal);
	public Accounts subBalance(Accounts Accounts, double subBal);
}
