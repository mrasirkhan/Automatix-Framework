package com.dao;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import beans.Client;
import beans.ExecutionDetails;
import beans.Feature;
import beans.Module;
import beans.Severity;
import beans.TestCase;
import beans.TestType;

public class TestCaseDao
{
	static Session session= HibernateUtil.getSession();

	public TestCaseDao()
	{

		// TODO Auto-generated constructor stub
	}

	public static String saveTestCase(TestCase testcase)
	{
		try
		{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			Long id = (Long) session.save(testcase);
			testcase.setId(id);
			return "success";
			
		}
		catch (Exception e)
		{
			session.getTransaction().rollback();
			System.out.println("Exception in saveTestCase()" + e);
			return "failed";
			
		}
		finally{
			session.getTransaction().commit();
		}
	}

	public static boolean isManualTestIdExist(TestCase testcase)
	{
		try
		{
			session = HibernateUtil.getSession();
			Query query = session.createQuery("from TestCase where manualTestId=:manualId");
			query.setString("manualId", testcase.getManualTestId());
			List<TestCase> results = query.list();
			if (!results.isEmpty())
			{
				return true;
			}
			
		}
		catch (Exception e)
		{
			System.out.println("Exception in saveTestCase()" + e);
		}
		return false;
	}

	public static boolean isManualTestIdExist(String manualTestId)
	{
		try
		{
			session = HibernateUtil.getSession();
			Query query = session.createQuery("from TestCase where manualTestId=:manualId");
			query.setString("manualId", manualTestId);
			List<TestCase> results = query.list();
			if (!results.isEmpty())
			{
				return true;
			}
			
		}
		catch (Exception e)
		{
			System.out.println("Exception in saveTestCase()" + e);
		}
		return false;
	}

	public static List<TestCase> getAllTestcases()
	{
		try
		{
			session = HibernateUtil.getSession();
			List<TestCase> testcases = session.createQuery("from TestCase").list();
			return testcases;
		}

		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
	

		return null;
	}

	public static void deleteTestCases(long testId)
	{
		try
		{
			HibernateUtil.closeSession();
			session = HibernateUtil.getSession();
			session.beginTransaction();
			TestCase testCase = new TestCase();
			testCase.setId(testId);
			session.delete(testCase);
			session.getTransaction().commit();

		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		finally
		{
			session.flush();
			HibernateUtil.closeSession();
		}
	}

	public static void updateTestCases(TestCase testcase)
	{
		
		try
		{
			
			TestCase updatedtestCase = new TestCase();
			updatedtestCase.setId(testcase.getId());
			updatedtestCase.setManualTestId(testcase.getManualTestId());
			updatedtestCase.setTestDescription(testcase.getTestDescription());
			updatedtestCase.setTestObjective(testcase.getTestObjective());
			updatedtestCase.setRequirenmentNo(testcase.getRequirenmentNo());
			updatedtestCase.setSeverityName(testcase.getSeverityName());
			Feature currFeature = FeatureDao.getFeatureWithId(testcase.getFeatureId());
			updatedtestCase.setFeature(currFeature);
			Module currModule = ModuleDao.getModuleWithId(testcase.getModuleId());
			updatedtestCase.setModule(currModule);
			TestType currTestType = TestTypeDao.getTestTypeWithId(testcase.getTestTypeId());
			updatedtestCase.setTestType(currTestType);
			Severity currSeverity = SeverityDao.getSeverityWithId(testcase.getSeverityId());
			updatedtestCase.setSeverity(currSeverity);

			updatedtestCase.setScriptName(testcase.getScriptName());
			updatedtestCase.setAutomationStatus(testcase.isAutomationStatus());
			updatedtestCase.setClients(testcase.getClients());
			updatedtestCase.setApplicableForAutomation(testcase.getApplicableForAutomation());
			if (testcase.getScriptName() != null && testcase.getScriptName() != "")
			{
				updatedtestCase.setAutomationStatus(true);
			}
			session = HibernateUtil.getSession();
			session.beginTransaction();
		
			session.update(updatedtestCase);
			
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		finally
		{
			session.getTransaction().commit();
		}
	}

	public static TestCase getTestCaseWithId(long testId)
	{
		try
		{
			session = HibernateUtil.getSession();
			TestCase testcase = (TestCase) session.get(TestCase.class, testId);
			

			return testcase;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return null;
	}

	public static Set<Client> getTestcaseClientList()
	{
		List<TestCase> testCases = new ArrayList<TestCase>();
		Set<Client> clients = new HashSet<Client>();
		try
		{
			session = HibernateUtil.getSession();
			testCases = session.createQuery("from TestCase").list();
			for (TestCase test : testCases)
			{
				clients.add((Client) test.getClients());
			}
			

		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}

		return clients;
	}

	public static void saveExecutionTestDetails(Set<TestCase> testCase, ExecutionDetails executionDetails)
	{
		try
		{
			session = HibernateUtil.getSession();
			session.beginTransaction();
			executionDetails.setTestCase(testCase);
			Long id = (Long) session.save(executionDetails);

		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		finally
		{
			session.getTransaction().commit();
		
		}

	}

	@Override
	public String toString()
	{
		return "TestCaseDao [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

}
