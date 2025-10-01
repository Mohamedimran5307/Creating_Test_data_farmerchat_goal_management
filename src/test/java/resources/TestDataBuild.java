package resources;

import java.util.UUID;

import pojo.GenerateOtpRequest;
import pojo.VerifyOtpRequest;
import pojo.UpdateUserProfile;
import pojo.UpdateUserLocation;
import pojo.VerifyReferralCode;
import pojo.CreateNewConversation;
import pojo.GetAnswerForTextQuery;

public class TestDataBuild {
	
	// Phone number range for dynamic generation
	private static final long START_PHONE = 9393932218L;
	private static final long END_PHONE = 9393932240L;
	private static long currentPhone = START_PHONE;
	
	/**
	 * Get next phone number in sequence
	 */
	public static String getNextPhoneNumber() {
		if (currentPhone > END_PHONE) {
			currentPhone = START_PHONE; // Reset to start
		}
		return String.valueOf(currentPhone++);
	}
	
	/**
	 * Get current phone number
	 */
	public static String getCurrentPhoneNumber() {
		return String.valueOf(currentPhone);
	}
	
	/**
	 * Generate dynamic device ID using UUID
	 */
	public String generateDynamicDeviceId() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * Generate timestamp-based device ID
	 */
	public String generateTimestampBasedDeviceId() {
		return "device_" + System.currentTimeMillis();
	}
	
	
	public GenerateOtpRequest Generate_OTP_Payload(String phoneNumber) {
		GenerateOtpRequest generateOtpRequest = new GenerateOtpRequest();
		generateOtpRequest.setPhone(phoneNumber);
		generateOtpRequest.setPhone_country_code("+91");
		generateOtpRequest.setDevice_id(generateDynamicDeviceId());
		return generateOtpRequest;
	}
	
	public VerifyOtpRequest Generate_Verify_Payload(String phoneNumber) {
		VerifyOtpRequest verifyOtpRequest = new VerifyOtpRequest();
		verifyOtpRequest.setPhone(phoneNumber);
		verifyOtpRequest.setPhone_country_code("+91");
		verifyOtpRequest.setOtp("1111");
		return verifyOtpRequest;
	}
	
	public UpdateUserProfile Generate_Update_User_Profile_Payload(String user_id, String userName) {
		UpdateUserProfile updateUserProfile = new UpdateUserProfile();
		updateUserProfile.setName(userName);
		updateUserProfile.setAge(null);
		updateUserProfile.setGender(null);
		// updateUserProfile.setRole("role_selection_all_practicing_farmer");
		updateUserProfile.setRole("role_selection_all_extension_worker");
		updateUserProfile.setLand_holding(null);
		updateUserProfile.setLive_stock_details(null);
		updateUserProfile.setUser_id(user_id);
		updateUserProfile.setSpecialization(null);
		updateUserProfile.setFarmer_reach_count(null);
		updateUserProfile.setProfile_picture("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
		updateUserProfile.setFcm_token(null);
		updateUserProfile.setReceive_com_via_whatsapp(false);
		updateUserProfile.setUpi_id("example.xbl");
		return updateUserProfile;
	}
	
	public UpdateUserLocation Generate_Update_User_Location_Payload(String user_id) {
		// Try using the exact coordinates from the working example first
		String lat = "13.165758141882232";
		String longitude = "75.86650729179382";
		// Use the original constructor order: user_id, lat, longitude
		UpdateUserLocation updateUserLocation = new UpdateUserLocation(user_id, lat, longitude);
		System.out.println("Using fixed coordinates for testing: lat=" + lat + ", long=" + longitude);
		return updateUserLocation;
	}
	
	public VerifyReferralCode Generate_Verify_Referral_Code_Payload(String user_id, String code) {
		VerifyReferralCode verifyReferralCode = new VerifyReferralCode();
		verifyReferralCode.setUser_id(user_id);
		verifyReferralCode.setCode(code);
		return verifyReferralCode;
	}
	
	/**
	 * Generate dynamic latitude within Indian geographical bounds
	 */
	public String generateDynamicLatitude() {
		// India latitude range: approximately 6.5 to 37.1
		double minLat = 6.5;
		double maxLat = 37.1;
		double lat = minLat + (Math.random() * (maxLat - minLat));
		return String.format("%.12f", lat);
	}
	
	/**
	 * Generate dynamic longitude within Indian geographical bounds
	 */
	public String generateDynamicLongitude() {
		// India longitude range: approximately 68.1 to 97.4
		double minLng = 68.1;
		double maxLng = 97.4;
		double lng = minLng + (Math.random() * (maxLng - minLng));
		return String.format("%.12f", lng);
	}
	
	public CreateNewConversation Generate_Create_New_Conversation_Payload(String user_id) {
		CreateNewConversation createNewConversation = new CreateNewConversation();
		createNewConversation.setUser_id(user_id);
		return createNewConversation;
	}
	
	public GetAnswerForTextQuery Generate_Get_Answer_For_Text_Query_Payload(String user_id, String conversation_id, String query) {
		GetAnswerForTextQuery getAnswerForTextQuery = new GetAnswerForTextQuery();
		getAnswerForTextQuery.setUser_id(user_id);
		getAnswerForTextQuery.setConversation_id(conversation_id);
		getAnswerForTextQuery.setQuery(query);
		getAnswerForTextQuery.setTriggered_input_type("text");
		return getAnswerForTextQuery;
	}
}

