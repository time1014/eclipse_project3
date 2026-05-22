package com.yedam.app;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.rest.service.Post;

@SpringBootTest
public class BuilderPatternTest {
	@Test
	void first() {
		Post post = new Post.Builder().userId(1).title("First Post").body("Hello").bulid();
		
		System.out.println(post);
	}
}
