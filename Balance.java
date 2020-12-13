package P1;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Balance extends HttpServlet
{	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session=request.getSession();
		String accno=(String) session.getAttribute("accno");
		Model m;
		try 
		{
			m = new Model();
			m.setAccno(accno);
			boolean status=m.checkbalance();
			if(status==true)
			{
				session.setAttribute("amt",m.getAmt());
				response.sendRedirect("/BankApp/PrintBalance.jsp");
			}
			else 
			{
				response.sendRedirect("/BankApp/SessionExpired.jsp");
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
