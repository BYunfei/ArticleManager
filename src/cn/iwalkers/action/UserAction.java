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

import cn.iwalkers.dao.UserDao;
import cn.iwalkers.entity.User;
import cn.iwalkers.services.UserServiceImpl;

public class UserAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, SessionAware {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map session;

	private String username;
	private String password;
	private String target;
	private String new_password;

	public String regist() throws Exception {
		if ("".equals(username)){
			this.addFieldError("username", "用户名不能为空");
			return ERROR;
		}
		if ("".equals(password)){
			this.addFieldError("password", "密码不能为空");
			return ERROR;
		}
		if (new UserDao().isName(username)) {
			this.addFieldError("username", "用户名已存在");
			return ERROR;
		} else {
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			if (new UserServiceImpl().register(user)) {
				session.put("username", user.getUsername());
				return "regist_success";
			} else {
				return "error";
			}
		}
	}

	public String login() throws Exception {
		if ("".equals(username))
			this.addFieldError("username", "用户名不能为空");
		if ("".equals(password))
			this.addFieldError("password", "密码不能为空");
		if (new UserServiceImpl().login(username, password)) {
			session.put("username", this.username);
			return "login_success";
		} else {
			this.addFieldError("password", "密码错误");
		}
		return ERROR;
	}

	public String logout() throws Exception {
		if (session.get("username") != null)
			session.remove("username");
		return "home_page";
	}

	public String updatePassword() {
		User u = new UserDao().getUserByUsername(session.get("username").toString());
		if (u.getPassword().equals(password)) {
			u.setPassword(new_password);
			if (new UserServiceImpl().update(u))
				return "user_article_list";
		}
		return ERROR;
	}

	public String catalog() {
		if (!"".equals(target))
			return target;
		return ERROR;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Map getSession() {
		return session;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
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
