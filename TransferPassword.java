package P1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class TransferPassword extends HttpServlet
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String enp=request.getParameter("enp");
		HttpSession session = request.getSession();
		String email=(String) session.getAttribute("email");
		try 
		{
			Model m=new Model();
			m.setPwd(enp);
			m.setEmail(email);
			boolean status=m.forgotPwd();
			if(status==true)
			{
				response.sendRedirect("BankApp/PasswordChangeSuccess.jsp");
			}
			else
			{
				response.sendRedirect("BankApp/PasswordChangeFail.jsp");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
