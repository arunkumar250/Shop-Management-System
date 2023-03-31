package DBWorks;
import java.sql.*;
public class DBConnection
{
	static Connection con = null;
	public static Connection make()
	{
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_Shop","root","root");
//		System.out.println("Connected");
		}
		catch(Exception e)
		{
			System.out.print(e);
		}	
		return con;
	}
}