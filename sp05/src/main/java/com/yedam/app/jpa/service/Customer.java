package com.yedam.app.jpa.service;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
public class Customer {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) // 기본키 설정
	private Long id;
	@Column(length=20 , nullable=false)
	private String name;
	@Column(length=20 , nullable=false , unique = true)
	private String phone;
	private String email;
	// hibernate 아니면 동작 안할수 있음
	@CreationTimestamp // 등록
	private Timestamp rdt;
	@UpdateTimestamp // 수정
	private Timestamp udt;
	
}
