package com.yedam.app.rest.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.yedam.app.rest.service.Post;

@RestController
public class PostController {
	private WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com/");
	
	@GetMapping("selectList")
	public Map<String , Object> selectList(){
		Map<String , Object> map = new HashMap();
		List <Post> list = webClient.get()
				.uri("/posts")
				.retrieve() // 실제 요청과 응답처리
 				.bodyToFlux(Post.class) // Body를 스트림으로 전환
				.collectList() // list로 변환
				.block();  // 처리
		map.put("결과", list);
		return map;
	}
	@GetMapping("selectOne")
	public Map<String , Object> selectOne(){
		Map<String , Object> map = new HashMap();
		Post post = webClient.get()
							.uri("/posts/1")
							.retrieve() // 실제 요청과 응답처리
							.bodyToMono(Post.class) // Body를 스트림으로
							.block();
		map.put("단건처리", post);
		return map;
	}
	
	@GetMapping("insertOne")
	public Map<String , Object> insertOne(){
		Map<String , Object> map = new HashMap();
		Post newPost = new Post.Builder().userId(101).title("New Post").body("New Content").build();
		Post post = webClient.post().uri("posts").contentType(MediaType.APPLICATION_JSON).bodyValue(newPost).retrieve().bodyToMono(Post.class).block();
		map.put("등록",post);
		return map;
		
	}
}
