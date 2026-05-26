package com.yedam.app.security.web;

import java.util.List;

import lombok.Data;



@Data
public class UserVO {

	private String loginId;
	private String password;
	private List<String> roleList;
}
