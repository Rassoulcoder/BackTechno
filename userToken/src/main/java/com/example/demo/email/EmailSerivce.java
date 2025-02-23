package com.example.demo.email;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
@Service
public class EmailSerivce implements EmailSender {
	@Autowired
	private final JavaMailSender mailSender = null;
	@Override
	public void sendEmail(String to, String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper =
					new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("mouhamedder98@gmail.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new IllegalStateException("failed to send email");
		}
	} 

}


