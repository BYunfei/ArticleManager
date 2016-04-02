package cn.iwalkers.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.iwalkers.services.UserServiceImpl;

public class LoginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware{

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;
	
	private String username;
	private String password;
	
	
	
	@Override
	public String execute() throws Exception {
		if("".equals(username))
			this.addFieldError("username", "用户名不能为空");
		if("".equals(password))
			this.addFieldError("password", "密码不能为空");
		if(new UserServiceImpl().login(username, password)){
			session.put("username", this.username);
			return SUCCESS;
		}
		else{
			this.addFieldError("password", "密码错误");
		}
		return ERROR;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}



}
