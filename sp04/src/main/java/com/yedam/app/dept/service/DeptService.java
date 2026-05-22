package com.yedam.app.dept.service;

import java.util.List;
import java.util.Map;

public interface DeptService {
	public List<DeptVO> findAll();
	public DeptVO findByDeptId(DeptVO deptVO);
	public int addDeptInfo(DeptVO deptVO);
	public Map<String , Object> modifyDeptInfo(DeptVO deptVO);
	public Map<String , Object> removeDeptInfo(int departmentId);
}
