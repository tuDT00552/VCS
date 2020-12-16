package com.itsol.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.bank.entity.Account;
import com.itsol.bank.entity.ErrorMessage;
import com.itsol.bank.service.AccountService;

@RestController
@CrossOrigin
public class AccountController {
	@Autowired
	private AccountService accountService;

	@PostMapping("/create-account")
	@PreAuthorize("hasAnyAuthority('USER_CREATE')")
	public ResponseEntity<?> createAccount(@RequestBody Account account) {
		if (!accountService.checkExitsAccount(account)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ErrorMessage(HttpStatus.CONFLICT.value(), "Địa chỉ email này đã tồn tại", "email"));
		} else if (!accountService.checkAccountByAccountNum(account)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(
					new ErrorMessage(HttpStatus.CONFLICT.value(), "Số tài khoản này đã tồn tại", "account-number"));
		} else if (account.getAge() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Tuổi phải lớn hơn 0"));
		} else if (account.getBalance() < 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Số dư không được nhỏ hơn 0"));
		} else {
			accountService.createAccount(account);
			return ResponseEntity.status(HttpStatus.CREATED).body(account);
		}
	}

	@PutMapping("/update-account")
	@PreAuthorize("hasAnyAuthority('USER_UPDATE')")
	public ResponseEntity<?> updateAccount(@RequestBody Account account) {
		if (accountService.checkExitsAccount(account)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Tài khoản này không tồn tại!", "email"));
		} else {
			if (!accountService.checkUpdateAccount(account)) {
				return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorMessage(HttpStatus.CONFLICT.value(),
						"Số tài khoản hoặc chỉ email này đã tồn tại", "email"));
			} else {
				accountService.updateAccount(account);
				return ResponseEntity.status(HttpStatus.CREATED).body(account);
			}
		}
	}

	@DeleteMapping("/delete-account")
	@PreAuthorize("hasAnyAuthority('USER_DELETE')")
	public ResponseEntity<?> deleteAccount(@RequestBody Account account) {
		if (accountService.checkExitsAccount(account)) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Tài khoản này không tồn tại!", "email"));
		} else {
			accountService.deleteAccount(account);
			return ResponseEntity.status(HttpStatus.CREATED).body(account);
		}
	}

	@GetMapping("/getListAccount")
	public Page<Account> getListAccount(@RequestParam("page") int page, @RequestParam("size") int maxResultCount) {
		return accountService.getPagingAccount(page, maxResultCount);
	}

	@GetMapping("/searchListAccount")
	public Page<Account> getListAccount(@RequestParam("keyword") String keyword, @RequestParam("page") int page,
			@RequestParam("size") int maxResultCount) {
		return accountService.searchByAllField(keyword, page, maxResultCount);
	}
}
