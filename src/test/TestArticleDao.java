package test;

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

	public void init() {
		User users[] = TestUtil.createRandomUser(20);
		for (int i = 0; i < users.length; i++) {
			new UserDao().save(users[i]);
		}
	}

	public void a_TestSaveArticle() {
		for (int i = 0; i < 10; i++) {

			Article article = new Article();
			article.setTitle("Hello"+" "+i);
			article.setAuthor(new UserDao().getUserById(3));
			article.setPublishDate(new Date());
			article.setContent("Hello World! "+i);
			new ArticleDao().save(article);
		}
	}
	
	public void b_TestDeleteUserWithArticle() {
		new UserDao().delete(1);
	}

	public void c_TestLoadArticleByUserId(){
		List<Article> artList = new ArticleDao().getArticleByUserId(3);
		for (Article article : artList) {
			System.out.println(article.getTitle()+" | "+article.getContent());
		}
	}
	
	@Test
	public void TestUpdateArticle(){
		int article_id = 7;
		String article_content = "Hello world! 4";
		String article_title = "Hello 4";
		
		Article article = new Article();
		article.setId(article_id);
		article.setPublishDate(new Date());
		article.setAuthor(new ArticleDao().getArticleById(article_id).getAuthor());
		article.setContent(article_content);
		article.setTitle(article_title);
		new ArticleDao().update(article);
	}
}
