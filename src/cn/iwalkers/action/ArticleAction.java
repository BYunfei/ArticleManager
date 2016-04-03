package cn.iwalkers.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import cn.iwalkers.dao.ArticleDao;
import cn.iwalkers.dao.UserDao;
import cn.iwalkers.entity.Article;

public class ArticleAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private String target;
	private int article_id;
	private String article_title;
	private String article_content;
	private String author_name;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	public String getAuthor_name() {
		return author_name;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}


	public int getArticle_id() {
		return article_id;
	}
	
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public String edit(){
		return "editArticle";
	}
	
	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String update(){
		Article article = new Article();
		article.setId(article_id);
		article.setPublishDate(new Date());
		article.setAuthor(new ArticleDao().getArticleById(article_id).getAuthor());
		article.setContent(article_content);
		article.setTitle(article_title);
		new ArticleDao().update(article);
		return "update_success";
	}
	
	public String delete(){
		new ArticleDao().delete(article_id);
		return SUCCESS;
	}
	
	public String addPage(){
		return "addPage";
	}
	
	public String catalog(){
		target = request.getParameter("target");
		if(target == null || "".equals(target.trim())){
			return "homePage";
		}
		return target;
	}
	
	public String add(){
		Article article = new Article();
		article.setTitle(article_title);
		article.setAuthor(new UserDao().getUserByUsername(author_name));
		article.setPublishDate(new Date());
		article.setContent(article_content);
		new ArticleDao().save(article);
		return SUCCESS;
	}
}
