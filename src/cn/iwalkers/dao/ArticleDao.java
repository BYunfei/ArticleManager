package cn.iwalkers.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.iwalkers.entity.Article;
import cn.iwalkers.entity.Comment;
import cn.iwalkers.util.HibernateUtil;

public class ArticleDao extends BaseDao<Article>{
	
	public void save(Article article){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(article);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public void delete(Article article){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.delete(article);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public void delete(int id){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			String hql = "delete Article article where article.id = :id";
			session.createQuery(hql).setString("id", id+"").executeUpdate();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void update(Article article){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.update(article);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtil.closeSession();
		}
	}	
	
	public Article getArticleById(int id){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		Article article;
		try{
			tx = session.beginTransaction();
			article = (Article)session.get(Article.class,id);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally{
			HibernateUtil.closeSession();
		}
		return article;
	}
	
	public List<Article> getArticleByUserId(int user_id){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		List<Article> articleList ;
		try{
			articleList = new ArrayList<>();
			tx = session.beginTransaction();
			String hql = "From Article article where author = :user_id";
			List pl = session.createQuery(hql).setString("user_id", user_id+"").list();
			for(Object ele:pl ){
				Article artcile = (Article) ele;
				articleList.add(artcile);
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally{
			HibernateUtil.closeSession();
		}
		return articleList;
	}
	
	public List<Article> getAllArticles(){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		List<Article> articleList;
		try{
			articleList = new ArrayList<>();
			tx = session.beginTransaction();
			String hql = "From Article article";
			List pl = session.createQuery(hql).list();
			for(Object ele: pl){
				Article article = (Article) ele;
				articleList.add(article);
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally{
			HibernateUtil.closeSession();
		}
		return articleList;
	}
	
	//获取页码数量
	public int getArticlePageCount(int pageSize){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session .beginTransaction();
			String hql = "select count(a) from Article as a";
			Number pageCount = (Number)session.createQuery(hql).uniqueResult();
			tx.commit();
			return (int)Math.ceil(pageCount.intValue()/6.0);
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return -1;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	//获取页码数量
	public int getArticlePageCount(int user_id,int pageSize){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session .beginTransaction();
			String hql = "select count(a) from Article as a where a.author = :user_id";
			Number pageCount = (Number)session.createQuery(hql).setString("user_id", user_id+"").uniqueResult();
			tx.commit();
			return (int)Math.ceil(pageCount.intValue()/6.0);
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return -1;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	//分页查询
	public List<Article> getArticlesPage(int start,int pageSize){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		List<Article> articleList;
		try{
			tx = session.beginTransaction();
			articleList = new ArrayList<>();
			String hql = "From Article article";
			Query q = session.createQuery(hql);
			q.setFirstResult(start);
			q.setMaxResults(pageSize);
			List pl = q.list();
			for(Object ele: pl){
				Article article = (Article)ele;
				articleList.add(article);
			}
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally{
			HibernateUtil.closeSession();
		}
		return articleList;
	}
	
	//分页查询
	public List<Article> getArticlesPage(int user_id,int start,int pageSize){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		List<Article> articleList;
		try{
			tx = session.beginTransaction();
			articleList = new ArrayList<>();
			String hql = "From Article article where author = :user_id";
			Query q = session.createQuery(hql).setString("user_id", user_id+"");
			q.setFirstResult(start);
			q.setMaxResults(pageSize);
			List pl = q.list();
			for(Object ele: pl){
				Article article = (Article)ele;
				articleList.add(article);
			}
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally{
			HibernateUtil.closeSession();
		}
		return articleList;
	}
	
	public List<Comment> getComments(int article_id){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		List<Comment> commentList = null;
		try{
			tx = session.beginTransaction();
			commentList = new ArrayList<>();
			String hql = "From Comment where belong = :article_id";
			Query q = session.createQuery(hql).setString("article_id", article_id+"");
			List pl = q.list();
			for(Object ele: pl){
				Comment c = (Comment) ele;
				commentList.add(c);
			}
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtil.closeSession();
			return commentList;
		}
	}
	
}