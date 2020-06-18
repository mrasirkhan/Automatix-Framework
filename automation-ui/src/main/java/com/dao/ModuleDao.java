package com.dao;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.Module;

public class ModuleDao
{
	static Session session = HibernateUtil.getSession();

	public static List<Module> getAllModules()
	{
		try
		{
			session = HibernateUtil.getSession();
			List<Module> modules = session.createQuery("from Module").list();
			return modules;
		}
		catch (Exception e)
		{
			session.getTransaction().rollback();
			System.out.println("Exception-------" + e);
		}

		return null;
	}

	public List<String> getAllModuleNames()
	{
		List<String> results = new ArrayList<>();
		try
		{
			session = HibernateUtil.getSession();
			Query query = session.createQuery("select moduleName from Module");
			results = query.list();
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		
		return results;
	}

	public static Module validModule(String moduleName)
	{
			moduleName = moduleName.trim();
			List<Module> moduleList = getAllModules();
			for (Module module : moduleList)
			{
				if (moduleName.equalsIgnoreCase(module.getModuleName()))
				{
					return module;
				}
			}
		return null;
	}

	public static boolean isValidModuleName(String currentModuleName)
	{
		currentModuleName = currentModuleName.trim();
		try
		{
			session = HibernateUtil.getSession();
			Query query = session.createQuery("from Module where moduleName=:currentModuleName");
			query.setString("currentModuleName", currentModuleName);
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
		finally{
			HibernateUtil.closeSession();
		}
		return false;
	}

	public static Module getModuleWithId(long moduleId)
	{
		try
		{
			session = HibernateUtil.getSession();
			Module module = (Module) session.get(Module.class, moduleId);

			return module;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
	
		return null;
	}
}
