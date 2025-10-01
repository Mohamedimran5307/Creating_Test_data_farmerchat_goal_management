package pojo;

public class GetAnswerForTextQuery {
	
	private String user_id;
	private String conversation_id;
	private String query;
	private String triggered_input_type;
	
	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getConversation_id() {
		return conversation_id;
	}
	
	public void setConversation_id(String conversation_id) {
		this.conversation_id = conversation_id;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public String getTriggered_input_type() {
		return triggered_input_type;
	}
	
	public void setTriggered_input_type(String triggered_input_type) {
		this.triggered_input_type = triggered_input_type;
	}
	
	@Override
	public String toString() {
		return "GetAnswerForTextQuery{" +
				"user_id='" + user_id + '\'' +
				", conversation_id='" + conversation_id + '\'' +
				", query='" + query + '\'' +
				", triggered_input_type='" + triggered_input_type + '\'' +
				'}';
	}
}
