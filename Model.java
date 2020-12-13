package P1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

public class Model 
{ 
	private String name;
	private String custid;
	private String accno;
	private String pwd;
	private String amt;
	private String email;
	private Connection con;
	private PreparedStatement pstmt ;
	private ResultSet res;
	private int rows;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amount) {
		this.amt = amount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Model() throws Exception
	{
	DriverManager.registerDriver(new OracleDriver());
	con=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "system","system");
	}

	public boolean login() 
	{
	try 
	{
		pstmt = con.prepareStatement("SELECT * FROM BANK WHERE CUSTID=? AND PWD=?");
		pstmt.setString(1, custid);
		pstmt.setString(2, pwd);
		res = pstmt.executeQuery();
	  if(res.next()==true)
	  {
		accno=res.getString("accno");
		return true;
	  }
	} 
	catch (SQLException e) 
	{		
		e.printStackTrace();
	}
	return false;
	}
	
	public boolean checkbalance() throws Exception
	{
		pstmt=con.prepareStatement("SELECT AMT FROM BANK WHERE ACCNO=?");
		pstmt.setString(1, accno);
		res=pstmt.executeQuery();
		if(res.next()==true)
		{
			amt=res.getString(1);
		}
		if(amt==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public boolean changePassword() throws Exception
	{
		pstmt=con.prepareStatement("UPDATE BANK SET PWD=? WHERE ACCNO=?");
		pstmt.setString(1, pwd);
		pstmt.setString(2, accno);
		rows=pstmt.executeUpdate();
		if(rows==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public boolean transfer() throws Exception
	{
		pstmt=con.prepareStatement("UPDATE BANK SET AMT=AMT-? WHERE ACCNO=?");
		pstmt.setString(1, amt);
		pstmt.setString(2, accno);
		rows=pstmt.executeUpdate();
		
		pstmt=con.prepareStatement("INSERT INTO BANKSTATEMENT VALUES(?,?)");
		pstmt.setString(1, accno);
		pstmt.setString(2, amt);
		pstmt.executeUpdate();
		if(rows==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public ArrayList getStatement()
	{
		ArrayList al=new ArrayList();
		try
		{
		pstmt=con.prepareStatement("SELECT * FROM BANKSTATEMENT WHERE ACCNO=?");
		pstmt.setString(1, accno);
		res=pstmt.executeQuery();
		while(res.next()==true)
		{
			String temp=res.getString(2);
			al.add(temp);
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return al;
	}
	public boolean forgotPwd()
	{
		try
		{
		pstmt=con.prepareStatement("UPDATE BANK SET PWD=? WHERE EMAIL=?");
		pstmt.setString(1, pwd);
		pstmt.setString(1, email);
		rows=pstmt.executeUpdate();
		if(rows==0)
		{
			return false;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}
}
