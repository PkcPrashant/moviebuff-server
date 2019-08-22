import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;
    
    static
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//Prashant-PC:1521/xe","system","manager");
        }
        catch(ClassNotFoundException | SQLException e)
        {
           System.out.println("Exception in database "+e); 
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
    
}
