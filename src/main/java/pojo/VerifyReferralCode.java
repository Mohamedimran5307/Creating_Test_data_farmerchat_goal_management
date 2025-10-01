package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VerifyReferralCode {
    
    @JsonProperty("user_id")
    private String user_id;
    
    @JsonProperty("code")
    private String code;
    
    // Default constructor
    public VerifyReferralCode() {
    }
    
    // Constructor with parameters
    public VerifyReferralCode(String user_id, String code) {
        this.user_id = user_id;
        this.code = code;
    }
    
    // Getter and Setter for user_id
    public String getUser_id() {
        return user_id;
    }
    
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    // Getter and Setter for code
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return "VerifyReferralCode{" +
                "user_id='" + user_id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
