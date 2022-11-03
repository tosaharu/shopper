package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.U_OtherUser;
import model.U_User;

public class UserDAO {

	public static final String URL = "jdbc:mysql://localhost:3306/shopper";
	public static final String NAME = "root";
	public static final String PASS = "root";

	//	CheckUserログインできるか
	public U_User CheckUser(U_User user) {

		Connection conn = null;
		//データベースに接続してみる

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //ドライバノロード
			conn = DriverManager.getConnection(URL, NAME, PASS);

			//SQL クエリ文を生成する
			String sql = "select * from user as a "
					+ "LEFT JOIN area AS b "
					+ "ON a.area_id = b.id "
					+ "LEFT JOIN prefecture AS c "
					+ "ON b.prefecture_id = c.id "
					+ "LEFT JOIN region AS d "
					+ "ON c.region_id = d.id "
					+ "where a.mail = ? and a.pass = ?";

			//ステートメント化
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, user.getMail());
			stat.setString(2, user.getPass());
			//SQL実行
			ResultSet result = stat.executeQuery();

			if (result.next()) {
				user.setUser_id(result.getInt("a.id"));
				user.setMail(result.getString("a.mail"));
				user.setPass(result.getString("a.pass"));
				user.setName(result.getString("a.name"));
				user.setGender(result.getInt("a.gender"));
				user.setBirthday(result.getDate("a.birthday"));
				user.setRegion_id(result.getInt("d.id"));
				user.setRegion_name(result.getString("d.name"));
				user.setPrefecture_id(result.getInt("c.id"));
				user.setPrefecture_name(result.getString("c.name"));
				user.setArea_id(result.getInt("b.id"));
				user.setArea_name(result.getString("b.name"));
				user.setActive(result.getInt("a.active_flag"));
				return user;
			} else {
				// ng
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
		//セッションに保存しておくデータの抽出

	}

	/**
	 * 志摩作、佐藤調整
	 * @param user
	 */
	public boolean userInsert(U_User user) {
		//util date ⇒ sql date
		long timeInMilliSeconds = user.getBirthday().getTime();
		java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);

		//ﾃﾞｰﾀﾍﾞｰｽ接続
		try (Connection conn = DriverManager.getConnection(URL, NAME, PASS)) {

			//			PreparedStatement pst = conn.prepareStatement(
			//
			//
			//					"SELECT mail FROM user where mail=? ");
			//			pst.setString(1, user.getMail());//メールアドレス
			//		//	System.out.println("preparedStatement:" + pst);
			//			//select実行格納
			//			ResultSet rs = pst.executeQuery();
			//
			//		//	System.out.println("result set:" + rs);
			//			user.setMail(rs.getString("mail"));//データベースのメールアドレス
			//			String mail_db = rs.getString("mail");
			//			System.out.println("チェック：" + mail_db);
			//
			//			int rs_mail = pst.executeUpdate();//帰ってくる件数
			//
			//			if (!(rs_mail!=1)) {
			//				//同じメールアドレスがすでに使われていたら、新規登録画面に戻しエラー文を表示
			//				System.out.println("同じメールアドレスがすでに登録されている");
			//
			//				return false;
			//			} else {
			//				//↑追加した処理 志摩

			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO user (mail,pass,name,birthday,gender,area_id,active_flag) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, user.getMail());//メールアドレス
			ps.setString(2, user.getPass());//パスワード
			ps.setString(3, user.getName());//表示名
			ps.setDate(4, sqlDate);//生年月日

			ps.setInt(5, user.getGender());//性別
			ps.setInt(6, user.getArea_id());//エリア
			ps.setInt(7, 1);//登録中:0・退会中:1

			int result = ps.executeUpdate();

			if (result != 1) {

				return false;
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public U_User selectUser(U_User user) {

		//データベースに接続してみる。
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//ドライバのロード
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(URL, NAME, PASS)) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT mail,pass,name,birthday,gender,area_id,active_flag from user where id = "
							+ user.getUser_id() + ";");
			//select実行格納
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setMail(rs.getString("mail"));
				user.setPass(rs.getString("pass"));
				user.setName(rs.getString("name"));

				//				user.setBirthday(rs.getDate("BIRTHDAY"));
				java.sql.Date birthday = rs.getDate("birthday");
				Date utilDate = new Date(birthday.getTime());
				user.setBirthday(utilDate);

				user.setArea_id(rs.getInt("area_id"));
				user.setGender(rs.getInt("gender"));

			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}
		return null;
	}

	/***
	 * 志摩：会員情報変
	 * @param user
	 */
	public boolean userChangeInfo(U_User user) {
		// util date ⇒ sql date
		long timeInMilliSeconds = user.getBirthday().getTime();
		java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		//ﾃﾞｰﾀﾍﾞｰｽ接続
		try (Connection conn = DriverManager.getConnection(URL, NAME, PASS)) {

			//SELECT文を準備
			PreparedStatement ps = conn.prepareStatement(
					//					"UPDATE user SET MAIL=?,NAME=?,BIRTHDAY=?, GENDER=?,AREA_ID=?,ACTIVE_FlAG=? " + "where id = "
					"UPDATE user SET MAIL=?,NAME=?, GENDER=?,AREA_ID=?,ACTIVE_FlAG=? where id =?;");

			System.out.println("daoを実行しました");
			//			user.setMail(rs.getString("mail"));
			//			user.setName(rs.getString("name"));
			//			user.setBirthday(rs.getDate("birthday"));
			//			user.setGender(rs.getInt("genber"));
			//			user.setArea_id(rs.getInt("area_id"));
			//			user.setActive(rs.getInt(1));
			ps.setString(1, user.getMail());//メールアドレス
			ps.setString(2, user.getName());//表示名
			ps.setInt(3, user.getGender());//性別
			ps.setInt(4, user.getArea_id());//エリア
			ps.setInt(5, 1);//登録中:1・退会中:0
			ps.setInt(6, user.getUser_id());

			//select実行格納
			int result = ps.executeUpdate();
			if (result != 1) {
			}

		} catch (SQLException e) {
			System.out.println("上書きに失敗しました");
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/***
	 * 志摩：会員退会処理
	 * @param user
	 */
	public U_User userQuit(U_User user) {

		System.out.println(user.getUser_id() + "dao実行します");
		//ﾃﾞｰﾀﾍﾞｰｽ接続
		try (Connection conn = DriverManager.getConnection(URL, NAME, PASS)) {
			PreparedStatement ps = conn.prepareStatement(
					//SELECT文を準備
					"UPDATE user SET active_flag= ? " + "where id = " + user.getUser_id() + ";");

			ps.setInt(1, 0);//退会中:0・継続中:1

			int result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("退会できませんでした");
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * 志摩：パスワード変更　※パスワード再設定とは別です
	 * @param user
	 * @return
	 */
	public U_User userNewPass(U_User user) {

		System.out.println(user.getUser_id());

		System.out.println(user.getPass() + "dao実行します");
		//ﾃﾞｰﾀﾍﾞｰｽ接続
		try (Connection conn = DriverManager.getConnection(URL, NAME, PASS)) {
			PreparedStatement ps = conn.prepareStatement(
					//SELECT文を準備
					"UPDATE user SET PASS= ? " + "where id = " + user.getUser_id() + ";");

			ps.setString(1, user.getPass());

			int result = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("パスワードを変更できませんでした");
			e.printStackTrace();
		}
		return user;
	}

	//パスワード再発行のためのユーザ照合
	public U_User CollationUser(U_User user) {
		//util date ⇒ sql date
		long timeInMilliSeconds = user.getBirthday().getTime();
		java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);

		//データ保存用リストを準備
		//List<User> list = new ArrayList<User>();
		Connection conn = null;
		//データベースに接続してみる
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //ドライバノロード
			conn = DriverManager.getConnection(URL, NAME, PASS);
			String sql = "SELECT * FROM user WHERE mail=? and birthday=?";
			//ステートメント化
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setString(1, user.getMail());
			stat.setDate(2, (java.sql.Date) user.getBirthday());
			System.out.println(user.getMail());
			System.out.println(user.getBirthday());
			//SQLを実行し結果を得る
			ResultSet result = stat.executeQuery();

			while (result.next()) {
				user.setUser_id(result.getInt("id"));
				user.setMail(result.getString("mail"));
				user.setPass(result.getString("pass"));
				user.setName(result.getString("name"));
				user.setGender(result.getInt("gender"));
				user.setBirthday(result.getDate("birthday"));
				user.setArea_id(result.getInt("area_id"));
				user.setActive(result.getInt("active_flag"));
				return user;
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
		return null;
	}

	//パスワード再発行のupdate文
	public U_User UpdatePass(String pass, int user_id) {

		Connection conn = null;

		//データベースに接続してみる。
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//ドライバのロード
			conn = DriverManager.getConnection(URL, NAME, PASS);

			//SQLクエリ文を生成する。
			PreparedStatement ps = conn.prepareStatement(
					//SELECT文を準備
					"UPDATE user SET pass = ? " + "WHERE id = ? " + ";");

			ps.setString(1, pass);
			ps.setInt(2, user_id);

			int result = ps.executeUpdate();

			System.out.println("パスワードを更新しました。");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();//通信切断

				} catch (Exception e2) {
					e2.printStackTrace();
				}
				conn = null;
			}
		}
		return null;
	}

	//フォローユーザー一覧取得
	public List<U_OtherUser> followUserList(int user_id) {

		Connection conn = null;

		List<U_OtherUser> followUserList = new ArrayList<>();
		//データベースに接続してみる。
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//ドライバのロード
			conn = DriverManager.getConnection(URL, NAME, PASS);
			//SQLクエリ文を生成する。
			String sql = "SELECT u.name, COUNT(f.follow_user_id) \r\n"
					+ "FROM user u \r\n"
					+ "JOIN follow f \r\n"
					+ "ON u.id = f.follow_user_id \r\n"
					+ "WHERE user_id=?\r\n"
					+ "GROUP BY u.name;";

			//			SELECT u.name, COUNT(f.follow_user_id)
			//			FROM user u
			//			JOIN follow f
			//			ON u.id = f.follow_user_id
			//			WHERE use_id=?
			//			GROUP BY u.name;

			//ステートメント化
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				stat.setInt(1, user_id);
				String user_name = rs.getString("u.name");
				int count_follower = rs.getInt("COUNT(f.follow_user_id)");
				U_OtherUser u_OtherUser = new U_OtherUser(user_name, count_follower);
				followUserList.add(u_OtherUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return followUserList;
	}

	//フォロワーユーザー一覧取得
	public List<U_OtherUser> followerUserList(int user_id) {

		Connection conn = null;

		List<U_OtherUser> followerUserList = new ArrayList<>();

		//データベースに接続してみる。
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//ドライバのロード
			conn = DriverManager.getConnection(URL, NAME, PASS);
			//SQLクエリ文を生成する。
			String sql = "	SELECT u.name, COUNT(f.follow_user_id)\r\n"
					+ "			FROM user u\r\n"
					+ "			JOIN follow f\r\n"
					+ "			ON u.id=? = f.follow_user_id=?\r\n"
					+ "			GROUP BY u.name;";

			//			SELECT u.name, COUNT(f.follow_user_id)
			//			FROM user u
			//			JOIN follow f
			//			ON u.id = f.follow_user_id
			//			GROUP BY u.name;
			//ステートメント化
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			while (rs.next()) {
				stat.setInt(1, user_id);
				stat.setInt(2, user_id);
				String user_name = rs.getString("name");
				int count_follower = rs.getInt("count(follow_user_id)");
				U_OtherUser u_OtherUser = new U_OtherUser(user_name, count_follower);
				followerUserList.add(u_OtherUser);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return followerUserList;
	}

	//ユーザーランキング一覧取得
	public List<U_OtherUser> rankingUserList() {
		Connection conn = null;
		System.out.println("DAO 0");
		List<U_OtherUser> rankingUserList = new ArrayList<>();

		//データベースに接続してみる。
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//ドライバのロード
			conn = DriverManager.getConnection(URL, NAME, PASS);
			//SQLクエリ文を生成する。
			String sql = "SELECT RANK() OVER (ORDER BY COUNT(follow_user_id) DESC) AS RANKING,\r\n"
					+ "name,user_id, COUNT(follow_user_id) \r\n"
					+ "FROM follow\r\n"
					+ "JOIN user\r\n"
					+ "ON follow.follow_user_id = user.id\r\n"
					+ "GROUP BY follow_user_id, name;";
			//			上記のSQL文と同じ
			//						SELECT RANK() OVER (ORDER BY COUNT(follow_user_id) DESC) AS RANKING,
			//						name, COUNT(follow_user_id),
			//						FROM follow
			//						JOIN user
			//						ON follow.follow_user_id = user.id
			//						GROUP BY follow_user_id, name;

			System.out.println("DAO 1");
			//ステートメント化
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();
			System.out.println("DAO 2");

			while (rs.next()) {
				int count_ranking = rs.getInt("RANKING");
				String user_name = rs.getString("name");

				int follow_user_id = rs.getInt("use_id");

				int count_follower = rs.getInt("COUNT(follow_user_id)");

				//selectできているかの確認
				//				System.out.println(count_ranking);
				//				System.out.println(user_name);
				//				System.out.println(count_follower);

				U_OtherUser u_OtherUser = new U_OtherUser(count_ranking, user_name, count_follower, follow_user_id);
				rankingUserList.add(u_OtherUser);

				//				System.out.println(rankingUserList);
				System.out.println("DAO 通過");

				//				return rankingUserList;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DAO null");
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rankingUserList;
	}

	//	}

	//フォロー機能
	public U_OtherUser FollowInsert(int follow_id, int follower_id) {
		Connection conn = null;
		System.out.println("DAO1");

		//データベースに接続してみる。
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//ドライバのロード
			conn = DriverManager.getConnection(URL, NAME, PASS);
			//SQLクエリ文を生成する。
			String sql = "INSERT INTO follow (user_id,follow_user_id) VALUES(?,?)";
			System.out.println("DAO2");
			//ステートメント化
			PreparedStatement stat = conn.prepareStatement(sql);

			stat.setInt(1, follow_id);
			stat.setInt(2, follower_id);
			System.out.println("DAO3");
			int result = stat.executeUpdate();
			System.out.println("DAO 通過");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();//通信切断

				} catch (Exception e2) {
					e2.printStackTrace();
				}
				conn = null;
			}
		}
		return null;
	}

	public List<U_OtherUser> FollowSelect(int follow_id, int follower_id) {
		Connection conn = null;
		System.out.println("DAO 0");

		List<U_OtherUser> followSelectList = new ArrayList<>();

		//データベースに接続してみる。
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//ドライバのロード
			conn = DriverManager.getConnection(URL, NAME, PASS);
			//SQLクエリ文を生成する。

			String sql = "SELECT user_id FROM follow WHERE follow_user_id=?";

			//ステートメント化
			PreparedStatement stat = conn.prepareStatement(sql);
			ResultSet rs = stat.executeQuery();

			stat.setInt(1, follow_id);
			stat.setInt(2, follower_id);
			while (rs.next()) {

				int follow_user_id = rs.getInt("follow_id");

				U_OtherUser u_OtherUser = new U_OtherUser(follow_user_id);
				followSelectList.add(u_OtherUser);

			}

			//return ;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();//通信切断

				} catch (Exception e2) {
					e2.printStackTrace();
				}
				conn = null;
			}
		}
		return followSelectList;
	}
}
