package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Region;

public class RegionDAO {

	public static final String URL = "jdbc:mysql://localhost:3306/shopper";
	public static final String NAME = "root";
	public static final String PASS = "root";

	public List<Region> getRegionList() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<Region> regionList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(
				URL, NAME, PASS)) {
			String sql = "SELECT * FROM region";
			PreparedStatement pSmt = conn.prepareStatement(sql);

			ResultSet result = pSmt.executeQuery();

			while (result.next()) {
				int region_id = result.getInt("ID");
				String region_name = result.getString("NAME");
				Region region = new Region(region_id, region_name);
				regionList.add(region);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return regionList;
	}
}





