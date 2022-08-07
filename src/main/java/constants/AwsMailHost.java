package constants;

public enum AwsMailHost {
	
	TOKYO("email-smtp.ap-northeast-1.amazonaws.com"),;
	
	AwsMailHost(String host){
		this.host = host;
	}
	
	final String host;

	public String getHost() {
		return host;
	}

}
