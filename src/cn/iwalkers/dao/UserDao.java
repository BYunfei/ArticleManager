package cn.iwalkers.dao;


import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.iwalkers.entity.User;
import cn.iwalkers.util.HibernateUtil;

public class UserDao {
	private List<User> userResult;
	private List<User> result;
	
	public boolean isName(String username){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		boolean flag = false;
		try{
			tx = session.beginTransaction();
			String hql = "From User u where username = :username";
			Query query = session.createQuery(hql);
			query.setString("username", username);
			result = query.list();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally {
			HibernateUtil.closeSession();
		}
		if(result.size()!=0)
			flag = true;
		return flag;
	}
	
	public void save(User user){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public void save(List<User> userList){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Iterator<User> iterator = userList.iterator();
			int count = 0;
			while(iterator.hasNext()){
				User u = iterator.next();
				session.save(u);
				if(++count%20==0){
					session.flush();
					session.clear();
				}
			}
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public void delete(User u){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.delete(u);
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
			User u = (User)session.get(User.class, id);
			session.delete(u);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public void update(User user){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public User getUserById(int id){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			User u = (User)session.get(User.class, id);
			tx.commit();
			return u;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			HibernateUtil.closeSession();
		}
	}
	
	public User getUserByUsername(String username){
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try{
			tx = session.getTransaction();
			String hql = "from User u where username = :username";
			Query query= session.createQuery(hql);
			query.setString("username", username);
			User u = (User)query.uniqueResult();
			return u;
		}catch(Exception e){
			e.printStackTrace();
			tx.rollback();
			return null;
		}finally{
			HibernateUtil.closeSession();
		}
	}
}
