package DBWorks;

import java.sql.Connection;

import PROJECT.DBTablePrinter;

public class AdminDB extends DBConnection{
static Connection connection;
	
	public static void viewCus()
	{
		connection = make();
		DBTablePrinter.printTable(connection,"Admin");
	}
}
