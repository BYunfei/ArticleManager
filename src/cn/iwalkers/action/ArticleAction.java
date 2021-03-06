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
	private String article_summary;
	private String author_name;
	private String pageNow;
	private String pageCount;
	private String pageSize;
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;

	/*
	 * 更新文章
	 */
	public String update(){
		Article article = new Article();
		article.setId(article_id);
		article.setPublishDate(new Date());
		article.setAuthor(new ArticleDao().get(article_id).getAuthor());
		article.setContent(article_content);
		article.setTitle(article_title);
		article.setSummary(article_summary);
		new ArticleDao().update(article);
		return "update_success";
	}
	
	/*
	 * 删除文章
	 */
	public String delete(){
		new ArticleDao().delete(article_id);
		return SUCCESS;
	}
	
	/*
	 * 跳转
	 */
	public String catalog(){
		target = request.getParameter("target");
		if(target == null || "".equals(target.trim())){
			return "homePage";
		}
		return target;
	}
	
	/*
	 * 添加文章
	 */
	public String add(){
		Article article = new Article();
		article.setTitle(article_title);
		article.setAuthor(new UserDao().getUserByUsername(author_name));
		article.setPublishDate(new Date());
		article.setContent(article_content);
		article.setSummary(article_summary);
		new ArticleDao().save(article);
		return SUCCESS;
	}

	
	
	public String getArticle_summary() {
		return article_summary;
	}

	public void setArticle_summary(String article_summary) {
		this.article_summary = article_summary;
	}

	public String getPageNow() {
		return pageNow;
	}

	public void setPageNow(String pageNow) {
		this.pageNow = pageNow;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	
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

}
