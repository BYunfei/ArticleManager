package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.iwalkers.dao.ArticleDao;
import cn.iwalkers.dao.UserDao;
import cn.iwalkers.entity.Article;
import cn.iwalkers.entity.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestArticleDao {
	
	@Before
	public void init(){
		User users[] = TestUtil.createRandomUser(20);
		for (int i = 0; i < users.length; i++) {
			new UserDao().save(users[i]);
		}
	}
	
	@Test
	public void a_TestSaveArticle(){
		Article article = new Article();
		article.setTitle("Hello");
		article.setAuthor(new UserDao().getUserById(1));
		article.setPublishDate(new Date());
		article.setContent("Hello World!");
		new ArticleDao().save(article);
	}
	
	@Test
	public void b_TestDeleteUserWithArticle(){
		new UserDao().delete(1);
	}
}
