package pl.n2god.data_source;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author n2god on 19/12/2019
 * @project DataSource
 */
public class ConnectionProvider {
	private static DataSource dataSource;

	public static Connection getConnection(){
		return getDSInstance().
	}

	private static DataSource getDSInstance() {
		if (dataSource == null){
			try {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:comp/env");
				dataSource = (DataSource) envContext.lookup("jdbc/world");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return dataSource;
	}
}
