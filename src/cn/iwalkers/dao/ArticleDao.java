package cn.iwalkers.dao;

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
}