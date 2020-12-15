package com.dao;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.Users;

public class UserDao
{
	static Session session = HibernateUtil.getSession();

	public static List<Users> getUserWithEmailAddress(String currentEmailAddress, String currentPassword)	
	{
		List<Users> results = new ArrayList<>();
		try
		{			
			session = HibernateUtil.getSession();
			Query query = session.createQuery("from Users where emailAddress=:currentEmailAddress");
								
			query.setString("currentEmailAddress", currentEmailAddress);			
			results = query.list();		
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return results;
	}
}
