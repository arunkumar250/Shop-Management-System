package PROJECT;
import java.util.*;
import java.util.regex.*;
import DBWorks.*;
import java.sql.*;
public class Main{

	private static Connection con = null;
	private static Shop shop = new Shop();
	private static Order_Details order = new Order_Details();
	private static Customer customer = new Customer();
	private static Admin admin = new Admin();
	private static Scanner in;
	private static int choice;
	
	public static void main(String args[])
	{
		in=new Scanner(System.in);
		do
		{
			System.out.println(" ---------------------------------------------------------------------------");
			System.out.println("|                 ----Shop Management System----                       |");
			System.out.println("|                 ----       Login Page         ----                       |");
			System.out.println(" ---------------------------------------------------------------------------\n");
			System.out.println("		[1]  Register Admin");
			System.out.println("		[2]  Register Customer");
			System.out.println("		[3]  Login Admin ");
			System.out.println("		[4]  Login Customer");
			System.out.println("		[5]  Close\n");
			System.out.print("Press the Option : ");
			choice = in.nextInt();
			if(choice == 1)
				regisAdmin();
			else if(choice==2)
				regisCustomer();
			else if(choice==3)
				loginadmin();
			else if(choice==4)
				loginCustomer();
			else if(choice==5)
				System.out.println("Done!");
			else
				System.out.println("Press the Correct Option");
		}while(choice!=5);
	}
	public static void regisAdmin()
	{
		in = new Scanner(System.in);
		String name,email,pass;
		int check = 0;
		int id,num,age;
		System.out.println(" -----------------------------------");
	    System.out.println("|           Admin Registration      |");
		System.out.println(" -----------------------------------");
		System.out.print("Enter Name = ");
		name=in.nextLine();
		System.out.println("Admin ID= ");
		id = in.nextInt();
		in.nextLine();
		System.out.print("Enter password= ");
		pass=in.nextLine();
		System.out.print("Enter contact number = ");
		num=in.nextInt();
		in.nextLine();
		System.out.print("Enter email= ");
		email=in.nextLine();
		if(Validation.validate(pass)) insert(name,id,pass,num,email,check);
		else 
			{
			System.out.println("Your PassWord is Weak!\n Try Again!\n");
			regisAdmin();
			}
	}
	public static void regisCustomer()
	{
		System.out.println(" ---------------------------------------");
	    System.out.println("|           Customer Registration       |");
		System.out.println(" ---------------------------------------");
		in = new Scanner(System.in);
		String name,email,pass;
		int check = 1;
		int id,num;
		System.out.print("Enter Name = ");
		name=in.nextLine();
		System.out.println("Customer ID= ");
		id = in.nextInt();
		in.nextLine();
		System.out.print("Enter password= ");
		pass=in.nextLine();
		System.out.print("Enter contact number = ");
		num=in.nextInt();
		in.nextLine();
		System.out.print("Enter email= ");
		email=in.nextLine();
		if(Validation.validate(pass)) insert(name,id,pass,num,email,check);
		else 
			{
			System.out.println("Your PassWord is Weak!\n Try Again!\n");
			regisCustomer();
			}
	}
	
	//loginAdmin
	public static void loginadmin()
	{
		in = new Scanner(System.in);
		int id,pass;
		System.out.println("Enter id : ");
		id = in.nextInt();
		System.out.println("Enter pass: ");
		pass = in.nextInt();
		if(Validation.check(id,pass,choice))
		{
			in = new Scanner(System.in);
			System.out.println(" -----------------------------------");
		    System.out.println("|           Admin page              |");
			System.out.println(" -----------------------------------");
			int key = 0;
			do
			{
				System.out.println("	[1] View Products");
				System.out.println("	[2] Manage products");
				System.out.println("	[3] View Customers");
				System.out.println("	[5] Close");
				key = in.nextInt();
				if(key==1)
					shop.viewProduct();
				else if(key==2)
					shop.manage();
				else if(key==3)
				{
					customer.viewCustomers();
				}
				else if(key==4)
				{
					order.viewOrders(id);
				}
				else if(key==5)
					System.out.println("Done");
			}while(key!=5);
		}
		else
		{
			System.out.println("enter correct details");
		}
	}

	public static void loginCustomer()
	{
		in = new Scanner(System.in);
		while(true)
		{
			int id,pass;
			System.out.println("Enter id : ");
			id = in.nextInt();
			System.out.println("Enter pass: ");
			pass = in.nextInt();
			if(Validation.check(id,pass,choice))
			{
				System.out.println("Welcome to the Shopping System");
				int choice;
				do
				{
					System.out.println(" ---------------------------------------");
				    System.out.println("|           Customer page               |");
					System.out.println(" ---------------------------------------");
					System.out.println("	[1] Do you want to See Products");
					System.out.println("	[2] Do you want to See the Orders");
					System.out.println("	[3] Do you want to See the Cart");
					System.out.println("	[4] Close the Page");
					choice = in.nextInt();
					switch(choice)
					{
						case 1:
							shop.viewProduct();
						    System.out.println("Do you wish to Buy any Item [Y/N]");
						    char buy = in.next().charAt(0);
						    if(buy=='Y' ||  buy=='y')
						    {
						    	System.out.println("Enter the Product id :");
						        int product = in.nextInt();
						        System.out.println("Enter Product Quantity :");
						        int count = in.nextInt();
						        if(shop.place(product,id,count)) {
							        System.out.println("Press 1 to Confirm");
							        int one = in.nextInt();
							        if(one==1) {
							        	System.out.println("Order Placed Successfully!\n\n");
							            shop.makebill(product);
							        }
							        else
							        	System.out.println("Cool!");
						        }
						        else
						        {
						        	System.out.println("See Another Products");
						        }
						    }
						    else
						    	continue;
						    
						    break;
						case 2:
							try{
								order.viewOrders(id);
							}
							catch(Exception e)
							{
								System.out.println(e);
							}
							break;
					}
				}while(choice!=4);
				break;
			}
			else
			{
				System.out.println("Enter correct details");
			}
		}
	}
	
	public static void insert(String name,int id,String pass,int num,String email,int check)
	{
		String s = "";
		if(check==0)
		{
		     s = "insert into Admin values("+"\'"+name+"\'"+","+id+","+pass+","+num+",'"+email+"\'"+")";
		}
		else
		{
			 s = "insert into Customer values("+"\'"+name+"\'"+","+id+","+pass+","+num+",'"+email+"\'"+")";
		}
		try
		{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online_Shop","root","root");
			Statement st = con.createStatement();
			int i = st.executeUpdate(s);
			
			if(i!=0)
			{
				System.out.println("Inserted");
			}
			else
			{
				System.out.println("Not inserted");
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
