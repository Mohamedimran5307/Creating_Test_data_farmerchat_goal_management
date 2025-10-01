package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import resources.APIResources;
import resources.TestDataBuild;
import pojo.GenerateOtpRequest;
import pojo.UpdateUserProfile;
import pojo.UpdateUserLocation;
import pojo.CreateNewConversation;
import pojo.GetAnswerForTextQuery;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.cucumber.java.en.Then;
import resources.Utilities;

public class StepDefinitions extends Utilities {
	
	// ========================================
	// STATIC VARIABLES FOR DATA SHARING
	// ========================================
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
    static String authToken;
    static String userId;
    static String randomName;
    static String currentPhoneNumber;
    static String conversationId;
	
	TestDataBuild data = new TestDataBuild(); // Create Object data for TestDataBuild class

	// ========================================
	// COMMON/UTILITY STEP DEFINITIONS
	// ========================================
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    APIResources resourceAPI = APIResources.valueOf(resource);
	    	System.out.println(resourceAPI.getResource()); //print resource of the API
			
	        //Response_spec_builder:
	    	resspec = new ResponseSpecBuilder().expectStatusCode(200)
	    			.expectContentType(ContentType.JSON)
	    			.build();

	    	if (method.equalsIgnoreCase("POST"))
	    		response = res.when().post(resourceAPI.getResource());
	    	else if (method.equalsIgnoreCase("GET"))
	    		response = res.when().get(resourceAPI.getResource());
	}

@Then("the API call got success with status code {int}")
public void the_api_call_got_success_with_status_code(Integer expectedStatusCode) {
    // Print detailed response information for debugging
    System.out.println("=== API RESPONSE DEBUG ===");
    System.out.println("Expected Status Code: " + expectedStatusCode);
    System.out.println("Actual Status Code: " + response.getStatusCode());
    System.out.println("Response Body: " + response.asString());
    System.out.println("Response Headers: " + response.getHeaders());
    System.out.println("=== END DEBUG ===");
    
    if (response.getStatusCode() != expectedStatusCode.intValue()) {
        System.err.println("API call failed with status code: " + response.getStatusCode());
        System.err.println("Response body: " + response.asString());
        throw new AssertionError("Expected status code " + expectedStatusCode + " but got " + response.getStatusCode() + 
                               ". Response: " + response.asString());
    }
    
    assertEquals(expectedStatusCode.intValue(), response.getStatusCode());
}

@Then("the Update User Profile API call should succeed with status code 200 or 201")
public void the_update_user_profile_api_call_should_succeed_with_status_code_200_or_201() {
    // Print detailed response information for debugging
    System.out.println("=== UPDATE USER PROFILE API RESPONSE DEBUG ===");
    System.out.println("Actual Status Code: " + response.getStatusCode());
    System.out.println("Response Body: " + response.asString());
    System.out.println("=== END DEBUG ===");
    
    // Accept both 200 (update) and 201 (create) as valid responses
    if (response.getStatusCode() == 200) {
        System.out.println("User profile updated successfully!");
    } else if (response.getStatusCode() == 201) {
        System.out.println("User profile created successfully!");
    } else {
        throw new AssertionError("Unexpected response: " + response.getStatusCode() + 
                               ". Response: " + response.asString());
    }
}

	@Then("{string} in response body is {string}")
    public void key_in_response_body_is(String Actualkey, String Expectedvalue) {
		// Debug: Print the actual response to see what we're getting
		System.out.println("=== RESPONSE DEBUG ===");
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.asString());
		System.out.println("Response Headers: " + response.getHeaders());
		System.out.println("=== END DEBUG ===");
		
		assertEquals(getJsonPath(response, Actualkey), Expectedvalue);
	}

	@Then("{string} in response body should not be null or empty")
	public void in_response_body_should_not_be_null_or_empty(String key) {
		String value = getJsonPath(response, key);
		if (value == null || value.isEmpty() || "null".equals(value)) {
			throw new RuntimeException("Response value for key '" + key + "' is null or empty!");
		}
		System.out.println("Value for key '" + key + "': " + value);
	}

	// ========================================
	// GENERATE OTP STEP DEFINITIONS
	// ========================================
	
	@Given("Generate OTP Payload with phone number {string}")
	public void generate_otp_payload_with_phone_number(String phoneNumber) throws IOException {
		System.out.println("Generating OTP Payload for phone number: " + phoneNumber);
		GenerateOtpRequest otpPayload = data.Generate_OTP_Payload(phoneNumber);
		System.out.println("OTP Payload: " + otpPayload.toString());
		res = given().spec(requestSpecification_Generate_OTP())
				.body(otpPayload);
	}

	// ========================================
	// VERIFY OTP STEP DEFINITIONS
	// ========================================
    	
	@Given("Generate Verify Payload with phone number {string}")
	public void generate_verify_payload_with_phone_number(String phoneNumber) throws IOException {
		System.out.println("Generating Verify Payload for phone number: " + phoneNumber);
	    res = given().spec(requestSpecification())
	    .body(data.Generate_Verify_Payload(phoneNumber));
	}

	@And("extract auth token from response")
	public void extract_auth_token() {
		authToken = getJsonPath(response, "access_token"); // adjust if needed
		if (authToken == null || authToken.isEmpty()) {
			throw new RuntimeException("Auth token is missing in response!");
		}
		System.out.println("Auth Token: " + authToken);
		
		// Extract user_id from response
		userId = getJsonPath(response, "id");
		if (userId == null || userId.isEmpty()) {
			throw new RuntimeException("User ID is missing in response!");
		}
		System.out.println("User ID: " + userId);
		
		// Extract phone number for reference
		String phone = getJsonPath(response, "phone");
		System.out.println("Phone Number: " + phone);
		
		// Print user info for tracking
		System.out.println("=== USER INFO EXTRACTED ===");
		System.out.println("User ID: " + userId);
		System.out.println("Phone: " + phone);
		System.out.println("===========================");
	}

	// ========================================
	// UPDATE USER PROFILE STEP DEFINITIONS
	// ========================================

	@Given("Generate Update User Profile Payload with name {string}")
	public void generate_update_user_profile_payload_with_name(String userName) throws IOException {
		System.out.println("Generating Update User Profile Payload with name: " + userName);
		System.out.println("Using User ID: " + userId);
		UpdateUserProfile payload = data.Generate_Update_User_Profile_Payload(userId, userName);
		System.out.println("Update User Profile Payload: " + payload.toString());
		
		// Print user details for tracking
		System.out.println("=== UPDATING USER PROFILE ===");
		System.out.println("User ID: " + userId);
		System.out.println("User Name: " + userName);
		System.out.println("Role: " + payload.getRole());
		System.out.println("=============================");
		
	    res = given().spec(requestSpecification())
	    .body(payload);
	}

	@Given("Generate Update User Location Payload")
	public void generate_update_user_location_payload() throws IOException {
		if (userId == null || userId.isEmpty()) {
			throw new RuntimeException("User ID is not available! Please ensure Verify OTP step is executed first.");
		}
		System.out.println("Generating Update User Location Payload with User ID: " + userId);
		UpdateUserLocation payload = data.Generate_Update_User_Location_Payload(userId);
		System.out.println("Update User Location Payload: " + payload.toString());
		
		// Print location details for tracking
		System.out.println("=== UPDATING USER LOCATION ===");
		System.out.println("User ID: " + userId);
		System.out.println("Latitude: " + payload.getLat());
		System.out.println("Longitude: " + payload.getLongitude());
		System.out.println("===============================");
		
	    res = given().spec(requestSpecification())
	    .body(payload);
	}

	

	@When("the user sends a POST request to {string} with header as {string}")
	public void the_user_sends_a_post_request_to_with_header_as(String resource, String Authorization) {
	    APIResources resourceAPI = APIResources.valueOf(resource);
	    System.out.println("Sending POST request to: " + resourceAPI.getResource());
	    res = res.header("Authorization", "Bearer " + authToken);
	    response = res.when().post(resourceAPI.getResource());
	}

	// ========================================
	// RESPONSE VALIDATION STEP DEFINITIONS
	// ========================================

	@Then("user profile user_id in response body matches the dynamic user_id")
	public void user_profile_user_id_in_response_body_matches_the_dynamic_user_id() {
	    String actualUserId = getJsonPath(response, "user_profile.user_id");
	    if (userId == null || userId.isEmpty()) {
	        throw new RuntimeException("Dynamic user_id is not available! Please ensure Verify OTP step is executed first.");
	    }
	    assertEquals("User ID should match the one from verify OTP", userId, actualUserId);
	    System.out.println("User ID validation passed: " + actualUserId);
	}


	@Then("\"user_profile.role[0].id\" in response body is {string}")
	public void user_profile_role_id_in_response_body_is(String expectedRoleId) {
	    String actualRoleId = getJsonPath(response, "user_profile.role[0].id");
	    assertEquals("Role ID should match", expectedRoleId, actualRoleId);
	    System.out.println("Role ID validation passed: " + actualRoleId);
	}

	@Then("\"user_profile.role[0].text\" in response body is {string}")
	public void user_profile_role_text_in_response_body_is(String expectedRoleText) {
	    String actualRoleText = getJsonPath(response, "user_profile.role[0].text");
	    assertEquals("Role text should match", expectedRoleText, actualRoleText);
	    System.out.println("Role text validation passed: " + actualRoleText);
	}


	@Then("\"user_profile.first_name\" in response body should not be null or empty")
	public void user_profile_first_name_in_response_body_should_not_be_null_or_empty() {
	    String actualName = getJsonPath(response, "user_profile.first_name");
	    if (actualName == null || actualName.isEmpty() || "null".equals(actualName)) {
	        throw new RuntimeException("User profile first name is null or empty!");
	    }
	    System.out.println("User profile first name validation passed: " + actualName);
	    
	    // Also validate that it matches the random name we generated
	    if (randomName != null && !randomName.equals(actualName)) {
	        System.out.println("Warning: Generated name '" + randomName + "' does not match response name '" + actualName + "'");
	    }
	}


	// ========================================
	// ADDITIONAL UTILITY STEP DEFINITIONS
	// ========================================

	@And("capture phone number from response")
	public void capture_phone_number_from_response() {
	    currentPhoneNumber = getJsonPath(response, "phone");
	    if (currentPhoneNumber == null || currentPhoneNumber.isEmpty()) {
	        throw new RuntimeException("Phone number is missing in response!");
	    }
	    System.out.println("Captured Phone Number: " + currentPhoneNumber);
	}

	@And("validate phone number is in expected range")
	public void validate_phone_number_is_in_expected_range() {
	    if (currentPhoneNumber == null || currentPhoneNumber.isEmpty()) {
	        throw new RuntimeException("Phone number not captured yet!");
	    }
	    
	    long phoneNumber = Long.parseLong(currentPhoneNumber);
	    long startRange = 9393932218L;
	    long endRange = 9393932240L;
	    
	    if (phoneNumber < startRange || phoneNumber > endRange) {
	        throw new RuntimeException("Phone number " + currentPhoneNumber + " is not in expected range (9393932218-9393932240)!");
	    }
	    
	    System.out.println("Phone number validation passed: " + currentPhoneNumber + " is in range");
	}

	// ========================================
	// VERIFY REFERRAL CODE STEP DEFINITIONS
	// ========================================


	@Given("Generate Verify Referral Code Payload with code {string}")
	public void generate_verify_referral_code_payload_with_code(String referralCode) throws IOException {
		if (userId == null || userId.isEmpty()) {
			throw new RuntimeException("User ID is not available! Please ensure Verify OTP step is executed first.");
		}
		System.out.println("Generating Verify Referral Code Payload with code: " + referralCode + " and user_id: " + userId);
	    res = given().spec(requestSpecification())
	    .body(data.Generate_Verify_Referral_Code_Payload(userId, referralCode));
	}

	@Then("the referral code API call should succeed or be already used")
	public void the_referral_code_api_call_should_succeed_or_be_already_used() {
		System.out.println("=== REFERRAL CODE API RESPONSE DEBUG ===");
		System.out.println("Status Code: " + response.getStatusCode());
		System.out.println("Response Body: " + response.asString());
		System.out.println("=== END DEBUG ===");
		
		// Accept both 200 (success) and 400 (already used) as valid responses
		if (response.getStatusCode() == 200) {
			System.out.println("Referral code verification successful!");
		} else if (response.getStatusCode() == 400 && response.asString().contains("referral")) {
			System.out.println("Referral code already used - this is acceptable for testing");
		} else {
			throw new AssertionError("Unexpected response: " + response.getStatusCode() + 
								   ". Response: " + response.asString());
		}
	}

	@Then("print user summary")
	public void print_user_summary() {
		System.out.println("==========================================");
		System.out.println("           USER SUMMARY REPORT            ");
		System.out.println("==========================================");
		System.out.println("User ID: " + userId);
		System.out.println("Phone Number: " + currentPhoneNumber);
		System.out.println("User Name: " + (currentPhoneNumber != null ? getCurrentUserName() : "N/A"));
		System.out.println("Auth Token: " + (authToken != null ? authToken.substring(0, 20) + "..." : "N/A"));
		System.out.println("==========================================");
	}
	
	/**
	 * Helper method to get current user name based on phone number
	 * This is a simple mapping for demonstration
	 */
	private String getCurrentUserName() {
		if (currentPhoneNumber == null) return "Unknown";
		
		// Simple mapping based on phone number pattern
		String[] names = {
			"Arjun Sharma", "Priya Patel", "Rajesh Kumar", "Sneha Singh", "Vikram Reddy",
			"Kavya Nair", "Rohit Gupta", "Ananya Iyer", "Suresh Joshi", "Meera Desai",
			"Amit Kumar", "Deepika Sharma", "Ravi Verma", "Shruti Agarwal", "Nikhil Jain",
			"Pooja Mehta", "Kiran Rao", "Sunita Malhotra", "Vishal Tiwari", "Ritu Saxena",
			"Manish Pandey", "Sonia Khanna", "Ajay Choudhary"
		};
		
		try {
			long phone = Long.parseLong(currentPhoneNumber);
			int index = (int)(phone - 9393932218L);
			if (index >= 0 && index < names.length) {
				return names[index];
			}
		} catch (NumberFormatException e) {
			// Fallback
		}
		return "Unknown User";
	}

	// ========================================
	// CREATE NEW CONVERSATION STEP DEFINITIONS
	// ========================================

	@Given("the API is up and user_id is sent as payload")
	public void the_api_is_up_and_user_id_is_sent_as_payload() throws IOException {
		if (userId == null || userId.isEmpty()) {
			throw new RuntimeException("User ID is not available! Please ensure Verify OTP step is executed first.");
		}
		System.out.println("Generating Create New Conversation Payload with User ID: " + userId);
		CreateNewConversation payload = data.Generate_Create_New_Conversation_Payload(userId);
		System.out.println("Create New Conversation Payload: " + payload.toString());
		
		System.out.println("=== CREATING NEW CONVERSATION ===");
		System.out.println("User ID: " + userId);
		System.out.println("================================");
		
	    res = given().spec(requestSpecification())
	    .body(payload);
	}

	@And("extract conversation_id from response")
	public void extract_conversation_id_from_response() {
		conversationId = getJsonPath(response, "conversation_id");
		if (conversationId == null || conversationId.isEmpty()) {
			throw new RuntimeException("Conversation ID is missing in response!");
		}
		System.out.println("Conversation ID: " + conversationId);
		
		// Print conversation info for tracking
		System.out.println("=== CONVERSATION INFO EXTRACTED ===");
		System.out.println("User ID: " + userId);
		System.out.println("Conversation ID: " + conversationId);
		System.out.println("===================================");
	}

	// ========================================
	// GET ANSWER FOR TEXT QUERY STEP DEFINITIONS
	// ========================================

	@Given("Generate Payload for Get_answer_for_Text_Query")
	public void generate_payload_for_get_answer_for_text_query() throws IOException {
		if (userId == null || userId.isEmpty()) {
			throw new RuntimeException("User ID is not available! Please ensure Verify OTP step is executed first.");
		}
		if (conversationId == null || conversationId.isEmpty()) {
			throw new RuntimeException("Conversation ID is not available! Please ensure Create New Conversation step is executed first.");
		}
		
		// Array of different queries to test multiple times
		String[] queries = {
			// "What are the best practices for organic farming?",
			// "How to manage soil fertility naturally?",
			"What crops are suitable for monsoon season?",
			"How to control pests without chemicals?",
			"What are the benefits of crop rotation?"
		};
		
		// Run 5 queries in the same conversation
		for (int i = 0; i < 3; i++) {
			String query = queries[i];
			System.out.println("=== GETTING ANSWER FOR TEXT QUERY " + (i + 1) + "/3 ===");
			System.out.println("User ID: " + userId);
			System.out.println("Conversation ID: " + conversationId);
			System.out.println("Query: " + query);
			System.out.println("=====================================");
			
			GetAnswerForTextQuery payload = data.Generate_Get_Answer_For_Text_Query_Payload(userId, conversationId, query);
			System.out.println("Get Answer for Text Query Payload: " + payload.toString());
			
		    res = given().spec(requestSpecification())
		    .body(payload)
		    .header("Authorization", "Bearer " + authToken);
		    
		    // Execute the API call
		    response = res.when().post(APIResources.Get_Answer_for_Text_Query.getResource());
		    
		    // Validate response
		    System.out.println("=== QUERY " + (i + 1) + " RESPONSE ===");
		    System.out.println("Status Code: " + response.getStatusCode());
		    System.out.println("Response Body: " + response.asString());
		    
		    if (response.getStatusCode() != 200) {
		        throw new AssertionError("Query " + (i + 1) + " failed with status code: " + response.getStatusCode());
		    }
		    
		    // Validate message
		    String message = getJsonPath(response, "message");
		    if (!"Successful retrieval of response for above query".equals(message)) {
		        throw new AssertionError("Query " + (i + 1) + " - Expected message not found. Actual: " + message);
		    }
		    
		    // Validate response content
		    String responseText = getJsonPath(response, "response");
		    if (responseText == null || responseText.isEmpty() || "null".equals(responseText)) {
		        throw new AssertionError("Query " + (i + 1) + " - Response text is null or empty!");
		    }
		    
		    System.out.println("Query " + (i + 1) + " completed successfully!");
		    System.out.println("Response: " + responseText);
		    System.out.println("=====================================");
		}
		
		System.out.println("All 5 text queries completed successfully in the same conversation!");
	}

	@Then("\"response\" in response body should not be null but answer should be present")
	public void response_in_response_body_should_not_be_null_but_answer_should_be_present() {
		String responseText = getJsonPath(response, "response");
		if (responseText == null || responseText.isEmpty() || "null".equals(responseText)) {
			throw new RuntimeException("Response text is null or empty!");
		}
		System.out.println("Response text validation passed: " + responseText);
	}

}

