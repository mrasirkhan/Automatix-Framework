package com.handlers;

import hibernate.HibernateUtil;
import com.util.HashUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import beans.Users;

import com.dao.UserDao;
import com.sun.net.httpserver.HttpExchange;

public class UserHandler
{
	public static String validateUser(String emailId, String password, HttpExchange httpExchange) throws IOException
	{
		StringBuilder contentBuilder = new StringBuilder();
		String content = "";
		List<Users> user=UserDao.getUserWithEmailAddress(emailId);
			
			String pwd=null;
			for(Users user1:user){
				pwd = user1.getPass();				
			}
			
		if (!user.isEmpty()&& HashUtil.validatePassword(password, pwd))
		{
			BufferedReader in = new BufferedReader(new FileReader("src/main/resources/html/main.html"));
			String str;
			while ((str = in.readLine()) != null)
			{
				contentBuilder.append(str);
			}
			String role=null;
			for(Users user1:user)
			{
				role= user1.getRole();
			}

			content = contentBuilder.toString().replaceAll("LOGIN_USER_ROLE", "<div hidden id='loginUserRole'>"+role+"</div>");

		}
		else
		{
			BufferedReader in = new BufferedReader(new FileReader("src/main/resources/html/login.html"));
			String str;
			while ((str = in.readLine()) != null)
			{
				contentBuilder.append(str);
			}
			content = contentBuilder.toString().replaceAll("ERROR_MESSAGE", "<div class='alert alert-danger'><span>Please enter valid credentials.</span></div>");
 
			//content = showError("Please enter a valid Email address.");
		}
		System.out.println("content of main.html " + content);

		return content;
	}

	private static String showError(String errorMessage) throws IOException {
		StringBuilder contentBuilder = new StringBuilder();
		BufferedReader in = new BufferedReader(new FileReader("src/main/resources/html/login.html"));
		String str;
		while ((str = in.readLine()) != null)
		{
			contentBuilder.append(str);
		}
		return contentBuilder.toString().replaceAll("ERROR_MESSAGE", "<div class='alert alert-danger'><span>" + errorMessage +"</span></div>");		
	}
	
}
