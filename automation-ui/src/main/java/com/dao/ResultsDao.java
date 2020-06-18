package com.dao;

import hibernate.HibernateUtil;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import beans.Browser;
import beans.Client;
import beans.EnvironmentDetails;
import beans.ExecutionDetails;
import beans.TestCase;
import beans.TestType;

public class ResultsDao {
	static Session session;

	public static List<ExecutionDetails> getAllResults() {
		List<ExecutionDetails> results = new ArrayList<ExecutionDetails>();
		/*
		 * try { String hql =
		 * "SELECT e.executionId as executionId,e.loggedInUser as loggedInUser,e.environment as environment,e.browser as browser,e.testType as testType,e.client as client,e.currentDate as currentDate,e.shareId as shareId,e.execStatus as execStatus,e.executionXml as executionXml , (CASE WHEN e.execStatus = 'In Progress' THEN 1 WHEN e.execStatus = 'Completed' THEN 3 WHEN e.execStatus = 'Not Started' THEN 2 ELSE 3 END) as sortingColumn  FROM ExecutionDetails e Order BY  e.sortingColumn ASC ,CASE e.sortingColumn WHEN 3 THEN e.currentDate END DESC ,CASE e.sortingColumn WHEN 2 THEN e.currentDate END ASC"
		 * ; session = HibernateUtil.getSession(); Query query =
		 * session.createQuery(hql); results =
		 * query.setResultTransformer(Transformers
		 * .aliasToBean(ExecutionDetails.class) ).list();
		 * System.out.println("getAllResults ========== " + results); return
		 * results; } catch (Exception e) {
		 * System.out.println("Exception-------" + e); }
		 */
		try {
			session = HibernateUtil.getSession();
			SQLQuery query = session
					.createSQLQuery("SELECT ed.EXECUTION_ID AS executionId,ed.LOG_IN_USER AS loggedInUser,e.ENVIRONMENT_NAME AS environment,b.BROWSER_NAME AS browser,t.TEST_TYPE_NAME AS testType,c.CLIENT_NAME AS client,ed.DATE AS currentDate,ed.SHARE_ID AS shareId,ed.Exec_Status AS execStatus,ed.EXECUTION_XML AS executionXml,(CASE WHEN Exec_Status = 'In Progress' THEN 1  WHEN Exec_Status = 'Completed' THEN 3 WHEN Exec_Status = 'Not Started' THEN 2  ELSE 3 END) sortColumn FROM execution_details ed LEFT JOIN  environment e  ON ed.ENVIRONMENT =  e.ENVIRONMENT_ID LEFT JOIN browser b ON ed.browser =  b.BROWSER_ID  LEFT JOIN test_type t  ON  ed.test_type	=  t.TEST_TYPE_ID  LEFT JOIN  client c  ON  ed.client  =  c.client_id ORDER BY sortColumn , CASE WHEN sortColumn = 3 THEN DATE END DESC , CASE  WHEN sortColumn = 2 THEN DATE END ASC");
			session = HibernateUtil.getSession();
			List<Object[]> data = query.list();
			for (Object[] row : data) {
				ExecutionDetails executionDetail = new ExecutionDetails();
				executionDetail.setExecutionId(((BigInteger)row[0]).longValue());
				executionDetail.setLoggedInUser(row[1].toString());
				EnvironmentDetails environmentDetails = new EnvironmentDetails();
				environmentDetails.setEnvironmentName(row[2].toString());
				executionDetail.setEnvironment(environmentDetails);
				
				Browser browser = new Browser();
				//long browserId  =  ((BigInteger)row[3]).longValue();
				//browser.setBrowserId(browserId);
				browser.setBrowserName(row[3].toString());
				executionDetail.setBrowser(browser);
				//long testTypeId  =  ((BigInteger)row[4]).longValue();
				TestType testType = new TestType();
				//testType.setTestTypeId(testTypeId);
				testType.setTestTypeName(row[4].toString());
				executionDetail.setTestType(testType);
				Client client = new Client();
				//long clientId  =  ((BigInteger)row[5]).longValue();
				client.setClientName(row[5].toString());
				executionDetail.setCurrentDate((Date) row[6]);
				executionDetail.setExecStatus(row[8].toString());
				executionDetail.setExecutionXml(row[9].toString());
				//client.setClientId(clientId);
				executionDetail.setShareId("");
				executionDetail.setClient(client);
				results.add(executionDetail);
			}
			System.out.println("getAllResults ========== " + results);
			return results;
		} catch (Exception e) {
			System.out.println("Exception-------" + e);
		}
		return null;
	}

	@Override
	public String toString() {
		return "ResultsDao [getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
