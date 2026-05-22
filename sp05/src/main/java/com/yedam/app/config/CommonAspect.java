package com.yedam.app.config;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect  // AOP의 설정
//@Component
public class CommonAspect {
	//포인트 컷 : 조인포인트중에서 Advice가 적용될 메소드 필드
	@Pointcut("execution(* com.yedam..*Impl.*(..))")
	public void allPointcut() {};
	
	// Weaving : 포인트컷 + 타이밍 + Advice
	@Before("allPointcut()")
	public void beforeLog(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		String keyword = (String) jp.getArgs()[0]; //Object 배열로 반환됨 string으로 강제 타입 변환 반드시 해야됨
		System.out.println("[사전처리] beforeLog : " + methodName);
		System.out.println("[사전처리] beforeLog : " + keyword);
	}
	
	@Around("allPointcut()")
	public Object aroundLog(ProceedingJoinPoint jp)throws Throwable{
	    String signatureStr = jp.getSignature().toString();
	    System.out.println("=== 시작 : " + signatureStr);

	    try {
	        Object obj = jp.proceed();
	        System.out.println("결과===" + (Integer)obj);
	        return obj;
	    }finally {
	        System.out.println("=== 끝 : " + signatureStr);
	    }
	}
}
