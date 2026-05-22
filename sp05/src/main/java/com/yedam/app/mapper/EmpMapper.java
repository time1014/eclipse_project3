package com.yedam.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmpMapper {
	@Select("SELECT MAX(employee_id) FROM employees")
	public Integer selectEmpInfo();
}
