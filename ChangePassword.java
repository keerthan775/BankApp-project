package P1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePassword extends HttpServlet
{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String npwd = request.getParameter("npwd");
		HttpSession session = request.getSession();
		String accno = (String) session.getAttribute("accno");
		try
		{
			Model m=new Model();
			m.setAccno(accno);
			m.setPwd(npwd);
			boolean status=m.changePassword();
			if(status==true)
			{
				response.sendRedirect("/BankApp/ChangePasswordSuccess.jsp");
			}
			else
			{
				response.sendRedirect("/BankApp/ChangePasswordFail.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
}
