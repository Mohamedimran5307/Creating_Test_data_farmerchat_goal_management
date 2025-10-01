package resources;

public enum APIResources {
	
	Generate_OTP("/api/user/generate_otp/"),
	Verify_OTP("/api/user/verify_otp/"),
	Update_User_Profile("/api/user/update_user_profile/"),
	Verify_Referral_Code("/api/user/verify_referral_code/"),
	Update_user_location("/api/user/update_user_location/"),
	Create_new_Conversation("/api/chat/new_conversation/"),
	Get_Answer_for_Text_Query("/api/chat/get_answer_for_text_query/");
	
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}

