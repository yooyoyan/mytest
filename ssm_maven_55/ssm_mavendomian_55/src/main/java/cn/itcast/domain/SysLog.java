package cn.itcast.domain;

import java.util.Date;

public class SysLog {
	
	private Long id;
	private Date visitTime;
	private String username;
	private String ip;
	private String method;
	private String executeResult;//成功 success  异常exception
	private String executeMsg;  //success  异常 异常具体的msg
	private Long executeTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getExecuteResult() {
		return executeResult;
	}
	public void setExecuteResult(String executeResult) {
		this.executeResult = executeResult;
	}
	public String getExecuteMsg() {
		return executeMsg;
	}
	public void setExecuteMsg(String executeMsg) {
		this.executeMsg = executeMsg;
	}
	public Long getExecuteTime() {
		return executeTime;
	}
	public void setExecuteTime(Long executeTime) {
		this.executeTime = executeTime;
	}
	
	
}
