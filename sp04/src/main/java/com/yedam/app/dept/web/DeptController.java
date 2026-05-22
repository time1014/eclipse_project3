package com.yedam.app.dept.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class DeptController {

	private final DeptService deptService;
	
	
	@GetMapping("deptList")
	public String deptList(Model model) {
		List<DeptVO> list = deptService.findAll();
		model.addAttribute("depts",list);
		return "dept/list";
	}
	
	@GetMapping("deptInfo")
	public String deptInfo(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.findByDeptId(deptVO);
		model.addAttribute("dept",findVO);
		return "dept/info";
	}
	
	@GetMapping("deptInsert")
	public String deptInsertForm() {
		return "dept/insert";
	}
	
	@PostMapping("deptInsert")
	public String deptInsertProcess(DeptVO deptVO) {
		int did = deptService.addDeptInfo(deptVO);
		return "redirect:deptInfo?departmentId=" + did;
	}
	
	@GetMapping("deptUpdate")
	public String deptUpdateForm(DeptVO deptVO , Model model) {
		DeptVO findVO = deptService.findByDeptId(deptVO);
		model.addAttribute("dept",findVO);
		return "dept/update";
	}
	
	@PostMapping("deptUpdate")
	@ResponseBody
	public Map<String , Object> deptUpdateProcess(@RequestBody DeptVO deptVO){
		return deptService.modifyDeptInfo(deptVO);
	}
	
	@GetMapping("deptDelete")
	public String deptDelete(Integer departmentId) {
		deptService.removeDeptInfo(departmentId);
		return "redirect:deptList";
	}
}
