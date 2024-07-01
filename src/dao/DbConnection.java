package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static void main(String[] args) {
		System.out.println(DbConnection.getDb());
		//測試下面方法return了啥並印出
	}

	public static Connection getDb() {
		String url="jdbc:mysql://localhost:3306/myshop";
		String user="root";
		String password="1234";
		Connection conn=null;
		//寫好電腦位址,ID,密碼, 先建一條網路線
		try {	//1.檢查Driver,記得build path 2.確認上面資訊能連sql資料庫
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return conn;
	}
	
	
}
