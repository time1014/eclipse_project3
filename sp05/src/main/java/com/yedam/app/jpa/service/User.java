package com.yedam.app.jpa.service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="TBL_USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long no;
	@Column(name = "username" , length=20,nullable=false , unique=true) //20byte
	private String id;
	@Column(length=20 , nullable=false)
	private String password;
	private Integer age;
	private String email;
}
