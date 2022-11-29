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

	private final String URL = "jdbc:mysql://localhost:3306/shopper";
	private final String NAME = "root";
	private final String PASS = "root";

	public List<Store> findAll() {

		List<Store> storeList = new ArrayList<>();

		//ドライバのロード
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// データベース接続～SQL実行
		try {
			// データベース接続
			Connection conn = DriverManager.getConnection(URL, NAME, PASS);

			String sql = "SELECT * FROM shopper.store";
			PreparedStatement ps = conn.prepareStatement(sql);

			//SELECT文を実行する。
			ResultSet result = ps.executeQuery();

			//SELECT文の結果をArrayListに格納する。
			while (result.next()) {

				int store_id = result.getInt("id");
				int area_id = result.getInt("area_id");
				String storeName = result.getString("name");
				String subPostcode = result.getString("address");
				String startHour = result.getString("start_hour");
				String endHour = result.getString("end_hour");
				String hp = result.getString("hp");
				int tel = result.getInt("tel");
				String payment = result.getString("payment");
				String information = result.getString("information");
				String service = result.getString("service");
				int administratedFlag = result.getInt("administrated_flag");
				int openFlag = result.getInt("open_flag");
				int count = result.getInt("count");

				Store store = new Store(store_id, storeName, subPostcode,startHour, endHour,
						hp, tel, payment, information, service, administratedFlag,
						openFlag, count);
				store.setArea_id(area_id);
				storeList.add(store);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return storeList;

	}


	public Store findTheStore(Store store) {

		//ドライバのロード
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// データベース接続～SQL実行
		try {
			// データベース接続
			Connection conn = DriverManager.getConnection(URL, NAME, PASS);

			// SQL文の作成
			String sql = "SELECT * FROM shopper.store where id=?;";
			PreparedStatement ps = conn.prepareStatement(sql);

			//SELECT文の？に使用する値を設定しSQL文を完成させる。
			ps.setInt(1,store.getStore_id());

			//SELECT文を実行する。
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				store.setStore_id(result.getInt("id"));
				store.setStoreName(result.getString("name"));
				store.setArea_id(result.getInt("area_id"));
				store.setSubPostcode(result.getString("address"));
				store.setStartHour(result.getString("start_hour"));
				store.setEndHour(result.getString("end_hour"));
				store.setHP(result.getString("hp"));
				store.setTel(result.getInt("tel"));
				store.setPayment(result.getString("payment"));
				store.setInformation(result.getString("information"));
				store.setService(result.getString("service"));
				store.setCorporationFlag(result.getInt("administrated_flag"));
				store.setOpenFlag(result.getInt("open_flag"));
				store.setCount(result.getInt("count"));

			}
			return store;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}


	public int create(Store store) {
		System.out.println("店舗作成処理開始");

		//ドライバのロード
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// データベース接続～SQL実行
		try {
			// データベース接続
			Connection conn = DriverManager.getConnection(URL, NAME, PASS);

			// SQL文の作成
			String sql1 = "INSERT INTO store (name,area_id,address,start_hour,end_hour,hp,tel,payment,information,service,administrated_flag,open_flag) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement ps = conn.prepareStatement(sql1);

			//SELECT文の？に使用する値を設定しSQL文を完成させる。
			ps.setString(1,store.getStoreName());
			ps.setInt(2, store.getArea_id());
			ps.setString(3,store.getSubPostcode());
			ps.setString(4,store.getStartHour());
			ps.setString(5,store.getEndHour());;
			ps.setString(6,store.getHp());
			ps.setInt(7, store.getTel());
			ps.setString(8,store.getPayment());
			ps.setString(9,store.getInformation());
			ps.setString(10,store.getService());
			ps.setInt(11, store.getCorporationFlag());
			ps.setInt(12, store.getOpenFlag());

			//SQL文を実行する。
			int updated = ps.executeUpdate();
			if (updated != 1) {
				return 0;

			}
			String sql2 = "SELECT LAST_INSERT_ID()";
			ps = conn.prepareStatement(sql2);

			//SELECT文を実行する。
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				int storeId = result.getInt("LAST_INSERT_ID()");
				System.out.println(storeId);
				return storeId;

			}
			return 0;

		}  catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}





}
