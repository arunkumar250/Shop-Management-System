package DBWorks;
import java.sql.*;

public class OrderDB extends DBConnection {
    
	static Connection conn;
    public static void viewOrder(int id)
	{
    	try {
		    conn = make();
			String order = "select *from Orders where Customer_id="+id+";";
			Statement st = conn.createStatement();
			ResultSet set = st.executeQuery(order);
			System.out.println("id   Customer_Name   Product_Name   Price");
			while(set.next())
			{
				System.out.println(set.getString("Customer_id")+"      "+set.getString("Customer_Name")+"            "+set.getString("Product_Name")+"   "+set.getString("Price"));
			}
    	}
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
	}
}
