package dto;

import constants.SmtpProperties;

public class StandardMailRequest extends BaseMailRequest {
	
	private StandardMailRequest(Builder builder) {
		super(builder);
	}
	public static class Builder extends BaseMailRequest.Builder<Builder> {

		@Override
		public StandardMailRequest build() {
			// TODO 自動生成されたメソッド・スタブ
			return new StandardMailRequest(this);
		}

		@Override
		protected Builder self() {
			return this;
		}
		
		/**
		 * SMTPの標準設定を適用する
		 */
		public Builder standardSmtpSetting() {
			Builder builder = self();
			builder.addSmtpProp(SmtpProperties.Protocol, "smtp");
			builder.addSmtpProp(SmtpProperties.Port, 587);
			builder.addSmtpProp(SmtpProperties.Starttls, "true");
			builder.addSmtpProp(SmtpProperties.Auth, "true");
			return self();
		}
		
	}
}
