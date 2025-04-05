package chatppserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJDBC
{
    Connection connection;
    Statement statement;
    ResultSet resultset;

    public MyJDBC(String ip , String port , String file)
    {

        try 
        {
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + file , "root" , "Parham_1");
            statement = connection.createStatement();   
        } 
        catch (SQLException e) 
        {
            System.out.println("\n1\n");
            e.printStackTrace();
        }
    }
    public int query(String query)
    {
        try
        {
            return statement.executeUpdate(query);
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            
            return -1;
        }
    }
    public boolean querySelect(String query)
    {
        try 
        {
            resultset =  statement.executeQuery(query);
            return true;
        } 
        catch (SQLException e) 
        {
            System.out.println("Error executing query");
            e.printStackTrace();
        }
        return false;
        
    }

    public static void main(String[] args) {
        MyJDBC connection = new MyJDBC("127.0.0.1", "3306", "mydb");
    }
    
}