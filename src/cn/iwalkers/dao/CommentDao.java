package cn.iwalkers.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.iwalkers.entity.Comment;
import cn.iwalkers.util.HibernateUtil;

public class CommentDao extends BaseDao<Comment>{
	
	public List<Comment> getChild(int comment_id){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		ArrayList<Comment> childList = null;
		try{
			childList = new ArrayList<>();
			tx = session.beginTransaction();
			String hql = "from Comment comment where father=:comment_id ";
			Query query = session.createQuery(hql).setString("comment_id", comment_id+"");
			List pl = query.list();
			for(Object el:pl){
				Comment c = (Comment)el;
				childList.add(c);
			}
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtil.closeSession();
			return childList;
		}
	}
	
	public List<Comment> getRootComments(int article_id){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		ArrayList<Comment> childList = null;
		try{
			childList = new ArrayList<>();
			tx = session.beginTransaction();
			String hql = "from Comment comment where belong=:article_id and father=null";
			Query query = session.createQuery(hql).setString("article_id", article_id+"");
			List pl = query.list();
			for(Object el:pl){
				Comment c = (Comment)el;
				childList.add(c);
			}
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtil.closeSession();
			return childList;
		}
	}
	
}
