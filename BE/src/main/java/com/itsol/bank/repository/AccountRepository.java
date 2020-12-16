package com.itsol.bank.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.itsol.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	@Query(value = "Select id from tudt_account where email = ?1", nativeQuery = true)
	Long checkAccountByEmail(String email);
	
	@Query(value = "Select id from tudt_account where id NOT LIKE ?3 AND (account_number = ?1 OR email = ?2) ", nativeQuery = true)
	Long checkUpdateAccount(Long account_number, String email, Long id);
	
	@Query(value = "Select id from tudt_account where account_number = ?1", nativeQuery = true)
	Long checkAccountByAccountNum(Long account_number);
	
	@Query(value = "Select id from tudt_account where id = ?1", nativeQuery = true)
	Long checkAccountExits(Long id);
	
	@Query(value = "Select * from tudt_account where gender LIKE ?1 or account_number = ?1 or firstname LIKE ?1 or lastname LIKE ?1 or address LIKE ?1 or balance = ?1 or city LIKE ?1 or employer LIKE ?1 or state LIKE ?1 or age = ?1 or email LIKE ?1", nativeQuery = true)
	Page<Account> searchByAllField(String keyword, Pageable pageable);
}
