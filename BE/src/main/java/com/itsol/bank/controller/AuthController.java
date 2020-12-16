package com.itsol.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.bank.entity.Account;
import com.itsol.bank.entity.Token;
import com.itsol.bank.entity.User;
import com.itsol.bank.resource.ReadJSON;
import com.itsol.bank.security.JwtUtil;
import com.itsol.bank.security.UserPrincipal;
import com.itsol.bank.service.AccountService;
import com.itsol.bank.service.TokenService;
import com.itsol.bank.service.UserService;

@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
        if (null == user || !new BCryptPasswordEncoder().matches(user.getPassword(), userPrincipal.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tài khoản hoặc mật khẩu không chính xác");
        }
        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));
        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(user.getCreatedBy());
        tokenService.createToken(token);
        return ResponseEntity.status(HttpStatus.CREATED).body(token.getToken());
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }
    
    @GetMapping("/insert")
    public ResponseEntity<String> insert(){
    	ReadJSON read = new ReadJSON();
    	List<Account> list = read.getDataJson();
    	for (Account account : list) {
			accountService.createAccount(account);
		}
        return ResponseEntity.ok("OK");
    }
    
   
}