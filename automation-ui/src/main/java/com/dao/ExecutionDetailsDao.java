package com.dao;

import hibernate.HibernateUtil;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.Test;

import beans.Client;
import beans.ExecutionDetails;
import beans.RawResults;
import beans.TestCase;

public class ExecutionDetailsDao
{
	static Session session = HibernateUtil.getSession();

	public static void saveExecutionDetails(Set<TestCase> testCaseSet, ExecutionDetails executionDetails)
	{
		try
		{

			executionDetails.setTestCase(testCaseSet);
			session=HibernateUtil.getSession();
			session.beginTransaction();
			Long id = (Long) session.save(executionDetails);
			executionDetails.setExecutionId(id);
			ExecutionDetails latestExecutionDetails = (ExecutionDetails) session.load(ExecutionDetails.class, id);
			session.getTransaction().commit();
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		finally{
			HibernateUtil.closeSession();
		}
	}

	public static ExecutionDetails getExecutionDetails(long executionId)
	{
		try
		{
			session=HibernateUtil.getSession();
			ExecutionDetails executionDetails = (ExecutionDetails) session.get(ExecutionDetails.class, executionId);

			return executionDetails;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return null;
	}

	public static void saveExecutionTestDetails(Set<TestCase> testCase, ExecutionDetails executionDetails)
	{

		try
		{
			session=HibernateUtil.getSession();
			session.beginTransaction();
			executionDetails.setTestCase(testCase);
			Long id = (Long) session.save(executionDetails);
			session.getTransaction().commit();

		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		finally
		{
			HibernateUtil.closeSession();
		}

	}

}
