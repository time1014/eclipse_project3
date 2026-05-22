package com.yedam.app.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AaaMapper {
	@Insert("INSERT INTO aaa VALUES(#{value})")
	public int aaaInsert(String value);
}
