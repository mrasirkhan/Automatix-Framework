/*****************************************************************************
 * Copyright 2016 SEI Investments Company. 
 *
 * This software constitutes the exclusive property of SEI Investments Company,
 * and constitutes the confidential and proprietary information of SEI Investments Company.  
 * This software shall not be used or disclosed, in whole or in part, for any purpose without 
 * the prior written consent of SEI Investments Company.
******************************************************************************/

package com.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dao.ClientDao;

import beans.Client;

public class ClientHandler
{
	public static Set<Client> getClient(Map<String, Object> dataMap)
	{
		Set<Client> clientSet = new HashSet<Client>();
		Long clientId;
		Client client = new Client();
		try
		{
			if (dataMap.get("editClients") instanceof String)
			{
				clientId = Long.parseLong((String) dataMap.get("editClients"));
				client = ClientDao.getClientWithId(clientId);
				clientSet.add(client);
			}
			else
			{
				ArrayList<String> clientList = (ArrayList<String>) dataMap.get("editClients");
				for (String selClient : clientList)
				{
					clientId = Long.parseLong(selClient);
					client = ClientDao.getClientWithId(clientId);
					clientSet.add(client);
					
				}
			}

		}
		catch (Exception e)
		{
			System.out.println("Exception in getClient --------" + e);
		}
		return clientSet;
	}

}
