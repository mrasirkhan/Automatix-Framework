package com.dao;

import hibernate.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

import beans.EnvironmentDetails;

public class EnvironmentDao
{
	static Session session = HibernateUtil.getSession();

	public static List<EnvironmentDetails> getAllEnvironments()
	{

		try
		{
			session = HibernateUtil.getSession();
			List<EnvironmentDetails> environments = session.createQuery("from EnvironmentDetails").list();

			return environments;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}

		return null;
	}

	public static EnvironmentDetails getEnvironmentWithId(long environmentId)
	{
		try
		{
			session = HibernateUtil.getSession();
			EnvironmentDetails environment = (EnvironmentDetails) session.get(EnvironmentDetails.class, environmentId);

			return environment;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return null;
	}
}
