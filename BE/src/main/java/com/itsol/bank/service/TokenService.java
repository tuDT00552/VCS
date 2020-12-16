package com.itsol.bank.service;

import com.itsol.bank.entity.Token;

public interface TokenService {
    Token createToken(Token token);
    Token findByToken(String token);
}