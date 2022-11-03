package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemDAO {


	private final String JDBC_URL =  "jdbc:mysql://localhost:3306/shopper";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public List<Item> findAll(){


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<Item> itemList = new ArrayList<>();
		//データベースに接続する

				try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

					//SQL文の準備
					String sql = "SELECT id,name,sub_item_id,unit FROM item";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					//SELEC文を実行する。
					ResultSet rs = pStmt.executeQuery();

					//SELECT文の結果をArrayListに格納する。
					while (rs.next()) {
						int item_id_a=			rs.getInt("id");
						 String item_a =	rs.getString("name");
						 int subItem_id_a =		rs.getInt("sub_item_id");
						 int unit_a =		rs.getInt("unit");
						Item itemList_in = new Item(item_id_a, item_a, subItem_id_a,unit_a);
						itemList.add(itemList_in);
					}

				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				return itemList;

	}

}
