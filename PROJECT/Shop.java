package PROJECT;
import DBWorks.*;
import java.util.*;
import java.sql.*;

public class Shop extends ShopDB
{
	static Scanner in;
	static Connection con = null;
	static DB take = new DB();
	static ShopDB shopdb;
	
	public static void main(String[] args)
	{
		System.out.println("Shop");
	}
	
	
	public static void view()
	{
		String s = "SELECT *FROM Products";
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online_Shop","root","root");
			Statement st = con.createStatement();
			ResultSet set = st.executeQuery(s);
			while(set.next())
			{
				System.out.println(set.getString(0));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public void manage()
	{
        in = new Scanner(System.in);
		int key;
		do {
		System.out.println("1 Add");
		System.out.println("2 Delete");
		System.out.println("3 Close");
		key = in.nextInt();
		if(key==1)
		{
			add();
		}
		else if(key==2)
		{
			delete();
		}
		else if(key==3)
		{
			System.out.println("Done");
		}
		}while(key!=3);
	}
	public static void add()
	{
		in = new Scanner(System.in);
		String cat,Name;
		int id,price;
		System.out.println("Enter the Id :");
		id = in.nextInt();
		in.nextLine();
		System.out.println("Enter Category");
		cat = in.nextLine();
		System.out.println("Enter Name of the Product :");
		Name = in.nextLine();
		System.out.println("Enter the Price :");
		price = in.nextInt();
		System.out.println("Entry Products Count :");
		int count = in.nextInt();
		productinsert(id,cat,Name,price,count);
	}
	
	
	public static void delete()
	{
		in = new Scanner(System.in);
		int id;
		System.out.println("Enter the Id :");
		id = in.nextInt();
		productdelete(id);
	}
	
	
	public void viewProduct()
	{
		try
		{
			viewProducts();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public boolean place(int id,int cusId,int count)
	{
		return placeProduct(id,cusId,count);
	}
	
	public void makebill(int id)
	{
		makeBill(id);
	}
	
//	public static void buy(int id,count) {
//	    Scanner in = new Scanner(System.in);
//        System.out.println("Enter the Product id :");
//        int product = in.nextInt();
//        shop.placeProduct(product,id,count);
//        System.out.println("Press 1 to Confirm");
//        int one = in.nextInt();
//        if(one==1) {
//        	System.out.println("Order Placed Successfully!\n\n");
//            bill(product);
//        }
//        else
//        	System.out.println("Cool!");
//	}
}