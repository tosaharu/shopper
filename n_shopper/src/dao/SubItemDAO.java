package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.SubItem;

public class SubItemDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopper";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public List<SubItem> findAll() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<SubItem> subItemList = new ArrayList<>();
		//データベースに接続する

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SQL文の準備
			String sql = "SELECT id,name,main_item_id FROM sub_item";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELEC文を実行する。
			ResultSet rs = pStmt.executeQuery();

			//SELECT文の結果をArrayListに格納する。
			while (rs.next()) {
				int subItem_id_a = rs.getInt("id");
				String subItem_a = rs.getString("name");
				int mainItem_id_a = rs.getInt("main_item_id");
				SubItem subItemList_in = new SubItem(subItem_id_a, subItem_a, mainItem_id_a);
				subItemList.add(subItemList_in);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return subItemList;

	}

}
