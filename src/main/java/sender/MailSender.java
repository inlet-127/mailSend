package sender;

import constants.AwsMailHost;
import dto.BaseMailRequest;
import dto.StandardMailRequest;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailSender {

	public static void main(String[] args) throws Exception {

		StandardMailRequest req = new StandardMailRequest.Builder().standardSmtpSetting()
				.from("from").to("to")
				.subject("Amazon SES test (SMTP interface accessed using Java)").body("<h1>TEST</h1>")
				.contentType("text/html").host(AwsMailHost.TOKYO)
				.smtpUser("smtp-user").smtpPassword("smtp-password").build();

		MailSender.sendEmail(req);
	}

	/**
	 * メール送信
	 * @param req
	 * @throws Exception
	 */
	public static void sendEmail(BaseMailRequest req) throws Exception {

		Session session = Session.getDefaultInstance(req.smtpProp);

		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(req.from, req.fromName));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(req.to));
		msg.setSubject(req.subject);
		msg.setContent(req.body, req.contentType);

		try (Transport transport = session.getTransport()) {
			System.out.println("Sending...");

			transport.connect(req.host, req.smtpUser, req.smtpPassword);
			transport.sendMessage(msg, msg.getAllRecipients());

			System.out.println("sucesess!!");
		} catch (Exception ex) {
			System.out.println("failed!!");
			System.out.println("Error message: " + ex.getMessage());
		}

	}
}
