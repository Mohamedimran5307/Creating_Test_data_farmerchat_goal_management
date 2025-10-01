package pojo;

public class GenerateOtpRequest {
	
	private String phone;
	private String phone_country_code;
	private String otp;
	private String device_id;
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone_country_code() {
		return phone_country_code;
	}
	
	public void setPhone_country_code(String phone_country_code) {
		this.phone_country_code = phone_country_code;
	}
	
	public String getOtp() {
		return otp;
	}
	
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	public String getDevice_id() {
		return device_id;
	}
	
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	
	@Override
	public String toString() {
		return "GenerateOtpRequest{" +
				"phone='" + phone + '\'' +
				", phone_country_code='" + phone_country_code + '\'' +
				", otp='" + otp + '\'' +
				", device_id='" + device_id + '\'' +
				'}';
	}
}

