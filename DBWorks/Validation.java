package DBWorks;
import java.util.regex.*;
import java.sql.*;

public class Validation extends DBConnection{
    static Connection connection = make();
    private static String pass = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
	public static boolean check(int i,int p,int choice)
	{
		String s;
		if(choice == 3)
			 s = "SELECT Name from Admin WHERE Id ="+i+" AND Password = "+p;
		else
			 s = "SELECT Name from Customer WHERE cus_Id ="+i+" AND Password = "+p;
		try {
			Statement st = connection.createStatement();
			ResultSet ch = st.executeQuery(s);
			if(ch.next())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return false;
	}
    
    public static boolean validate(String password) {
    	Pattern pattern = Pattern.compile(pass);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
