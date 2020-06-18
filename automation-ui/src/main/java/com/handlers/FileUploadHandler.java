package com.handlers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FileUploadHandler implements HttpHandler
{
	@Override
	public void handle(final HttpExchange httpExchange) throws IOException
	{
		for (Entry<String, List<String>> header : httpExchange.getRequestHeaders().entrySet())
		{
			System.out.println(header.getKey() + ": " + header.getValue().get(0));
		}
		DiskFileItemFactory d = new DiskFileItemFactory();

		try
		{
			ServletFileUpload up = new ServletFileUpload(d);
			List<FileItem> result = up.parseRequest(new RequestContext()
			{

				@Override
				public String getCharacterEncoding()
				{
					return "UTF-8";
				}

				@Override
				public int getContentLength()
				{
					return 0; // tested to work with 0 as return
				}

				@Override
				public String getContentType()
				{
					return httpExchange.getRequestHeaders().getFirst("Content-type");
				}

				@Override
				public InputStream getInputStream() throws IOException
				{
					return httpExchange.getRequestBody();
				}

			});

			OutputStream os = httpExchange.getResponseBody();
			for (FileItem fi : result)
			{
				InputStream inputStream = fi.getInputStream();
				String path = com.util.AutomationUtil.getBasePath();
				System.out.println("path------ " + path);
				File file = new File(path + "/ServerFiles/" + fi.getName());
				OutputStream outputStream = new FileOutputStream(file);

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1)
				{
					outputStream.write(bytes, 0, read);
				}

				System.out.println("Done!");

				String response = null;
				response = TestCaseDetailsHandler.validateUploadedTestcases(file);
				System.out.println("response --" + response);
				if (file.exists())
					file.delete();
				outputStream.close();
				httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, response.length());
				byte[] buf = new byte[1024];
				ByteArrayInputStream is = new ByteArrayInputStream(response.getBytes("Windows-1252"));
				int c = 0;
				while ((c = is.read(buf)) != -1)
				{
					System.out.println("length" + c);
					os.write(buf, 0, c);
					os.flush();
				}
				is.close();
				os.close();
				os.flush();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}