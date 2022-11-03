package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.StoreUpdate;

public class StoreUpdateDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopper";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public StoreUpdate findTheUpdate(StoreUpdate store_update) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		//データベースに接続する

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SQL文の準備
			String sql = "SELECT  id,date,user_id,store_user_id FROM shopper.store_update where id=?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文の？に使用する値を設定しSQL文を完成させる。
			pStmt.setInt(1, store_update.getStoreUpdate_id());

			//SELEC文を実行する。
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				store_update.setStoreUpdate_id(rs.getInt("id"));
				store_update.setDate(rs.getDate("date"));
				store_update.setUser_id(rs.getInt("user_id"));
				store_update.setStoreUser_id(rs.getInt("store_user_id"));

			}
			return store_update;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
