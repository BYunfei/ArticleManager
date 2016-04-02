package cn.iwalkers.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.iwalkers.entity.Article;
import cn.iwalkers.util.HibernateUtil;

public class ArticleDao{
	
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
		try{
			tx = session.beginTransaction();
			Article article = (Article)session.get(Article.class,id);
			tx.commit();
			return article;
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public List<Article> getArticleByUserId(int user_id){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		List<Article> articleList ;
		try{
			articleList = new ArrayList<>();
			tx = session.beginTransaction();
			String hql = "From Article article where author = :user_id";
			List pl = session.createQuery(hql).setString("user_id", new Integer(user_id).toString()).list();
			for(Object ele:pl ){
				Article artcile = (Article) ele;
				articleList.add(artcile);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return articleList;
	}
}