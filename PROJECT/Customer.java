package PROJECT;
import java.util.*;
import java.util.Date;
import DBWorks.*;
import java.sql.*;
public class Customer extends CustomerDB
{
	static Shop shop = new Shop();
	public static void main(String[] args)
	{
		Scanner in=new Scanner(System.in);
		System.out.println("Customer Page");
	}
	
	public static void bill(int id)
	{
		Date d = new Date();
		try
		{
			ConnectionEstablishment con = new ConnectionEstablishment() ;
			Connection conn = ConnectionEstablishment.make();
			String s = "select id,Name,Price from Products where id = "+id+";";
			Statement st = conn.createStatement();
			ResultSet set = st.executeQuery(s);
			System.out.println("Take Your Bill");
			while(set.next()) {
			System.out.println("Product ID :"+set.getString("id"));
			System.out.println("Product Name :"+set.getString("Name"));
			System.out.println("Price :"+set.getString("Price"));
			}
			System.out.println("Date :"+d.toString()+"\n\n\n");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void viewCustomers()
	{
		viewCus();
	}
}