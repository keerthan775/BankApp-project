package P1;

public class MailProgram 
{	
		static String email="";
		static String password="";
		
		static String getEmail()
		{
			return email;
		}

		public static String getPassword() {
			return password;
		}

		public  void setPassword(String password) {
			this.password = password;
		}

		public static void setEmail(String email) {
			MailProgram.email = email;
		}
		
}
