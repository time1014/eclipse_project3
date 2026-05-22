package com.yedam.app.service.Impl;

import org.springframework.stereotype.Service;

import com.yedam.app.mapper.EmpMapper;
import com.yedam.app.service.EmpService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

	private final EmpMapper empMapper;
	
	
	@Override
	public Integer selectInfo(String keyword) {
		// TODO Auto-generated method stub
		return empMapper.selectEmpInfo();
	}

}
