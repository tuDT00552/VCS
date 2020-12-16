package com.itsol.bank.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.itsol.bank.entity.Account;

public interface AccountService {
	Boolean checkExitsAccount(Account account);
	Boolean checkAccountByAccountNum(Account account);
	Boolean checkUpdateAccount(Account account);
	Boolean checkAccountExits(Account account);
	Account createAccount(Account account);
	Account updateAccount(Account account);
	ResponseEntity<Account> deleteAccount(Account account);
	List<Account> findAll();
	Page<Account> searchByAllField(String keyword, int page, int maxResultCount);
	Page<Account> getPagingAccount(int page, int maxResultCount);
}
