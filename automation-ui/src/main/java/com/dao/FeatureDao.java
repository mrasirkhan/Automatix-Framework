package com.dao;

import hibernate.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.Feature;
import beans.Module;

public class FeatureDao
{
	static Session session = HibernateUtil.getSession();

	public static Feature validFeature(String featureName)
	{
		session = HibernateUtil.getSession();
		featureName = featureName.trim();
		List<Feature> featureList = getAllFeatures();
		for (Feature feature : featureList)
		{
			if (featureName.equalsIgnoreCase(feature.getFeatureName()))
			{
				return feature;
			}

		}
		return null;
	}

	public static List<Feature> getAllFeatures()
	{
		try
		{
			session = HibernateUtil.getSession();
			List<Feature> features = session.createQuery("from Feature").list();
			return features;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}

		return null;
	}

	public List<String> getAllFeatureNames()
	{
		List<String> results = new ArrayList<>();
		try
		{
			session = HibernateUtil.getSession();
			Query query = session.createQuery("select featureName from Feature");
			results = query.list();
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return results;
	}

	public static boolean isValidFeatureName(String currentFeatureName)
	{
		currentFeatureName = currentFeatureName.trim();
		try
		{
			session = HibernateUtil.getSession();
			Query query = session.createQuery("from Feature where featureName=:currentFeatureName");
			query.setString("currentFeatureName", currentFeatureName);
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

	public static Feature getFeatureWithId(long featureId)
	{
		try
		{
			session = HibernateUtil.getSession();
			Feature feature = (Feature) session.get(Feature.class, featureId);
			return feature;
		}
		catch (Exception e)
		{
			System.out.println("Exception-------" + e);
		}
		return null;
	}

}
