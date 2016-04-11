package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.iwalkers.dao.ArticleDao;
import cn.iwalkers.dao.CommentDao;
import cn.iwalkers.dao.UserDao;
import cn.iwalkers.entity.Article;
import cn.iwalkers.entity.Comment;
import cn.iwalkers.entity.User;

public class TestCommentDao {
	
	public void init(){
		List<User> uList =  createUser(5);
		List<Article> aList = createArticle(5,uList);
		new UserDao().save(uList);
		for(int i=0;i<aList.size();i++){
			System.out.println("------"+i+"-----");
			new ArticleDao().save(aList.get(i));
		}
	}
	
	public void a_TestAddComment(){
		Comment c = new Comment();
		c.setAuthor(new UserDao().get(2));
		c.setBelong(new ArticleDao().get(2));
		c.setContent("Hello");
		c.setCreateDate(new Date());
		c.setFather(null);
		new CommentDao().save(c);
		System.out.println("--------------\n"+new CommentDao().get(1).getContent());
	}
	
	public void replyComment(){
		Comment c = new Comment();
		c.setAuthor(new UserDao().get(2));
		c.setBelong(new ArticleDao().get(2));
		c.setContent("Hello");
		c.setCreateDate(new Date());
		c.setFather(new CommentDao().get(1));
		new CommentDao().save(c);
	}
	
	@Test
	public void testGetRootComment(){
		List<Comment> cList = new CommentDao().getRootComments(2);
		for(Comment c:cList){
			System.out.println("----------\n"+c.getContent());
		}
	}
	
	public void b_replayComment(){
		Comment c = new Comment();
		c.setAuthor(new UserDao().get(2));
		c.setBelong(new ArticleDao().get(2));
		c.setContent("thanks");
		c.setCreateDate(new Date());
		c.setFather(new CommentDao().get(1));
		new CommentDao().save(c);
	}
	
	private List<User> createUser(int num){
		List<User> uList = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			User u = new User();
			u.setId(i+1);
			u.setUsername("a"+i);
			u.setPassword("a"+i);
			uList.add(u);
			System.out.println(u.getId());
		}
		return uList;
	}
	
	private List<Article> createArticle(int num,List<User> uList){
		List<Article> aList = new ArrayList<>();
		for (int i = 0; i < num; i++) {			
			Article a = new Article();
			a.setTitle("title"+i);
			a.setPublishDate(new Date());
			a.setContent("content"+i);
			a.setAuthor(uList.get(i));
			aList.add(a);
		}
		return aList;
	}
	
}
