package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Area;

public class AreaDAO {

	public static final String URL = "jdbc:mysql://localhost:3306/shopper";
	public static final String NAME = "root";
	public static final String PASS = "root";

	public List<Area> getAreaList() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<Area> areaList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(
				URL, NAME, PASS)) {
			String sql = "SELECT * FROM area";
			PreparedStatement pSmt = conn.prepareStatement(sql);

			ResultSet result = pSmt.executeQuery();

			while (result.next()) {
				int area_id = result.getInt("ID");
				String area_name = result.getString("NAME");
				int prefecture_id = result.getInt("PREFECTURE_ID");
				Area area = new Area(area_id, area_name, prefecture_id);
				areaList.add(area);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return areaList;
	}
}





