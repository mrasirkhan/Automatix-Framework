package hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{

	private static SessionFactory sessionFactory = buildSessionFactory();
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal();

	public static SessionFactory buildSessionFactory()
	{
		try
		{
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable ex)
		{
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException
	{
		Session session = threadLocal.get();

		if (session == null || !session.isOpen())
		{
			if (sessionFactory == null)
			{
				session.close();
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;
			threadLocal.set(session);
		}
		return session;
	}

	public static Session openNewSession() throws HibernateException
	{
		Session session = threadLocal.get();
		threadLocal.set(null);
		if (session!= null)
		{
			session.clear();
			session.flush();
			session.close();
			if (sessionFactory == null)
			{
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;
			threadLocal.set(session);
		}
		return session;
	}

	
	
	public static void rebuildSessionFactory()
	{
		try
		{
			sessionFactory = buildSessionFactory();
		}
		catch (Exception e)
		{
			System.err.println("%%%% Error Creating SessionFactory %%%%" + e);
		}
	}

	public static void closeSession() throws HibernateException
	{
		Session session = (Session) threadLocal.get();
		threadLocal.set(null);

		if (session != null)
		{
			session.close();
		}
	}

	public static void closeSessionFactory()
	{
		sessionFactory.close();

	}
}
