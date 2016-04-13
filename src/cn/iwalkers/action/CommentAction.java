package cn.iwalkers.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import cn.iwalkers.dao.ArticleDao;
import cn.iwalkers.dao.CommentDao;
import cn.iwalkers.dao.UserDao;
import cn.iwalkers.entity.Comment;

public class CommentAction extends ActionSupport{
	private String comment_content;
	private int article_id;
	private int author_id;
	private int father_id;
	
	/*
	 * 添加评论功能
	 */
	public String create(){
		Comment c = new Comment();
		c.setAuthor(new UserDao().get(author_id));
		c.setBelong(new ArticleDao().get(article_id));
		c.setContent(comment_content);
		c.setCreateDate(new Date());
		new CommentDao().save(c);
		return "create_success";
	}
	
	public String reply(){
		Comment c = new Comment();
		c.setAuthor(new UserDao().get(author_id));
		c.setBelong(new ArticleDao().get(article_id));
		c.setFather(new CommentDao().get(father_id));
		c.setContent(comment_content);
		c.setCreateDate(new Date());
		new CommentDao().save(c);
		return "reply_success";
	}
	
	/*
	 * getter & setter
	 */
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public int getFather_id() {
		return father_id;
	}

	public void setFather_id(int father_id) {
		this.father_id = father_id;
	}
	
	
}
