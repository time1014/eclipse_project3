package com.yedam.app.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.mapper.AaaMapper;
import com.yedam.app.service.AaaService;

import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class AaaServiceImpl implements AaaService{
	final private AaaMapper aaaMapper;
	@Override
	public void insert() {
		aaaMapper.aaaInsert("101");
		aaaMapper.aaaInsert("a101");
	}
}
