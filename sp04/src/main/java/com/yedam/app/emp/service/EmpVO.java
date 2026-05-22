package com.yedam.app.emp.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //Mybatis , HandlerAdapter
@AllArgsConstructor
@Builder
public class EmpVO {
	// DB와 연결 => 컬럼명을 참고해서 필드명 구성
	private Integer employeeId; // PK
	private String firstName;
	private String lastName;    // Not Null
	private String email;		// Not Null, Unique
	private String phoneNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;		// Not Null
	private String jobId;		// Not Null, FK(jobs)
	private Double salary;		// 0보다 커야함
	private double commissionPct;// 1보다 작은 값
	private int managerId;		// FK(employees)
	private int departmentId;	// FK(departments)
}
