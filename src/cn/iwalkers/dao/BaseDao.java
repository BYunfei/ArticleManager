package cn.iwalkers.dao;

import java.lang.reflect.ParameterizedType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.iwalkers.util.HibernateUtil;

public class BaseDao<T> {

	private Class<T> clz;

	public void save(T t) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.currentSession();
		}
	}

	public void update(T t) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public void delete(T t) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
		}
	}

	public T get(int id) {
		Session session = HibernateUtil.currentSession();
		Transaction tx = null;
		T t = null;
		try {
			tx = session.beginTransaction();
			t = (T)session.get(getClz(), id);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			HibernateUtil.closeSession();
			return t;
		}
	}

	@SuppressWarnings("unchecked")
	private Class<T> getClz() {
		if (null == clz) {
			clz = (Class<T>) (((ParameterizedType) (this.getClass()
					.getGenericSuperclass()))
					.getActualTypeArguments()[0]);
		}
		return clz;
	}

}