package pojo;

public class VerifyOtpRequest {
	
	private String phone;
	private String phone_country_code;
	private String otp;
	
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
	
	@Override
	public String toString() {
		return "VerifyOtpRequest{" +
				"phone='" + phone + '\'' +
				", phone_country_code='" + phone_country_code + '\'' +
				", otp='" + otp + '\'' +
				'}';
	}
}

