package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;
import java.util.UUID;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream("src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	public static String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}
	
	public RequestSpecification requestSpecification() throws IOException
	{
		// Request_spec_builder:
		PrintStream log = new PrintStream(new FileOutputStream("Logging.txt", true)); // append mode
		
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.setContentType(ContentType.JSON)
				.addHeader("Accept", "application/json")
				.addHeader("User-Agent", "Mobile-App-Test")
				.addHeader("Device-info", generateDynamicDeviceInfo())
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
	}
	
	public RequestSpecification requestSpecification_Generate_OTP() throws IOException
	{
		// Request_spec_builder for Generate OTP (overwrite mode for first request):
		PrintStream log = new PrintStream(new FileOutputStream("Logging.txt", false)); // overwrite mode
		
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.setContentType(ContentType.JSON)
				.addHeader("Accept", "application/json")
				.addHeader("User-Agent", "Mobile-App-Test")
				.addHeader("Device-info", generateDynamicDeviceInfo())
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
	}
	
	public RequestSpecification requestSpecification_Update_Location() throws IOException
	{
		// Request_spec_builder for location update without Device-info header
		PrintStream log = new PrintStream(new FileOutputStream("Logging.txt", true)); // append mode
		
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.setContentType(ContentType.JSON)
				.addHeader("Accept", "*/*")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
	}
	
	public RequestSpecification requestSpecification_Update_Profile() throws IOException
	{
		// Request_spec_builder for profile update without Device-info header
		PrintStream log = new PrintStream(new FileOutputStream("Logging.txt", true)); // append mode
		
		RequestSpecification req = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseUrl"))
				.setContentType(ContentType.JSON)
				.addHeader("Accept", "application/json")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return req;
	}
	
	/**
	 * Generate dynamic device info with unique androidId for each request
	 */
	private String generateDynamicDeviceInfo() {
		String androidId = UUID.randomUUID().toString();
		String deviceInfo = String.format(
			"{\n" +
			"  \"androidId\": \"%s\",\n" +
			"  \"deviceModel\": \"Test Device\",\n" +
			"  \"osVersion\": \"Android 11\",\n" +
			"  \"appVersion\": \"1.0.0\"\n" +
			"}", androidId
		);
		
		// URL encode the JSON
		try {
			return java.net.URLEncoder.encode(deviceInfo, "UTF-8");
		} catch (Exception e) {
			return deviceInfo;
		}
	}
}

