package com.dao;

import hibernate.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

import beans.Browser;

public class BrowserDao
{
	 static Session session =null;
	 
	 
	 public void BrowserDao(){
		 session = HibernateUtil.getSession();
	 }

	public static Browser getBrowserWithId(long environmentId)
	{
		try
		{
			session = HibernateUtil.getSession();
			Browser browser = (Browser) session.get(Browser.class, environmentId);
			return browser;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return null;
	}

	public static List<Browser> getAllBrowsers()
	{
		try
		{
			session = HibernateUtil.getSession();
			List<Browser> browsers = session.createQuery("from Browser").list();
			return browsers;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}

		return null;
	}

}
