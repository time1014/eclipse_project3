package com.yedam.app.security.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yedam.app.security.web.UserVO;

@Mapper
public interface UserMapper {
	public UserVO getUserInfo(String id);
}
