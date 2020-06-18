package com.dao;

import hibernate.HibernateUtil;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.Module;
import beans.TestType;

public class TestTypeDao
{

	static Session session = HibernateUtil.getSession();

	public static TestType getTestTypeWithId(long testTypeId)
	{

		try
		{
			session = HibernateUtil.getSession();
			TestType testType = (TestType) session.get(TestType.class, testTypeId);
			return testType;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return null;
	}

	public static List<TestType> getAllTestType()
	{
		try
		{
			session = HibernateUtil.getSession();
			List<TestType> testTypes = session.createQuery("from TestType").list();

			return testTypes;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}

		return null;
	}

	public List<String> getAllTestTypeNames()
	{
		session = HibernateUtil.getSession();
		Query query = session.createQuery("select testTypeName from TestType");
		List<String> results = query.list();
		
		return results;
	}

	public static TestType validTestType(String testTypeName)
	{
		try
		{

			testTypeName = testTypeName.trim();
			List<TestType> testTypeList = TestTypeDao.getAllTestType();
			

			for (TestType testType : testTypeList)
			{
				if (testTypeName.equalsIgnoreCase(testType.getTestTypeName()))
				{
					return testType;
				}

			}
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return null;
	}

	public static boolean isValidTestTypeName(String currentTestTypeName)
	{
		currentTestTypeName = currentTestTypeName.trim();
		try
		{
			session=HibernateUtil.getSession();
			Query query = session.createQuery("from TestType where testTypeName=:currentTestTypeName");
			query.setString("currentTestTypeName", currentTestTypeName);
			List<Module> results = query.list();
			

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
}
