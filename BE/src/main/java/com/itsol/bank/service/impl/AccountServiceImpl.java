package com.itsol.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itsol.bank.entity.Account;
import com.itsol.bank.repository.AccountRepository;
import com.itsol.bank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accountRepository;

	@Override
	public Account createAccount(Account account) {
		return accountRepository.saveAndFlush(account);
	}

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}
	
	
	@Override
	public Page<Account> getPagingAccount(int page, int maxResultCount) {
		return accountRepository.findAll(PageRequest.of(page, maxResultCount, Sort.by(Sort.Direction.DESC, "updatedAt")));
	}
	
	@Override
	public Page<Account> searchByAllField(String keyword, int page, int maxResultCount) {
		return accountRepository.searchByAllField(keyword, PageRequest.of(page, maxResultCount));
	}


	@Override
	public Account updateAccount(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public ResponseEntity<Account> deleteAccount(Account account) {
		accountRepository.delete(account);
		return ResponseEntity.ok(account);
	}

	@Override
	public Boolean checkExitsAccount(Account account) {
		return accountRepository.checkAccountByEmail(account.getEmail()) == null;
	}

	@Override
	public Boolean checkAccountByAccountNum(Account account) {
		return accountRepository.checkAccountByAccountNum(account.getAccount_number()) == null;
	}

	@Override
	public Boolean checkUpdateAccount(Account account) {
		return accountRepository.checkUpdateAccount(account.getAccount_number(), account.getEmail(), account.getId()) == null;
	}

	@Override
	public Boolean checkAccountExits(Account account) {
		return accountRepository.checkAccountExits(account.getId()) == null;
	}

//	@Override
//	public List<Account> searchByAllField(String keyword) {
//		return accountRepository.searchByAllField(keyword);
//	}
}
