package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class Sp06ApplicationTests {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	void pwdEncoderTest() {
		String password = "1234";
		
		String endPwd = passwordEncoder.encode(password);
		System.out.println(endPwd);
		
		boolean result = passwordEncoder.matches(password,endPwd);
		System.out.println(result);
	}

}
