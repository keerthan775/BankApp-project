package P1;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetStatement extends HttpServlet 
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String accno=(String) session.getAttribute("accno");
		try
		{
			Model m=new Model();
			m.setAccno(accno);
			ArrayList al=m.getStatement();
			if(al.isEmpty()==true)
			{	
				response.sendRedirect("/BankApp/StatementFail.jsp");
			}
			else
			{
				session.setAttribute("al", al);
				response.sendRedirect("/BankApp/StatementSuccess.jsp");
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
}