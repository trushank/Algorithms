import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
* 
* post.java
* @author Trushank
* Date Apr 10, 2013
* Version 1.0
* 
 * 
 */
/**
 * @author Trushank
 *
 */
public class post {

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
	try {
	    Class.forName("org.postgresql.Driver");
	
	Connection connection = null;
	connection = DriverManager.getConnection(
	   "jdbc:postgresql://reddwarf.cs.rit.edu:5432/p77101b","p77101b", "FACEbook123");
	Statement s=connection.createStatement();
	//System.out.print(s.execute("CREATE TABLE cars(id INTEGER PRIMARY KEY, mame VARCHAR(25), price INT)"));
	//System.out.print(s.executeUpdate("INSERT INTO cars VALUES(1,'Audi',52642)"));
	ResultSet rs=s.executeQuery("Select * from cars");
	rs.next();
	System.out.println(rs.getString(2));
	connection.close();
    } catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
    } catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
    }	}

}

