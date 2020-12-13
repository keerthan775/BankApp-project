package P1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Transfer extends HttpServlet 
{
	private String amt;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		amt=request.getParameter("tamt");
		HttpSession session=request.getSession();
		String accno=(String) session.getAttribute("accno");
		try 
		{
			Model m=new Model();
			m.setAmt(amt);
			m.setAccno(accno);
			boolean status=m.transfer();
			if(status==true)
			{
				response.sendRedirect("/BankApp/TransferSuccess.jsp");
			}
			else
			{
				response.sendRedirect("/BankApp/TransferFail.jsp");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
