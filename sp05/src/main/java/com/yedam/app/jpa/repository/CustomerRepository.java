package com.yedam.app.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.jpa.service.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	// SELECT * FROM customer WHERE name = ?
	List<Customer> findByName(String name);
	
	//  SELECT * FROM customer WHERE name = ? AND phone = ?
	List<Customer> findByNameAndPhone(String name , String phone);
	
	//  SELECT * FROM customer WHERE name LIKE '%'?'%'
	List<Customer> findByNameContaining(String name);
	
	// JPQL: 실제 테이블과 컬럼이름이 아닌 entity 명과 필드명 사용 * 대신 별칭 사용
	// 1) SELECT
	@Query("SELECT u FROM Customer u WHERE u.name LIKE CONCAT(CONCAT('%',?1),'%') ORDER BY u.id DESC")
	public List<Customer> findByAndSort(String name);
	// 2) DML
	@Transactional
	@Modifying
	@Query("UPDATE Customer c SET c.name =?1 WHERE c.id = ?2")
	public int setFixedNameFor(String name , Long id);
}
