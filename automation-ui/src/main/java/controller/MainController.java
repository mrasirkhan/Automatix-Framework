package controller;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.log4j.Logger;

import com.handlers.AllServiceHandler;
import com.handlers.DropDownDataHandler;
import com.handlers.FileUploadHandler;
import com.handlers.ResourceHttpServerHandler;
import com.sun.net.httpserver.HttpServer;
import com.util.LoggerUtil;

/**
 * Simple executable to start HttpServer for HTTP request/response interaction.
 */
public class MainController {
	public static final int PORT = 8004;
	public static final int BACKLOG = 0; // none
	public static final String LOGIN_URL = "/automation";
	public static final String RESOURCE_CONTEXT = "/resources";
	public static final String VALIDATE_LOGIN_DETAILS = "/loginUrl";
	public static final String FILE_UPLOAD_URL = "/uploadFileUrl";
	public static final String ALL_TEST_CASE_URL = "/getAllTestCasesUrl";
	public static final String ALL_RESULTS_URL = "/getAllResultsUrl";	
	public static final String DELETE_TEST_CASE_URL = "/deleteTestCaseUrl";
	public static final String GET_DROPDOWN_DATA_URL = "/getDropdownData";
	public static final String GET_FEATURE_MODULE_DATA = "/getModuleAndFeatureData";
	public static final String FILTERED_TESTCASE_DATA_URL="/filteredTestcaseDataUrl";
	public static final String ADD_MANUAL_TEST_CASE = "/addManualTestCaseUrl";
	public static final String CREATE_XML_URL = "/createXmlUrl";
	public static final String ADD_MANUAL_TEST_CASE_URL = "/addManualTestCaseUrl";
	public static final String UPDATE_TEST_CASE_URL="/updateTestCaseUrl";
	public static final String CHARTING_DATA_URL="/getChartingDataUrl";
	public static final String LOGOUT_URL="/logout";

	/**
	 * Main executable to run Sun's built-in JVM HTTP server.
	 * 
	 * @param args
	 *            the command line arguments
	 */
	
	private static final Logger LOGGER = Logger.getLogger(MainController.class);
	
	@SuppressWarnings("restriction")
	public static void main(String[] args) throws IOException {
		LoggerUtil.getConfig();
		LOGGER.info("START : MainController : main");
		final HttpServer server = HttpServer.create(new InetSocketAddress(PORT), BACKLOG);
		server.createContext(RESOURCE_CONTEXT, new ResourceHttpServerHandler());
		server.createContext(LOGIN_URL, new AllServiceHandler());
		server.createContext(VALIDATE_LOGIN_DETAILS, new AllServiceHandler());
		server.createContext(ALL_TEST_CASE_URL, new AllServiceHandler());
		server.createContext(ALL_RESULTS_URL, new AllServiceHandler());		
		server.createContext(FILTERED_TESTCASE_DATA_URL, new AllServiceHandler());
		server.createContext(FILE_UPLOAD_URL, new FileUploadHandler());
		server.createContext(DELETE_TEST_CASE_URL, new AllServiceHandler());
		server.createContext(ADD_MANUAL_TEST_CASE_URL, new AllServiceHandler());
		server.createContext(GET_DROPDOWN_DATA_URL, new DropDownDataHandler());
		server.createContext(GET_FEATURE_MODULE_DATA, new DropDownDataHandler());
		server.createContext(CREATE_XML_URL, new AllServiceHandler());
		server.createContext(UPDATE_TEST_CASE_URL,new AllServiceHandler());
		server.createContext(CHARTING_DATA_URL,new AllServiceHandler());
		server.createContext(LOGOUT_URL,new AllServiceHandler());
		server.setExecutor(null); // allow default executor to be created
		server.start();
		LOGGER.info("END: MainController : main");
	}
}
