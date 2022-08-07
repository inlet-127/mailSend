package dto;

import java.util.Properties;

import constants.AwsMailHost;
import constants.SmtpProperties;

public abstract class BaseMailRequest {
	public final String from;
	public final String fromName;
	public final String to;
	public final String subject;
	public final String body;
	public final String contentType;
	public final Properties smtpProp; 
	public final String smtpUser;
	public final String smtpPassword;
	public final String host;
	
	protected BaseMailRequest(Builder<?> builder){
		this.from = builder.from;
		this.to = builder.to;
		this.subject = builder.subject;
		this.body = builder.body;
		this.contentType = builder.contentType;
		this.smtpProp = builder.smtpProp;
		this.smtpUser = builder.smtpUser;
		this.smtpPassword = builder.smtpPassword;
		this.fromName = builder.fromName;
		this.host = builder.host;
	}
	
	abstract static class Builder<T extends Builder<T>> {
		private String from;
		private String fromName;
		private String to;
		private String subject;
		private String body;
		private String contentType;
		private Properties smtpProp = new Properties(); 
		private String smtpUser;
		private String smtpPassword;
		private String host;
		/**
		 * 送信元アドレスを設定する
		 * @param fromAddress 送信元アドレス
		 * @return
		 */
		public T from(String fromAddress) {
			this.from = fromAddress;
			return self();
		}
		/**
		 * 送信者名を設定する
		 * @param fromAddress 送信元アドレス
		 * @return
		 */
		public T fromName(String fromName) {
			this.fromName = fromName;
			return self();
		}
		/**
		 * 送信先アドレスを設定する
		 * @param to 送信元アドレス
		 * @return
		 */
		public T to(String toAddress) {
			this.to = toAddress;
			return self();
		}
		/**
		 * 件名を設定する
		 * @param body メール本文
		 * @return
		 */
		public T subject(String subject) {
			this.subject = subject;
			return self();
		}
		/**
		 * メール本文
		 * @param body メール本文
		 * @return
		 */
		public T body(String body) {
			this.body = body;
			return self();
		}
		/**
		 * コンテンツタイプ
		 * @return
		 */
		public T contentType(String contentType) {
			this.contentType = contentType;
			return self();
		}
		/**
		 * SMTPプロパティを追加する
		 * @param key
		 * @param value
		 * @return
		 */
		public T addSmtpProp(String key, String value ) {
			this.smtpProp.put(key, value);
			return self();
		}
		/**
		 * SMTPプロパティを追加する
		 * @param key
		 * @param value
		 * @return
		 */
		public T addSmtpProp(SmtpProperties key, String value ) {
			this.addSmtpProp(key.getProp(), value);
			return self();
		}
		/**
		 * SMTPプロパティを追加する
		 * @param key
		 * @param value
		 * @return
		 */
		public T addSmtpProp(SmtpProperties key, int value ) {
			this.smtpProp.put(key.getProp(), value);
			return self();
		}
		/**
		 * SMTPユーザを設定する
		 * @param smtpUser
		 * @return
		 */
		public T smtpUser(String smtpUser) {
			this.smtpUser = smtpUser;
			return self();
		}
		/**
		 * SMTPパスワードを設定する
		 * @param smtpPassword
		 * @return
		 */
		public T smtpPassword(String smtpPassword) {
			this.smtpPassword = smtpPassword;
			return self();
		}
		/**
		 * ホスト名を設定する
		 * @param host
		 * @return
		 */
		public T host(String host) {
			this.host = host;
			return self();
		}
		/**
		 * ホスト名を設定する
		 * @param host
		 * @return
		 */
		public T host(AwsMailHost host) {
			this.host(host.getHost());
			return self();
		}
		abstract BaseMailRequest build();
		
		protected abstract T self();
	}
}
