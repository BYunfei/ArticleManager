package cn.iwalkers.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	public static final SessionFactory SESSION_FACTORY;
	
	static{
		try{
			Configuration conf = new Configuration().configure();
			ServiceRegistry sr = new ServiceRegistryBuilder().applySettings(conf.getProperties()).build();
			SESSION_FACTORY = conf.buildSessionFactory(sr);
		}catch(Throwable e){
			System.err.println("Initial SessionFactory creation failed."+e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static final ThreadLocal<Session> session = new ThreadLocal<>();
	
	public static Session currentSession() throws HibernateException{
		Session s = session.get();
		if(s == null){
			s = SESSION_FACTORY.openSession();
			session.set(s);
		}
		return s;
	}
	
	public static void closeSession() throws HibernateException{
		Session s = session.get();
		if(s!=null)
			s.close();
		session.set(null);
	}
}
