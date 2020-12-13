package P1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String custid=request.getParameter("custid");
		String pwd=request.getParameter("pwd");
		Model m;
		try 
		{
			m = new Model();
			m.setCustid(custid);
			m.setPwd(pwd);
			boolean status=m.login();
			if(status==true)
			{
				String accno = m.getAccno();
				HttpSession session=request.getSession(true);
				session.setAttribute("accno", accno);
				response.sendRedirect("/BankApp/LoginSuccess.jsp");
			}
			else
			{
				response.sendRedirect("/BankApp/LoginFailure.jsp");	
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}       
}