package com.itsol.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsol.bank.entity.Token;
import com.itsol.bank.repository.TokenRepository;
import com.itsol.bank.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token createToken(Token token){
        return tokenRepository.saveAndFlush(token);
    }
    
    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}