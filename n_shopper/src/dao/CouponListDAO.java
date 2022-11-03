
//	＜クーポン一覧表示＞
//テスト2
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
public class CouponListDAO {
	public static final String URL = "jdbc:mysql://localhost:3306/shopper";
	public static final String NAME = "root";
	public static final String PASS = "root";

	public static List<CouponList> selectCouponList(int user_id) {
		//		データベースにクーポンデータ一覧を持ってくる
		List<CouponList> list = new ArrayList<CouponList>();
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, NAME, PASS);

			//coupon,coupon_use(対象ユーザーに絞ったもの),sub_item,storeテーブルを結合
			String sql = "select *,COALESCE(cUse.used_flag,1) "
					+ "from coupon as c "
					+ "join store as s "
					+ "on c.store_id = s.id "
					+ "join sub_item as item "
					+ "on c.sub_item_id = item.id "
					+ "join (select * from coupon_use "
					+ "where user_id = ?) as cUse "
					+ "on c.id = cUse.coupon_id "
					+ "where c.start_date <= CURRENT_TIMESTAMP and  c.end_date >= CURRENT_TIMESTAMP "
					+ "order by COALESCE(cUse.used_flag,1) ";

			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, user_id);

			//SQL実行
			ResultSet result = stat.executeQuery();
			System.out.println(3);

			//一行ずつ確認　.nextで次の行→繰り返し
			while (result.next()) {

				int coupon_id = result.getInt("c.id");
				String subItem_id = result.getString("item.name");
				String name = result.getString("c.name");
				String comment = result.getString("comment");
				Timestamp end_date = result.getTimestamp("end_date");
				String image = result.getString("image");
				String storename = result.getString("s.name");
				int Used_flag = result.getInt("used_flag");
				int Used_flag2 = result.getInt("COALESCE(cUse.used_flag,1)");

				list.add(new CouponList(coupon_id, subItem_id, name, comment, image, storename,
						end_date, Used_flag2));

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

	//	<クーポン一覧から、クリックしたクーポンの詳細を表示する>
	public static CouponList selectDetailCoupon(int user_id, int coupon_id) {

		CouponList deteal = new CouponList();
		Connection conn = null;
		{

			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); //ドライバノロード
				conn = DriverManager.getConnection(URL, NAME, PASS);

				String sql = "select *,COALESCE(cUse.used_flag,1)"
						+ "from coupon as c "
						+ "join store as s "
						+ "on c.store_id = s.id "
						+ "join sub_item as item "
						+ "on c.sub_item_id = item.id "
						+ "join (select * from coupon_use "
						+ "where user_id = ?) as cUse "
						+ "on c.id = cUse.coupon_id "
						+ "where  c.id = ? and c.start_date <= CURRENT_TIMESTAMP and  c.end_date >= CURRENT_TIMESTAMP "
						+ "order by COALESCE(cUse.used_flag,1) ";
				System.out.println("1");

				//ステートメント化
				PreparedStatement stat = conn.prepareStatement(sql);
				stat.setInt(1, user_id);
				stat.setInt(2, coupon_id);
				//SQL実行
				ResultSet result = stat.executeQuery();

				System.out.println("selectDetailCouponSQL実行");

				if (result.next()) {

					deteal.setCoupon_id(result.getInt("c.id"));
					deteal.setSubItem_id(result.getString("item.name"));
					deteal.setName(result.getString("c.name"));
					deteal.setComment(result.getString("comment"));
					//					deteal.setDisplayStart(result.getTimestamp("displayStart"));
					deteal.setStart_date(result.getTimestamp("start_date"));
					deteal.setEnd_date(result.getTimestamp("end_date"));
					deteal.setComment(result.getString("comment"));
					deteal.setImage(result.getString("image"));
					deteal.setStorename(result.getString("s.name"));
					deteal.setUsed_flag(result.getInt("used_flag"));
					deteal.setUsed_flag2(result.getInt("COALESCE(cUse.used_flag,1)"));
					//					deteal.setActive(result.getInt("active"));

					return deteal;
				} else {
					return null;
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace(); //ドライバクラスが見つからなかったとき
				return null;

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally// ※通信切断の詳細 旧式のやり方
			{
				if (conn != null) {
					try {
						conn.close();//通信切断

					} catch (Exception e) {
						e.printStackTrace();
					}
					conn = null;
				}
			}
		}
	}



	public List<CouponList> selectCouponList(CouponList coupon) {

		//		データベースにクーポンデータ一覧を持ってくる
		List<CouponList> couponList = new ArrayList<CouponList>();
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, NAME, PASS);
			//店舗とクーポンjoin select 中品目、利用開始日、利用終了日、使用開始
			String sql = "select * from coupon as c join store as s on c.id = s.id where s.id=? and start_date <= CURRENT_TIMESTAMP and  end_date >= CURRENT_TIMESTAMP;";//先生に聞く！！！
			//			String sql = "select * from coupon as c "
			//					+ "join store as s "
			//					+ "on c.store_id = s.store_id"
			//					+ "where c.store_id=? ;";

			PreparedStatement stat = conn.prepareStatement(sql);

			//SQL文の？に使用する値を設定しSQL文を完成させる。
			stat.setInt(1, coupon.getStore_id());
			System.out.println("テスト-CouponListDAOの値：" + coupon.getStore_id());

			//SQL実行
			System.out.println();
			System.out.println("CouponListDAO SQL実行前");
			ResultSet result = stat.executeQuery();
			System.out.println("CouponListDAO SQL実行後");

			//一行ずつ確認　.nextで次の行→繰り返し
			while (result.next()) {

				int coupon_id = result.getInt("c.id");
				String subItem_id = result.getString("s.id");
				String name = result.getString("c.name");
				//
				//Date date= result.getDate("date");//util型
				//java.sql.Date date= result.getDate("display_start");//SQL型 つじつまを合わせるため...こっち消去の調整で大丈夫です。
				//				Timestamp start= result.getTimestamp("start");//timestamp
				Timestamp stop = result.getTimestamp("end_date");//timestamp

				//				int coupon_id = result.getInt("coupon_id");
				//				String subItem_id = result.getString("subItem_id");
				//				String name = result.getString("c.name");
				//				String comment = result.getString("comment");
				//				String image = result.getString("image");
				//				String storename = result.getString("s.name");
				//				Date date= result.getDate("date");
				//				int active = result.getInt("active");

				//				System.out.println(id + name + income);

				couponList.add(new CouponList(coupon_id, subItem_id, stop, name));
				//				list.add(new CouponList(coupon_id, subItem_id, start,stop,name));
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

		return couponList;
	}

}
