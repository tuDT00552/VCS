package com.itsol.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itsol.bank.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	Token findByToken(String token);
}