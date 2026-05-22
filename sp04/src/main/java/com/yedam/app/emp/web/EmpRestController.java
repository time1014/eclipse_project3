package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@RestController // @Controller + @ResponseBody
@RequiredArgsConstructor
public class EmpRestController {
	private final EmpService empService;
	
	//전체 조회 : GET + emps
	@GetMapping("emps")
	public List<EmpVO> empList(){
		return empService.findAll();
	}
	
	// 단건조회:GET + emps/{eid}
	@GetMapping("emps/{eid}")
	public EmpVO empInfo(@PathVariable Integer eid){
//		EmpVO empVO = new EmpVO();
//		empVO.setEmployeeId(eid);
		EmpVO empVO = EmpVO.builder().employeeId(eid).build();
		return empService.findByEmployeeId(empVO);
	}
	
	//등록:POST + emps  + JSON
	@PostMapping("emps")
	public Integer empInsert(@RequestBody EmpVO empVO) {
		return empService.addEmpInfo(empVO);
	}	
	// 수정:PUT + emps/{eid} + JSON
	@PutMapping("emps/{eid}")
	public Map<String , Object> empUpdate(@PathVariable Integer eid,@RequestBody EmpVO empVO){
		empVO.setEmployeeId(eid);
		return empService.modifyEmpInfo(empVO);
	}
	
	// 삭제:DELETE + emps/{eid}
	@DeleteMapping("emps/{eid}")
	public Map<String, Object> empDelete(@PathVariable Integer eid ){
		return empService.removeEmpInfo(eid);
	}
	
}
