package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * 
 * Class DBcontect chứa các phương thức dùng để kết nối với database
 * 
 * */
public class DBcontext {
	//các tham số cần thiết
	private final String serverName = "localhost";
	private final String databaseName = "ShoppingDB";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String user = "servlet";
	private final String password = "servlet";
	
	private final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//phương thức trả về một kết nối Connection vói database
	public Connection getConnection() throws SQLException {
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + databaseName;
		if(instance == null || instance.trim().isEmpty()) {
			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + databaseName;
		}
		try {
			Class.forName(driver);			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return DriverManager.getConnection(url, user, password);
	}
}
