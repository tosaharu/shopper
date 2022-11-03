//＜クーポンを使用したとき、だれがどのクーポンを何時に使ったか記録＞

package dao;
//テスト

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CouponUseDAO {
	//データベースアクセスオブジェト
	public static final String URL = "jdbc:mysql://localhost:3306/shopper";
	public static final String NAME = "root";
	public static final String PASS = "root";


//	使用済みフラグを変更するメソッド
	public static void updateU_CouponList(int coupon_id) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, NAME, PASS);

			String sql ="update coupon_use "
					+ "set used_flag = 1 "
					+ "where coupon_id = ? ";

			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, coupon_id);

			stat.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //ドライバクラスが見つからなかったとき

		} catch (SQLException e) {
			e.printStackTrace();

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





	//		insertU_CouponList クーポン使用テーブルにデータをインサート
	public static void insertU_CouponList(int coupon_id, int user_id) {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, NAME, PASS);

			String sql = "INSERT INTO coupon_use "
					+ "(id,user_id,start_date)"
					+ "VALUES"
					+ "(? ,? ,CURRENT_TIMESTAMP)";

			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, coupon_id);
			stat.setInt(2, user_id);

			stat.executeUpdate();

		} catch (ClassNotFoundException e) {
			e.printStackTrace(); //ドライバクラスが見つからなかったとき

		} catch (SQLException e) {
			e.printStackTrace();

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

	//			selectU_CouponList クーポン使用状況判定のためにデータをセレクト
	public static int selectCouponList(int user_id, int coupon_id)  {
		Connection conn1 = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn1 = DriverManager.getConnection(URL, NAME, PASS);
			//クーポンid ユーザーid で絞り込み→使用開始時間をセレクト
			String sql = "SELECT start_date FROM coupon_use where user_id = ? and coupon_id = ?";
			PreparedStatement stat = conn1.prepareStatement(sql);

			stat.setInt(1, user_id);
			stat.setInt(2, coupon_id);
			//SQL実行
			ResultSet result = stat.executeQuery();

			//実行してデータがあれば、
			if (result.next()) {

					Timestamp UseStartTime = result.getTimestamp("UseStartTime");

					String UseStartTimeToString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(UseStartTime);

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date UseStartTimeToDate = sdf.parse(UseStartTimeToString);

					System.out.println("String:"+UseStartTimeToString);
					System.out.println("Date:"+UseStartTimeToDate);


				//				unixTimestampに変換したUseStartTime
//					LocalDateTime useStartTimeToDate = LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0);
//			        long epochSecond = UseStartTimeToDate.atZone(ZoneId.systemDefault()).toEpochSecond();
//			        System.out.println(epochSecond);

			        long unixTime =UseStartTimeToDate.getTime()/1000;
			        System.out.println(unixTime);



				//				現在時刻のunixTimestamp
				long now = System.currentTimeMillis()/1000;
				System.out.println("現在時刻 秒"+now);

				//				秒で比較
				long seconds = now-unixTime;
				System.out.println(seconds);
				//				分に変換
				long minutes = seconds/60;
				System.out.println("分"+minutes);

				if (minutes >= 30) {
					//使用済み
					return 0;
				} else {
					//使用中
					return 1;
				}
			} else {
				//使用前
				return 2;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (conn1 != null) {
				try {
					conn1.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 0;

	}

}
