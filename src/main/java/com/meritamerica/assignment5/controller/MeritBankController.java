package com.meritamerica.assignment5.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment5.models.*;


@RestController
public class MeritBankController {

	@GetMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.OK)
	public AccountHolder[] getAccountHolder(){
		return MeritBank.getAccountHolders();
	}
	
	@PostMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody AccountHolder accountHolder) {
		MeritBank.addAccountHolder(accountHolder);
		return accountHolder;
	}
	
	@GetMapping(value = "/AccountHolders/{id}")
	public AccountHolder getAccountHolderById(
			@PathVariable(name = "id") long id) throws Exception {
		AccountHolder account = MeritBank.getAccountHolderById(id);
		if (account == null) {
			throw new Exception("ID Not Found");
		}
		return account;
	}
	
	// Checking Account Stuff
	
	@PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public CheckingAccount addCheckingAccount(
			@PathVariable(name = "id") long id,
			@RequestBody CheckingAccount checkingAccount) throws Exception {
		AccountHolder account = MeritBank.getAccountHolderById(id);
		account.addCheckingAccount(checkingAccount);
		return checkingAccount;
		
	}
	
	@GetMapping("/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.OK)
	public CheckingAccount[] getCheckingAccounts(@PathVariable(name = "id") long id) throws Exception {
		AccountHolder account = MeritBank.getAccountHolderById(id);
		if(account == null) {
			throw new Exception("ID Not Found");
		}
		return account.getCheckingAccounts();
	}
	
	// Savings Account Stuff

	@PostMapping("/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccount(@PathVariable(name = "id") long id,
			@RequestBody SavingsAccount savingsAccount) throws Exception,
			ExceedsCombinedBalanceLimitException, NegativeAmountException {
		AccountHolder account = MeritBank.getAccountHolderById(id);
		account.addSavingsAccount(savingsAccount);
		return savingsAccount;
	}
	
	@GetMapping("/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.OK)
	public SavingsAccount[] getSavingsAccounts(@PathVariable(name = "id") long id) throws Exception {
		AccountHolder account = MeritBank.getAccountHolderById(id);
		if(account == null) {
			throw new Exception("ID Not Found");
		}
		return account.getSavingsAccounts();
	}
	
	// CDAccount Stuff
	
	@PostMapping("/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@PathVariable(name = "id") long id,
			@RequestBody CDAccount cdAccount) throws Exception,
			ExceedsCombinedBalanceLimitException, NegativeAmountException {
		AccountHolder account = MeritBank.getAccountHolderById(id);
		account.addCDAccount(cdAccount);
		return cdAccount;
	}
	
	@GetMapping("/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.OK)
	public CDAccount[] getCDAccounts(@PathVariable(name = "id") long id) throws Exception {
		AccountHolder account = MeritBank.getAccountHolderById(id);
		if(account == null) {
			throw new Exception("ID Not Found");
		}
		return account.getCDAccounts();
	}
	
	// CD Offerings
	
	@PostMapping("/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering createCDOffering(@RequestBody CDOffering cdOffering) {
		MeritBank.addCDOfferings(cdOffering);
		return cdOffering;
	}
	
	@GetMapping("/CDOfferings")
	@ResponseStatus(HttpStatus.OK)
	public CDOffering[] getCDOfferings() throws Exception { 
		CDOffering[] cdOfferings = MeritBank.getCDOfferings();
		return cdOfferings;
	}
	
}
