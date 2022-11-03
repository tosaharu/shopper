package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Store;

public class StoreDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopper";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public List<Store> findAll() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<Store> storeList = new ArrayList<>();
		//データベースに接続する

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SQL文の準備
			String sql = "SELECT * FROM shopper.store;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELEC文を実行する。
			ResultSet rs = pStmt.executeQuery();

			//SELECT文の結果をArrayListに格納する。
			while (rs.next()) {

				int store_id_a = rs.getInt("id");
				int area_id_a = rs.getInt("area_id");
				String storeName_a = rs.getString("name");
				String subPostcode_a = rs.getString("address");
				String businessHours_a = rs.getString("business_hours");
				String hp_a = rs.getString("hp");
				int tel_a = rs.getInt("tel");
				String payment_a = rs.getString("payment");
				String information_a = rs.getString("information");
				String service_a = rs.getString("service");
				int administratedFlag_a = rs.getInt("administrated_flag");
				int openFlag_a = rs.getInt("open_flag");
				int count_a = rs.getInt("count");

				Store storeList_in = new Store(store_id_a, storeName_a, subPostcode_a, businessHours_a,
						hp_a, tel_a, payment_a, information_a, service_a, administratedFlag_a,
						openFlag_a, count_a);
				storeList_in.setArea_id(area_id_a);
				storeList.add(storeList_in);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return storeList;

	}


	public Store findTheStore(Store store) {
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
			String sql = "SELECT * FROM shopper.store where id=?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文の？に使用する値を設定しSQL文を完成させる。
			pStmt.setInt(1,store.getStore_id());

			//SELEC文を実行する。
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				store.setStore_id(rs.getInt("id"));
				store.setStoreName(rs.getString("name"));
				store.setArea_id(rs.getInt("area_id"));
				store.setSubPostcode(rs.getString("address"));
				store.setBusinessHours(rs.getString("business_hours"));
				store.setHP(rs.getString("hp"));
				store.setTel(rs.getInt("tel"));
				store.setPayment(rs.getString("payment"));
				store.setInformation(rs.getString("information"));
				store.setService(rs.getString("service"));
				store.setCorporationFlag(rs.getInt("administrated_flag"));
				store.setOpenFlag(rs.getInt("open_flag"));
				store.setCount(rs.getInt("count"));

			}
			return store;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}


	public boolean create(Store store) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}


		//データベースに接続する

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SQL文の準備
			String sql = "INSERT INTO store (name,area_id,address,business_hours,hp,tel,payment,information,service,administrated_flag,open_flag) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文の？に使用する値を設定しSQL文を完成させる。
			pStmt.setString(1,store.getStoreName());
			pStmt.setInt(2, store.getArea_id());
			pStmt.setString(3,store.getSubPostcode());
			pStmt.setString(4,store.getBusinessHours());
			pStmt.setString(5,store.getHp());
			pStmt.setInt(6, store.getTel());
			pStmt.setString(7,store.getPayment());
			pStmt.setString(8,store.getInformation());
			pStmt.setString(9,store.getService());
			pStmt.setInt(10, store.getCorporationFlag());
			pStmt.setInt(11, store.getOpenFlag());

			//SQL文を実行する。
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}

//			★★★消す★★★「
			//ResultSet rs = pStmt.executeQuery();
//			if (rs.next()) {
//				store.setStore_id(rs.getInt("id"));
//				store.setStoreName(rs.getString("name"));
//				store.setArea_id(rs.getInt("area_id"));
//				store.setSubPostcode(rs.getString("address"));
//				store.setBusinessHours(rs.getString("business_hours"));
//				store.setHP(rs.getString("hp"));
//				store.setTel(rs.getInt("tel"));
//				store.setPayment(rs.getString("payment"));
//				store.setInformation(rs.getString("information"));
//				store.setService(rs.getString("service"));
//				store.setCorporationFlag(rs.getInt("administrated_flag"));
//				store.setOpenFlag(rs.getInt("open_flag"));
//				store.setCount(rs.getInt("count"));
//
//			}
//			★★★消す★★★」
			return true;

		}  catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}





}
