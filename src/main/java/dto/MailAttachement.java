package dto;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeBodyPart;

public class MailAttachement {
	final MimeBodyPart part = new MimeBodyPart();
	
	public MailAttachement text(String text) {
		try {
			part.setText(text, "ISO-2022-JP");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return this;
	}
	
	public MailAttachement fileName(String fileName) {
		try {
			part.setFileName(fileName);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return this;
	}
	
	public MailAttachement file(DataSource ds) {
		try {
			part.setDataHandler(new DataHandler(ds));
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return this;
	}

}
