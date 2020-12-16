package com.itsol.bank.entity;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "tudt_account")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity{
	private Long account_number;
	private String firstname;
	private String lastname;
	private String address;
	private Long balance;
	private String gender;
	private String city;
	private String employer;
	private String state;
	private Long age;
	private String email;
	
	public Account() {
	}
	
	public Account(Long account_number, String firstname, String address, Long balance, String gender, String city,
			String employer, String state, Long age, String email, String lastname) {
		this.account_number = account_number;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.balance = balance;
		this.gender = gender;
		this.city = city;
		this.employer = employer;
		this.state = state;
		this.age = age;
		this.email = email;
	}
}
