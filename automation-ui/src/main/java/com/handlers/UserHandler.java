package com.handlers;

import hibernate.HibernateUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.codec.digest.Md5Crypt;

import beans.Users;

import com.dao.UserDao;
import com.sun.net.httpserver.HttpExchange;

public class UserHandler
{
	public static String validateUser(String emailId, String password, HttpExchange httpExchange) throws IOException
	{
		StringBuilder contentBuilder = new StringBuilder();
		String content = "";
		List<Users> user=UserDao.getUserWithEmailAddress(emailId,password);
			
			String pwd=null;
			for(Users user1:user){
				pwd = user1.getPass();				
			}
			
		if (!user.isEmpty()&& pwd.equals(genaratePassword(password)))
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
	
	public static String genaratePassword(String password) {
		String generatedPassword = null;
	try {
		// Create MessageDigest instance for MD5
		            MessageDigest md;					
					md = MessageDigest.getInstance("MD5");
					
		            //Add password bytes to digest
		            md.update(password.getBytes());
		            //Get the hash's bytes 
		            byte[] bytes = md.digest();
		            //This bytes[] has bytes in decimal format;
		            //Convert it to hexadecimal format
		            StringBuilder sb = new StringBuilder();
		            for(int i=0; i< bytes.length ;i++)
		            {
		                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		            }
		            
		            //Get complete hashed password in hex format
		            generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	return generatedPassword;
	}
}
