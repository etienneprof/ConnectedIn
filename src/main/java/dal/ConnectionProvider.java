package dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class ConnectionProvider {
	private static DataSource ds;
	
	public static Connection getConnection() throws SQLException {
		if (ds == null) {			
			Context context;
			try {
				context = new InitialContext();
				ds = (DataSource)context.lookup("java:comp/env/admin");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
		return ds.getConnection();
	}
}
