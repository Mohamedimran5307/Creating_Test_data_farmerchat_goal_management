package pojo;

public class UpdateUserProfile {
	
	private String name;
	private String age;
	private String gender;
	private String role;
	private String land_holding;
	private String live_stock_details;
	private String user_id;
	private String specialization;
	private String farmer_reach_count;
	private String profile_picture;
	private String fcm_token;
	private boolean receive_com_via_whatsapp;
	private String upi_id;
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getLand_holding() {
		return land_holding;
	}
	
	public void setLand_holding(String land_holding) {
		this.land_holding = land_holding;
	}
	
	public String getLive_stock_details() {
		return live_stock_details;
	}
	
	public void setLive_stock_details(String live_stock_details) {
		this.live_stock_details = live_stock_details;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getSpecialization() {
		return specialization;
	}
	
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public String getFarmer_reach_count() {
		return farmer_reach_count;
	}
	
	public void setFarmer_reach_count(String farmer_reach_count) {
		this.farmer_reach_count = farmer_reach_count;
	}
	
	public String getProfile_picture() {
		return profile_picture;
	}
	
	public void setProfile_picture(String profile_picture) {
		this.profile_picture = profile_picture;
	}
	
	public String getFcm_token() {
		return fcm_token;
	}
	
	public void setFcm_token(String fcm_token) {
		this.fcm_token = fcm_token;
	}
	
	public boolean isReceive_com_via_whatsapp() {
		return receive_com_via_whatsapp;
	}
	
	public void setReceive_com_via_whatsapp(boolean receive_com_via_whatsapp) {
		this.receive_com_via_whatsapp = receive_com_via_whatsapp;
	}
	
	public String getUpi_id() {
		return upi_id;
	}
	
	public void setUpi_id(String upi_id) {
		this.upi_id = upi_id;
	}
	
	@Override
	public String toString() {
		return "UpdateUserProfile{" +
				"name='" + name + '\'' +
				", age='" + age + '\'' +
				", gender='" + gender + '\'' +
				", role='" + role + '\'' +
				", land_holding='" + land_holding + '\'' +
				", live_stock_details='" + live_stock_details + '\'' +
				", user_id='" + user_id + '\'' +
				", specialization='" + specialization + '\'' +
				", farmer_reach_count='" + farmer_reach_count + '\'' +
				", profile_picture='" + profile_picture + '\'' +
				", fcm_token='" + fcm_token + '\'' +
				", receive_com_via_whatsapp=" + receive_com_via_whatsapp +
				", upi_id='" + upi_id + '\'' +
				'}';
	}
}

