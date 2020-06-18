/*****************************************************************************
 * Copyright 2016 SEI Investments Company. 
 *
 * This software constitutes the exclusive property of SEI Investments Company,
 * and constitutes the confidential and proprietary information of SEI Investments Company.  
 * This software shall not be used or disclosed, in whole or in part, for any purpose without 
 * the prior written consent of SEI Investments Company.
******************************************************************************/

package com.dao;

import hibernate.HibernateUtil;

import java.util.List;

import org.hibernate.Session;

import beans.Client;

public class ClientDao
{
	static Session session = HibernateUtil.getSession();

	public static List<Client> getAllClients()
	{
		try
		{
			session = HibernateUtil.getSession();
			List<Client> clients = session.createQuery("from Client").list();
			
			return clients;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}

		return null;
	}

	public static Client getClientWithId(long clientId)
	{
		Client client = new Client();
		try
		{
			session = HibernateUtil.getSession();
			client = (Client) session.get(Client.class, clientId);

			return client;
		}
		catch (Exception e)
		{
			System.out.println("Exception while getting client on client id-------" + e);
		}
		return client;
	}
}
