package com.itsol.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsol.bank.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}