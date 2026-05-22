package com.yedam.app.rest.service;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Post {
	private Integer userId;
	private Integer id;
	private String title;
	private String body;
	
	 public Post(Integer userId , Integer id , String title, String body) {
		 this.userId = userId;
		 this.id = id;
		 this.title = title;
		 this.body = body;
	 }
	
	public static class Builder{
		private Integer userId;
		private Integer id;
		private String title;
		private String body;
		
		public Builder userId(int userId) {
			this.userId = userId;
			return this;
		}
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		public Builder body(String body) {
			this.body = body;
			return this;
		}
		public Post bulid() {
			return new Post(userId , id ,title ,body);
		}
	}
}
