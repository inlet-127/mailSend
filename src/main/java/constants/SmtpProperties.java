package constants;

public enum SmtpProperties {
	
	Protocol("mail.transport.protocol"), Port("mail.smtp.port"), Starttls("mail.smtp.starttls.enable"), Auth("mail.smtp.auth"),;
	
	SmtpProperties(String prop){
		this.prop = prop;
	}
	
	private final String prop;

	public String getProp() {
		return prop;
	}
	
}
