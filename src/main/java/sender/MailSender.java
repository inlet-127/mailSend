package sender;

import java.io.File;
import java.io.FileInputStream;

import constants.AwsMailHost;
import dto.BaseMailRequest;
import dto.MailAttachement;
import dto.StandardMailRequest;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

public class MailSender {

	public static void main(String[] args) throws Exception {

		StandardMailRequest req = new StandardMailRequest.Builder().standardSmtpSetting().from("************")
				.to("************").subject("use jakarta.mail").body(new MailAttachement().text("test"))
				.contentType("text/html").host(AwsMailHost.TOKYO).smtpUser("************").smtpPassword("************")
				.addAttachment(new MailAttachement().fileName("test1.txt")
						.file(new ByteArrayDataSource(new FileInputStream(new File("D:\\test1.txt")), "text/plain")))
				.addAttachment(new MailAttachement().fileName("test2.txt")
						.file(new ByteArrayDataSource(new FileInputStream(new File("D:\\test2.txt")), "text/plain")))
				.build();
		MailSender.sendEmail(req);
	}

	/**
	 * メール送信
	 * 
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
		msg.setContent(req.multipart);

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
