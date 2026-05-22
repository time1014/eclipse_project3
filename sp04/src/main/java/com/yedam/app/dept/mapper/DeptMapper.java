package com.yedam.app.dept.mapper;

import java.util.List;

import com.yedam.app.dept.service.DeptVO;

public interface DeptMapper {
	public List<DeptVO> selectDeptAll();
	public DeptVO selectDept(DeptVO deptVO);
	public int insertDept(DeptVO deptVO);
	public int updateDept(DeptVO deptVO);
	public int deleteDept(int departmentId);
}
