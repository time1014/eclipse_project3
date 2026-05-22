package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@Controller // Web(DispatcherServlet)과 관련된 Bean
@RequiredArgsConstructor
public class EmpController {
	private final EmpService empService;
	
	
	// GET : 빈페이지 , 조회
	// POST : 데이터 조작 ( 등록 , 수정 , 삭제)
	// 삭제 => 경우에 따라서 GET으로 처리함

	// 전체조회 - GET
	@GetMapping("empList") // 1) endPoint
	public String empList(Model model) {
		// 2) 제공할 서비스
		List<EmpVO> list = empService.findAll();
		// 3) 응답 형태 -view
		// 3-1 ) 페이지에 전달할 데이터 담기
		model.addAttribute("emp",list);
		return "emp/list";	// return은 /로 시작하면 안됨 prefix랑 suffix 떄문에 prefix랑 suffix는 경로 비슷한거 앞뒤로 붙음
}
	
	// 단건조회 - GET
	@GetMapping("empInfo") // QueryString 1) 커맨드객체 2) @RequestParam
	public String empInfo(EmpVO empVO , Model model) {
		EmpVO findVO = empService.findByEmployeeId(empVO);
		model.addAttribute("emp",findVO);
		return "emp/info";
	
}
	// 등록 , 페이지 - GET
	@GetMapping("empInsert")
	public String empInsertForm() {
		
		return "emp/insert";
	}
	//등록 , 처리 - POST => <form/> -> QueryString
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		int eid = empService.addEmpInfo(empVO);
		return "redirect:empInfo?employeeId=" + eid;
		// redirect는 새로운 컨트롤러 메소드를 호출
	}
	
	// 수정 , 페이지 - GET
	@GetMapping("empUpdate") // GET -> 단건조회 -> queryString -> 커맨드객체
	public String empUpdateForm(EmpVO empVO , Model model) {
		EmpVO findVO = empService.findByEmployeeId(empVO);
		model.addAttribute("emp",findVO);
		return "emp/Update";
	
}
	// 수정 ,처리 - POST => ajax , @ResponseBody
	@PostMapping("empUpdate")
	@ResponseBody // 반환부분 ajax 용이라 페이지 없이 데이터만 반환
				  // 리턴 타입이 반환될 데이터를 반영
	public Map<String , Object> empUpdateProcess(@RequestBody EmpVO empVO){ // 매개변수 요청처리
		return empService.modifyEmpInfo(empVO);
	}
	
	// 삭제 , 처리 - GET -> QueryString -> 기본타입형-> @RequestParam
	@GetMapping("empDelete") 
	public String empDelete(Integer empId) {
		empService.removeEmpInfo(empId);
		return "redirect:empList";
	}
}
