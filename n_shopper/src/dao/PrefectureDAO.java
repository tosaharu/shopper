package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Prefecture;

public class PrefectureDAO {

	public static final String URL = "jdbc:mysql://localhost:3306/shopper";
	public static final String NAME = "root";
	public static final String PASS = "root";

	public List<Prefecture> getPrefectureList() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<Prefecture> prefectureList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(
				URL, NAME, PASS)) {
			String sql = "SELECT * FROM prefecture";
			PreparedStatement pSmt = conn.prepareStatement(sql);

			ResultSet result = pSmt.executeQuery();

			while (result.next()) {
				int prefecture_id = result.getInt("ID");
				String prefecture_name = result.getString("NAME");
				int region_id = result.getInt("REGION_ID");
				Prefecture prefecture = new Prefecture(prefecture_id, prefecture_name, region_id);
				prefectureList.add(prefecture);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return prefectureList;
	}
}





