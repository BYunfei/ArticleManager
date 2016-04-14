package test;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.iwalkers.dao.ArticleDao;
import cn.iwalkers.dao.CommentDao;
import cn.iwalkers.dao.UserDao;
import cn.iwalkers.entity.Article;
import cn.iwalkers.entity.Comment;
import cn.iwalkers.entity.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestArticleDao {

	public void init() {
		User users[] = TestUtil.createRandomUser(20);
		for (int i = 0; i < users.length; i++) {
			new UserDao().save(users[i]);
		}
	}
	
	@Test
	public void a_TestSaveArticle() {
		for (int i = 0; i < 10; i++) {

			Article article = new Article();
			article.setTitle("Hello" + " " + i);
			article.setAuthor(new UserDao().getUserById(3));
			article.setPublishDate(new Date());
			article.setContent("Hello World! " + i);
			new ArticleDao().save(article);
		}
	}

	public void b_TestDeleteUserWithArticle() {
		new UserDao().delete(1);
	}

	public void c_TestLoadArticleByUserId() {
		List<Article> artList = new ArticleDao().getArticleByUserId(3);
		for (Article article : artList) {
			System.out.println(article.getTitle() + " | " + article.getContent());
		}
	}

	public void TestUpdateArticle() {
		int article_id = 7;
		String article_content = "Hello world! 4";
		String article_title = "Hello 4";

		Article article = new Article();
		article.setId(article_id);
		article.setPublishDate(new Date());
		article.setAuthor(new ArticleDao().get(article_id).getAuthor());
		article.setContent(article_content);
		article.setTitle(article_title);
		new ArticleDao().update(article);
	}

	public void TestLoadAllArticles() {
		for (int i = 0; i < 100; i++) {
			System.out.println("------------" + i + "--------------");
			List<Article> articleList = new ArticleDao().getAllArticles();
			for (Article a : articleList) {
				// System.out.println(a.getTitle()+" \t| "+
				// a.getAuthor().getUsername() +" \t| "+a.getContent());
				System.out.println(a.getTitle() + "   \t| " + a.getContent());
			}
		}
	}

	public void TestArticlePage() {
		int pageCount = new ArticleDao().getArticlePageCount(6);
		System.out.println("一共有：" + pageCount + "页");
		for (int i = 0; i < pageCount; i++) {
			List<Article> list = new ArticleDao().getArticlesPage(i*6, 6);
			System.out.println("------ 第 "+(i+1)+" 页 -------");
			for (Article a : list) {
				System.out.println(a.getTitle() + "   \t| " + a.getContent());
			}
		}
	}
	
	public void TestGetArticle(){
		Article a = new ArticleDao().get(12);
		System.out.println(a.getTitle());
	}
	
	public void TestSave(){
		Article a = new Article();
		a.setTitle("Test BaseDao<Article>");
		a.setContent("\n");
		a.setAuthor(new UserDao().getUserByUsername("abc"));
		a.setPublishDate(new Date());
		new ArticleDao().save(a);
	}
	
	public void TestGetComment(){
//		Comment c = new CommentDao().get(1);
//		System.out.println(c.getAuthor().getUsername());
//		Article a = new ArticleDao().get(1);
		List<Comment> c = new ArticleDao().getComments(1);
		for(Comment comment : c){
			System.out.println("来自:"+comment.getAuthor().getUsername()+"的评论："+comment.getContent());
			List<Comment> replayList = new CommentDao().getChild(comment.getId());
			for(Comment replay: replayList)
				if(replay!=null)
					System.out.println("回复："+replay.getContent());
		}
	}
}
