package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLInjection {

	private final static String DBPATH = "jdbc:sqlite::resource:injected.db";
	
	private Connection connect() throws ClassNotFoundException {
	       
        Connection conn = null;
        try {
			Class.forName("org.sqlite.JDBC");
        	conn = DriverManager.getConnection(DBPATH);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
	
	
	public String doBadThings(String request) throws ClassNotFoundException, SQLException{
		Connection connection = this.connect();
		String sql = "Select * from test where id = "+ request;
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		String result="";
		while(rs.next()){
			result += rs.getString("name")+"; ";
			result += rs.getDouble("salar")+"; ";
			result += rs.getString("password")+"; \n";
		}
		rs.close();
		stmt.close();
		connection.close();
		return result;
	}
	
	public int doBadThings2(String request,String description) throws ClassNotFoundException, SQLException{
		Connection connection = this.connect();
		String sql = "UPDATE test set description = '"+description+"' where id = "+ request;
		Statement stmt = connection.createStatement();
		int returnValue = stmt.executeUpdate(sql);
		stmt.close();
		connection.close();
		return returnValue;
		
	}
}
