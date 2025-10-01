 Feature: Update User Profile API Testing
  As a mobile app user
  I want to test the complete user profile update flow
  So that I can verify all APIs work correctly

  @E2E_Integration
  Scenario Outline: Complete E2E Integration Test for User Profile Update
    Given Generate OTP Payload with phone number "<phone_number>"
    When user calls "Generate_OTP" with "POST" http request
    Then the API call got success with status code 201
    And capture phone number from response
    
    Given Generate Verify Payload with phone number "<phone_number>"
    When user calls "Verify_OTP" with "POST" http request
    Then the API call got success with status code 200
    And extract auth token from response
    
    Given Generate Update User Profile Payload with name "<user_name>"
    When the user sends a POST request to "Update_User_Profile" with header as "Authorization"
    Then the Update User Profile API call should succeed with status code 200 or 201
    And "user_profile.first_name" in response body is "<user_name>"
    And user profile user_id in response body matches the dynamic user_id
    
    Given Generate Update User Location Payload
    When the user sends a POST request to "Update_user_location" with header as "Authorization"
    Then the API call got success with status code 200
    And "user_profile.lat" in response body should not be null or empty
    And "user_profile.long" in response body should not be null or empty
    And "user_profile.geography_level2_name" in response body should not be null or empty
    
    Given Generate Verify Referral Code Payload with code "<referral_code>"
    When the user sends a POST request to "Verify_Referral_Code" with header as "Authorization"
    Then the referral code API call should succeed or be already used
    And "message" in response body is "You are mapped to your referrer successfully"
    Then print user summary

  Examples:

      | referral_code | user_name  | phone_number|
      | 9090909011    | Ravi      | 8080808001   |
      | 9090909011    | Ramesh    | 8080808002   |
      | 9090909011    | Varun     | 8080808003   |
      | 9090909011    | Imran     | 8080808004   |
      | 9090909011    | Areef     | 8080808005   |
      | 9090909011    | Sameer    | 8080808006   |
      | 9090909011    | Geeresh   | 8080808007   |
      | 9090909011    | Veeresh   | 8080808008   |
      | 9090909011    | Jeevan    | 8080808009   |