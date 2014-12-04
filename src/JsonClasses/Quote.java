package JsonClasses;

public class Quote implements java.io.Serializable {
	private  final long serialVersionUID = 2L;
	private String overallID = "getQuote";
	private String quote;
	private String author;
	private String topic;

	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getQuote() {
		return quote;
	}
	public String getOverallID() {
		return overallID;
	}
	public void setOverallID(String overallID) {
		this.overallID = overallID;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
}
