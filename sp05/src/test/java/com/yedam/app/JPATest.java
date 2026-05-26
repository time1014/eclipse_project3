package com.yedam.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.jpa.repository.CustomerRepository;
import com.yedam.app.jpa.repository.UserRepository;
import com.yedam.app.jpa.service.Customer;
import com.yedam.app.jpa.service.User;

//@Transactional
@SpringBootTest
public class JPATest {
	@Autowired
	private UserRepository userRep;
	
	//@Test
	void insertTest() {
		User first = new User();
		first.setId("Hong");
		first.setPassword("1234");
		first.setAge(28);
		
		userRep.save(first);
		
		User second = new User();
		second.setId("kang");
		second.setPassword("1234");
		
		userRep.save(second);
	}
	//@Test
	void findAllTest() {
		List<User> list = userRep.findAll();
		for(User info : list) {
			System.out.println(info);
		}
	}
	//@Test
	void findByIdTest() {
		User user = userRep.findById(1L).orElse(null); // 확실하게 값이 있다고 판단되면 .get() 써도 됨
		System.out.println(user);
	}
	
	//@Test
	void updateTest() {
		User user = userRep.findById(1L).get();
		user.setNo(1L);
		user.setId("lee");
		user.setEmail("lee@email.com");
		userRep.save(user);
		System.out.println(user);
		}
	@Autowired
	private CustomerRepository cusRepo;
	
	//@Test
	void custInsertTest() {
		Customer first = new Customer();
		first.setName("홍길동");
		first.setPhone("01042130532");
		cusRepo.save(first);
		
		Customer second = new Customer();
		second.setName("이한동");
		second.setPhone("01032341133");
		cusRepo.save(second);
	}
	
	//@Test
	void queryMethodTest() {
		List<Customer> list = cusRepo.findByNameAndPhone("이한동", "01032341133");
		for(Customer info : list) {
			System.out.println(info);
		}
		list = cusRepo.findByNameContaining("동");
		list.stream().forEach(System.out::println);
	}
	@Test
	void jpqlTest() {
		List<Customer> list = cusRepo.findByAndSort("동");
		for(Customer info : list) {
			cusRepo.setFixedNameFor("Hong",info.getId());
		}
	}
}
