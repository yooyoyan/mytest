package cn.itcast.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import cn.itcast.domain.SysLog;
import cn.itcast.service.SysLogService;
/*
 * @Aspect标明当前类为切面
 * @Component 交给容器管理
 * */
@Aspect
@Component
public class ControllerLogAspect {
	
	@Autowired
	private SysLogService logService;
	
	@Autowired
	private HttpServletRequest request;
	
	private SysLog sysLog;
	
	//定义切入点 配置拦截的方法
	@Pointcut("execution(* cn.itcast.controller.*.*(..))")
	public void pointCut() {}
	
	//前置通知
	@Before("pointCut()")
	public void beforeExecute(JoinPoint jp) {
		sysLog = new SysLog();
		sysLog.setVisitTime(new Date());
		SecurityContext context = SecurityContextHolder.getContext();
		//获取存储的user对象
		User user = (User) context.getAuthentication().getPrincipal();
		sysLog.setUsername(user.getUsername());
		//ip的获取借助于HttpServletRequest
		sysLog.setIp(request.getRemoteAddr());
		//获取方法名 类名+方法名组成
		String className = jp.getTarget().getClass().getSimpleName();
		String methodName = jp.getSignature().getName();
		sysLog.setMethod(className+"=="+methodName);
	
		
	}
	
	//后置通知
	@AfterReturning("pointCut()")
	public void afterExecute() {
		sysLog.setExecuteMsg("success");
		sysLog.setExecuteResult("success");
		long currtTime = new Date().getTime();
		long vistiTime = sysLog.getVisitTime().getTime();
		sysLog.setExecuteTime(currtTime-vistiTime);
		logService.saveSysLog(sysLog);
	}
	//异常通知
	@AfterThrowing(pointcut="pointCut()",throwing="e")
	public void exceptionExecute(Exception e) {
		sysLog.setExecuteMsg(e.getMessage());
		sysLog.setExecuteResult("exception");
		long currtTime = new Date().getTime();
		long vistiTime = sysLog.getVisitTime().getTime();
		sysLog.setExecuteTime(currtTime-vistiTime);
		logService.saveSysLog(sysLog);
	}
}
