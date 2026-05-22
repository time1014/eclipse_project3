package com.yedam.app.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeptServiceImpl  implements DeptService{

	private final DeptMapper deptMapper;
	// 전체 조회
	@Override
	public List<DeptVO> findAll() {
		return deptMapper.selectDeptAll();
	}

	// 단건조회
	@Override
	public DeptVO findByDeptId(DeptVO deptVO) {
		return deptMapper.selectDept(deptVO);
	}

	//등록
	@Override
	public int addDeptInfo(DeptVO deptVO) {
		int result = deptMapper.insertDept(deptVO);
		return result > 0 ? deptVO.getDepartmentId() : -1;
}

	// 수정
	@Override
	public Map<String, Object> modifyDeptInfo(DeptVO deptVO) {
		Map<String , Object> map = new HashMap<>();
		boolean successed = false;
		int result = deptMapper.updateDept(deptVO);
		if(result >= 1) {
			successed = true;
		}
		map.put("result", successed);
		map.put("target", deptVO);
		return map;
	}

	// 삭제
	@Override
	public Map<String, Object> removeDeptInfo(int departmentId) {
		Map<String  , Object> map = new HashMap<>();
		int result = deptMapper.deleteDept(departmentId);
		if(result >= 1) {
			map.put("departmentId", departmentId);
		}
		return map;
	}

}
