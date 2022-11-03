//	＜クーポン一覧表示＞

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.CouponList;

//データベースアクセスオブジェト
public class CouponDAO {
	public static final String URL = "jdbc:mysql://localhost:3306/shopper";
	public static final String NAME = "root";
	public static final String PASS = "root";

	public static List<CouponList> selectCouponList() {
		//		データベースにクーポンデータ一覧を持ってくる
		List<CouponList> list = new ArrayList<CouponList>();
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, NAME, PASS);
			//店舗とクーポンjoin select 中品目、利用開始日、利用終了日、使用開始
			String sql = "select * "
					+ "from coupon as c "
					+ "join store as s "
					+ "on c.id = s.id "
					+ "join couponuse as u "
					+ "on c.id = u.id ";
//			String sql = "select * "
//					+ "from coupon as c "
//					+ "join store as s "
//					+ "on c.coupon_id = s.store_id "
//					+ "join couponuse as u "
//					+ "on c.coupon_id = u.user_id ";
			PreparedStatement stat = conn.prepareStatement(sql);
			//SQL実行
			ResultSet result = stat.executeQuery();

			//一行ずつ確認　.nextで次の行→繰り返し
			while (result.next()) {

				int coupon_id = result.getInt("id");
				String subItem_id = result.getString("id");
				String name = result.getString("c.name");
				String comment = result.getString("comment");
				String image = result.getString("image");
				String storename = result.getString("s.name");
				int active = result.getInt("active");

//				int coupon_id = result.getInt("coupon_id");
//				String subItem_id = result.getString("subItem_id");
//				String name = result.getString("c.name");
//				String comment = result.getString("comment");
//				String image = result.getString("image");
//				String storename = result.getString("s.name");
//				int active = result.getInt("active");

				//				System.out.println(id + name + income);

				list.add(new CouponList(coupon_id, subItem_id, name, comment, image, storename, active));

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public List<CouponList> selectCouponList(CouponList coupon_list) {
		//@author Jasmine

		//		データベースにクーポンデータ一覧を持ってくる
		List<CouponList> list = new ArrayList<CouponList>();
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, NAME, PASS);
			//店舗とクーポンjoin select 中品目、利用開始日、利用終了日、使用開始
			String sql = "select * from coupon as c join store as s on c.id = s.id where c.id=?;";
//			String sql = "select * from coupon as c "
//					+ "join store as s "
//					+ "on c.store_id = s.store_id"
//					+ "where c.store_id=? ;";

			PreparedStatement stat = conn.prepareStatement(sql);

			//SQL文の？に使用する値を設定しSQL文を完成させる。
			stat.setInt(1, coupon_list.getStore_id());
			System.out.println("テスト-CouponDAOの値："+coupon_list.getStore_id());

			//SQL実行
			ResultSet result = stat.executeQuery();

			//一行ずつ確認　.nextで次の行→繰り返し
			while (result.next()) {

				int coupon_id = result.getInt("c.id");
				String subItem_id = result.getString("s.id");
				String name = result.getString("c.name");
//
				//Date date= result.getDate("date");//util型
				//java.sql.Date date= result.getDate("display_start");//SQL型 つじつまを合わせるため...こっち消去の調整で大丈夫です。
				Timestamp start= result.getTimestamp("start");//timestamp
				Timestamp stop= result.getTimestamp("stop");//timestamp


//				int coupon_id = result.getInt("coupon_id");
//				String subItem_id = result.getString("subItem_id");
//				String name = result.getString("c.name");
//				String comment = result.getString("comment");
//				String image = result.getString("image");
//				String storename = result.getString("s.name");
//				Date date= result.getDate("date");
//				int active = result.getInt("active");

				//				System.out.println(id + name + income);

				list.add(new CouponList(coupon_id, subItem_id, start,stop,name));
//				list.add(new CouponList(coupon_id, subItem_id, name, comment, image, storename, active));

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

}