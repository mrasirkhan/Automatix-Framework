package com.handlers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Simple HTTP Server Handler example that demonstrates how easy it is to apply
 * the Http Server built-in to Sun's Java SE 6 JVM.
 */
public class ResourceHttpServerHandler implements HttpHandler {
	private static final String IMAGE_CONTENT_TYPE = "image/png";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String PATH = "src/main";
	private static final String PNG = "png";

	/**
	 * Implementation of only required method expected of an implementation of
	 * the HttpHandler interface.
	 * 
	 * @param httpExchange
	 *            Single-exchange HTTP request/response.
	 */
	
	private static final Logger LOGGER = Logger.getLogger(ResourceHttpServerHandler.class);
	
	public void handle(HttpExchange httpExchange) throws IOException {
		LOGGER.info("START : ResourceHttpServerHandler : handle");
		String response = null;
		final OutputStream os = httpExchange.getResponseBody();
		
		if(httpExchange.getRequestURI().toString().contains(PNG)){
	    	  processImageRequest(httpExchange, os);
	      }else{
	    	  response = buildResponse(httpExchange);
	    	  httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,response.length());
	    	  os.write(response.getBytes());
		  }
		os.close();
		LOGGER.info("END: ResourceHttpServerHandler : handle");
	}

	

	private String buildResponse(HttpExchange httpExchange) throws IOException {
		LOGGER.info("START : ResourceHttpServerHandler : buildResponse");
		String filePath =  PATH + httpExchange.getRequestURI().toString();
		String response = null;
		StringBuilder contentBuilder = new StringBuilder();
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String fileData;
			while ((fileData = in.readLine()) != null) {
				contentBuilder.append(fileData);
			}
			in.close();
			response = contentBuilder.toString();
		} catch (IOException e) {
			LOGGER.error("The exception occurred while reading the data from the file and the exception is..."+e);
		}
		LOGGER.info("END: ResourceHttpServerHandler : buildResponse");
		return response;
	}
	
	private void processImageRequest(HttpExchange httpExchange,final OutputStream os) throws FileNotFoundException, IOException {
		LOGGER.info("START : ResourceHttpServerHandler : processImageRequest");
		Headers header = httpExchange.getResponseHeaders();
		header.add(CONTENT_TYPE, IMAGE_CONTENT_TYPE);
		File file = new File (PATH+httpExchange.getRequestURI().toString());
		byte [] bytearray  = new byte [(int)file.length()];
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		bis.read(bytearray, 0, bytearray.length);
		httpExchange.sendResponseHeaders(200, file.length());
		os.write(bytearray,0,bytearray.length);
		LOGGER.info("END: ResourceHttpServerHandler : processImageRequest");
	}
}