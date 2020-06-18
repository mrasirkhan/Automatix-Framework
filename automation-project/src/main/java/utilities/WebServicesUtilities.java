package utilities;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WebServicesUtilities 
{

	@SuppressWarnings("deprecation")
	public static int getStatusCode(String restURL) throws ClientProtocolException, IOException 
	{
		int statusCode = 0;
		/*HttpUriRequest request = new HttpGet(restURL);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		statusCode = httpResponse.getStatusLine().getStatusCode();*/
		@SuppressWarnings("deprecation")
		DefaultHttpClient httpClient = new DefaultHttpClient();
	    try
	    {
	        //Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
	        //Choice depends on type of method you will be invoking.
	        HttpGet getRequest = new HttpGet(restURL);
	         
	        //Set the API media type in http accept header
	        getRequest.addHeader("accept", "application/xml");
	          
	        //Send the request; It will immediately return the response in HttpResponse object
	        HttpResponse response = httpClient.execute(getRequest);
	         
	        //verify the valid error code first
	        statusCode = response.getStatusLine().getStatusCode();
	    }
	    finally
	    {
	        //Important: Close the connect
	        httpClient.getConnectionManager().shutdown();
	    }
		return statusCode;
		//Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_OK);
	}

	public static String getContent(String restURL, String element, int itemIndex) throws ClientProtocolException, IOException, SAXException, ParserConfigurationException 
	{

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(restURL);
		NodeList nodelist = doc.getElementsByTagName(element);
		return nodelist.item(0).getTextContent();
		//Assert.assertEquals(expectedValue,nodelist.item(0).getTextContent()); 
	}

	public static String getContentJSON(String restURL, String element) throws ClientProtocolException, IOException, SAXException, ParserConfigurationException, JSONException 
	{

		HttpUriRequest request = new HttpGet(restURL);
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Convert the response to a String format
		String result = EntityUtils.toString(httpResponse.getEntity());

		// Convert the result as a String to a JSON object
		JSONObject jo = new JSONObject(result);
		return jo.getString(element);
		//Assert.assertEquals(expectedValue, jo.getString(element));

	}

}
