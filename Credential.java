package P1;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Credential extends HttpServlet
{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email=request.getParameter("email");
		HttpSession session1 = request.getSession(true);
		session1.setAttribute("email", email);

		String fromEmail=; //sender's mail id.
		String pwd=;		//sender's mail pwd.

		String toEmail="nareshpalem07@gmail.com";  //reciever's mail id.
		
		//Creating Session Object
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(fromEmail, pwd);
			}
		});

		//Composing the Mail
		MimeMessage mesg = new MimeMessage(session);
		try 
		{
			mesg.setFrom(new InternetAddress(fromEmail));
			mesg.setRecipient(Message.RecipientType.TO,new InternetAddress(toEmail));
			mesg.setSubject("BankApp Project");  
			mesg.setText("http://localhost:9090/BankApp/ChangeToNewPassword.jsp");
		}
		catch (MessagingException e1) 
		{
			e1.printStackTrace();
		}
		//Sending the Mail
		try 
		{
			Transport.send(mesg);
			System.out.println("Mail Sent!!");
		} 
		catch (MessagingException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			Model m=new Model();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
