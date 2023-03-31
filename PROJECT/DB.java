package PROJECT;
import java.sql.*;
public class DB extends Shop {
	static DB insert = new DB();
	static ConnectionEstablishment con = new ConnectionEstablishment();
	Shop shop = new Shop();
	public static void productdelete(int id)
	{
		String s = "delete from Products where id="+id+";";
		try
		{
			Connection conn = make();
			Statement st = conn.createStatement();
		    int  ch = st.executeUpdate(s);
			if(ch!=0)
			{
				System.out.println("Deleted Successfully");
			}
			else
			{
				System.out.println("Not Deleted");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void viewProducts()
	{
		insert.viewProduct();
	}
}
