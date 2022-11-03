package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MainItem;

public class MainItemDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopper";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public List<MainItem> findAll() {

		System.out.println("******DAO******findAll(mainItem_Beans)が始動。");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<MainItem> mainItemList = new ArrayList<>();
		//データベースに接続する

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SQL文の準備
			String sql = " SELECT id,name FROM main_item;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELEC文を実行する。
			ResultSet rs = pStmt.executeQuery();

			//SELECT文の結果をArrayListに格納する。
			while (rs.next()) {
				int mainItem_id_a = rs.getInt("id");
				String mainItem_a = rs.getString("name");
				MainItem mainItemList_in = new MainItem(mainItem_id_a, mainItem_a);
				mainItemList.add(mainItemList_in);
			}

			System.out.println(mainItemList.get(0).getMainItem_id());
			//★★	mainItemList.get(0).getMainItem_id();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("******DAO******returnがnull;");
			return null;
		}
		System.out.println("******DAO******return;");
		return mainItemList;

	}

}
