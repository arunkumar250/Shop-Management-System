package DBWorks;
import PROJECT.DBTablePrinter;
import java.util.Date;
import java.sql.*;
public class ShopDB extends DBConnection{
	
	static Connection conn  = make();
	static Statement st;
    public static void main(String[] args)
    {
    	System.out.println("ShopDB");
    }
    
    public static void viewProducts()
	{
		conn = make();
		DBTablePrinter.printTable(conn,"Products");
	}
    
    public boolean placeProduct(int id,int cus_id,int count)
    {
    	try
		{
    		st = conn.createStatement();
    		String s4 = "select product_available from Products where id ="+id+";";
    		String str = "";
    		ResultSet set2 = st.executeQuery(s4);
    		while(set2.next()) str = set2.getString("Product_Available");
    		int vsl = Integer.parseInt(str);
    	if(count<=vsl)
    	{
		    int id1 = 0,Price = 0;
		    String Name = "",n = "";
			conn = make();
			String s = "select id,Name,Price from Products where id = "+id+";";
			ResultSet set = st.executeQuery(s);
			while(set.next())
			{
				System.out.println("Product Details");
				id1 = Integer.parseInt(set.getString("id"));
				System.out.println("id :"+id1);
				Name = set.getString("Name");
				System.out.println("Name :"+Name);
				Price = Integer.parseInt(set.getString("Price"));
				System.out.println("Price :"+Price);
			}
			String s2 = "select Name from customer where cus_id = "+cus_id;
			ResultSet set1 = st.executeQuery(s2);
			while(set1.next()) n = set1.getString("Name");
			System.out.println("Customer id :"+id+"\nCustomer Name :"+n);
			String s1 = "insert into Orders values("+cus_id+",'"+n+"','"+Name+"',"+Price+");";
			st.execute(s1);
			 String s3 = "update Products set product_available = "+ (vsl-count)+" where id = "+id+";";
		     st.execute(s3);
			 System.out.println("Added in Orders");
			 return true;
		}
		else
			{
			System.out.println("Out of Stock");
			return false;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
    	return true;
    }
 
    
    public void makeBill(int id)
    {
    	Date d = new Date();
		try
		{
			conn = make();
			String s = "select id,Name,Price from Products where id = "+id+";";
			System.out.println(s);
			Statement st = conn.createStatement();
			ResultSet set = st.executeQuery(s);
			System.out.println("Take Your Bill");
			while(set.next()) {
			System.out.println("Product ID :"+set.getString("id"));
			System.out.println("Product Name :"+set.getString("Name"));
			System.out.println("Price :"+set.getString("Price"));
			}
			System.out.println("Date :"+d.toString()+"\n\n");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
    }
    
    //operations

	public static void productinsert(int id,String cat,String name,int price,int count)
	{
		String s = "insert into Products values("+id+",'"+cat+"','"+name+"',"+price+","+count+");";
		try
		{
			Connection conn = make();
			Statement st = conn.createStatement();
		    int  ch = st.executeUpdate(s);
			if(ch!=0)
			{
				System.out.println("Added Successfully");
			}
			else
			{
				System.out.println("Not Added");
			}
		}
		catch(Exception e)
		{
			System.out.println("Already the item Exists!");
		}
	}
	
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
}
