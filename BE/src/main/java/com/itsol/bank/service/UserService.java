package com.itsol.bank.service;

import com.itsol.bank.entity.User;
import com.itsol.bank.security.UserPrincipal;

public interface UserService {
    User createUser(User user);
    UserPrincipal findByUsername(String username);
}