package com.intuit.qbo.subscription.framework.helper.dbHelper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author bsingh5
 *
 */
public class HibernateUtil {
	
	private static final SessionFactory sessionFactory;
	
	static {
		try {
			Configuration cfg = new Configuration();
			String cfglocation = "hibernet.cfg.xml";
			System.out.println(cfglocation);
			cfg.configure(cfglocation); 
		    sessionFactory =cfg.buildSessionFactory();  
		    
		    //sessionFactory =  new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
