package PROJECT;
import DBWorks.*;
public class Order_Details extends OrderDB{

	public void viewOrders(int id)
	{
		try
		{
			viewOrder(id);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
