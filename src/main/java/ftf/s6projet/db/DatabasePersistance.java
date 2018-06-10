package ftf.s6projet.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabasePersistance {
	
	public String init() {
		String user_name = "test";
	    try {
	        Context ctx = new InitialContext();
	        if (ctx == null)
	            throw new Exception("Boom - No Context");
	        Context envCtx = (Context) ctx.lookup("java:comp/env");
	        DataSource ds = (DataSource) envCtx.lookup("jdbc/postgres");

	        if (ds != null) {
	            Connection conn = ds.getConnection();

	            if (conn != null) {
	            	user_name = "Connection success";
	                conn.close();
	            } else {
	            	user_name = "Connection failed";
	            }
	        }
	    }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
		return user_name;
	}
}
