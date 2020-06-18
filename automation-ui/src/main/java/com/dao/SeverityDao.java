package com.dao;

import hibernate.HibernateUtil;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.Severity;

public class SeverityDao
{
	static Session session = HibernateUtil.getSession();

	public static List<Severity> getAllSeverity()
	{
		try
		{
			session = HibernateUtil.getSession();
			List<Severity> severityList = session.createQuery("from Severity").list();
			HibernateUtil.closeSession();
			return severityList;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}

		return null;
	}

	public static Severity getSeverityWithId(long severityId)
	{
		Severity severity = new Severity();
		try
		{
			session = HibernateUtil.getSession();
			severity = (Severity) session.get(Severity.class, severityId);
			HibernateUtil.closeSession();
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}

		return severity;
	}

	public static Severity validSeverity(String severityName)
	{
		severityName = severityName.trim();
		try
		{

			List<Severity> severityList = getAllSeverity();
			

			for (Severity severity : severityList)
			{
				if (severityName.equalsIgnoreCase(severity.getSeverityName()))
				{
					return severity;
				}

			}
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return null;
	}

	public static boolean isValidSeverityName(String currentSeverityName)
	{
		currentSeverityName = currentSeverityName.trim();
		try
		{
			session = HibernateUtil.getSession();
			Query query = session.createQuery("from Severity where severityName=:currentSeverityName");
			query.setString("currentSeverityName", currentSeverityName);
			List<Severity> results = query.list();
			HibernateUtil.closeSession();

			if (!results.isEmpty())
			{
				return true;
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return false;
	}

	public List<String> getAllSeverityNames()
	{
		try
		{
			session = HibernateUtil.getSession();
			Query query = session.createQuery("select severityName from Severity");
			List<String> results = query.list();
			HibernateUtil.closeSession();
			return results;

		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return null;
	}

}
