package com.itsol.bank.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table(name = "tudt_permission")
@Getter
@Setter
public class Permission extends BaseEntity {

	private String permissionName;

	private String permissionKey;

}