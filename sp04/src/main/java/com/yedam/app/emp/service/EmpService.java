package com.yedam.app.emp.service;

import java.util.List;
import java.util.Map;

public interface EmpService {
	public List<EmpVO> findAll();
	
	public EmpVO findByEmployeeId(EmpVO empVO);
	
	public int addEmpInfo(EmpVO empVO);
	
	public Map<String , Object> modifyEmpInfo(EmpVO empVO);
	
	public Map<String , Object> removeEmpInfo(int empId);
}
