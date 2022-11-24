package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.U_OtherUser;
import model.U_User;

/**
 * ユーザーに関する情報をDBとやり取りするDAO
 * @author Haruka Sato
 */
public class UserDAO {

	public static final String URL = "jdbc:mysql://localhost:3306/shopper";
	public static final String NAME = "root";
	public static final String PASS = "root";

	/**
	 * メールアドレスがDBに存在するかチェック（主に新規会員登録時のバリデーションで使用）
	 * @param mail ユーザーが入力したメールアドレス
	 * @return メールアドレスが存在する場合はuser_idを返し、ない場合に0を返す
	 */
	public int checkMail(String mail) {

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
			PreparedStatement ps = conn.prepareStatement("SELECT * from user where mail = ?");
			ps.setString(1, mail);

			// SELECT実行
			ResultSet result = ps.executeQuery();

			// 合致した結果があるか確認
			if (result.next()) {
				int id = result.getInt("id");
				return id;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	/**
	 * メールアドレスをもとにユーザーデータを取得する（主にログインで使用）
	 * @param user ユーザーのデータ
	 * @return ログイン可能であれば、そのユーザーデータをUser型を、可能でない場合はnullを返す
	 */
	public U_User getUserByMail(String mail) {

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
			String sql = "select * from user as a "
					+ "LEFT JOIN area AS b "
					+ "ON a.area_id = b.id "
					+ "LEFT JOIN prefecture AS c "
					+ "ON b.prefecture_id = c.id "
					+ "LEFT JOIN region AS d "
					+ "ON c.region_id = d.id "
					+ "where a.mail = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mail);

			// SELECT実行
			ResultSet result = ps.executeQuery();

			// 合致した結果を格納
			if (result.next()) {
				// 合致するデータがある場合
				U_User user = new U_User();
				user.setUser_id(result.getInt("a.id"));
				user.setMail(result.getString("a.mail"));
				user.setPass(result.getString("a.pass"));
				user.setName(result.getString("a.name"));
				user.setGender(result.getInt("a.gender"));
				user.setBirthday(result.getTimestamp("a.birthday").toLocalDateTime());
				user.setRegion_id(result.getInt("d.id"));
				user.setRegion_name(result.getString("d.name"));
				user.setPrefecture_id(result.getInt("c.id"));
				user.setPrefecture_name(result.getString("c.name"));
				user.setArea_id(result.getInt("b.id"));
				user.setArea_name(result.getString("b.name"));
				user.setActive(result.getInt("a.active_flag"));
				return user;
			} else {
				// 合致するデータがない場合
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ユーザーデータをDBに新規登録する
	 * @param user ユーザーのデータ
	 * @return 正常に処理が完了した場合にtrue、しなかった場合にfalseを返す
	 */
	public boolean userInsert(U_User user) {
		/*
		 * Date型を使用する旧方式
		 */
		//		//util date を sql date に変換
		//		long timeInMilliSeconds = user.getBirthday().getTime();
		//		java.sql.Date sqlDate = new java.sql.Date(timeInMilliSeconds);

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
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO user (mail,pass,name,birthday,gender,area_id,active_flag) VALUES(?,?,?,?,?,?,?)");
			ps.setString(1, user.getMail());
			ps.setString(2, user.getPass());
			ps.setString(3, user.getName());
			// Localdatetime型→Timestamp型(sql)に変換
			Timestamp birthday = Timestamp.valueOf(user.getBirthday());
			ps.setTimestamp(4, birthday);
			ps.setInt(5, user.getGender());
			ps.setInt(6, user.getArea_id());
			// 入会状態である「1」でフラグを設定
			ps.setInt(7, 1);

			// INSERT実行
			int result = ps.executeUpdate();

			// 処理の結果を確認
			if (result != 1) {
				// 正常に完了しなかった場合(正常に処理が完了するのは result = 1 の場合のみ)
				return false;
			}
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * ユーザーIDを使用してユーザー情報を取得する（不要になった？使用していないかもなのでいったんコメントアウト）
	 * @param user
	 * @return
	 */
	//	public U_User selectUser(U_User user) {
	//
	//		//ドライバのロード
	//		try {
	//			Class.forName("com.mysql.cj.jdbc.Driver");
	//		} catch (ClassNotFoundException e) {
	//			e.printStackTrace();
	//		}
	//
	//		// データベース接続～SQL実行
	//		try {
	//			// データベース接続
	//			Connection conn = DriverManager.getConnection(URL, NAME, PASS);
	//
	//			// SQL文の作成
	//			PreparedStatement ps = conn.prepareStatement(
	//					"SELECT mail,pass,name,birthday,gender,area_id,active_flag from user where id = "
	//							+ user.getUser_id() + ";");
	//			// SELECT実行
	//			ResultSet rs = ps.executeQuery();
	//			if (rs.next()) {
	//				user.setMail(rs.getString("mail"));
	//				user.setPass(rs.getString("pass"));
	//				user.setName(rs.getString("name"));
	//
	//				//				user.setBirthday(rs.getDate("BIRTHDAY"));
	//				java.sql.Date birthday = rs.getDate("birthday");
	//				Date utilDate = new Date(birthday.getTime());
	//				user.setBirthday(utilDate);
	//
	//				user.setArea_id(rs.getInt("area_id"));
	//				user.setGender(rs.getInt("gender"));
	//				return user;
	//
	//			}else {
	//				return null;
	//			}
	//
	//		} catch (SQLException e) {
	//			e.printStackTrace();
	//			return null;
	//		}
	//	}

	/**
	 * ユーザーデータを変更する
	 * @param user 更新に使用したいユーザーの新しいデータ
	 * @return 正常に処理が完了した場合にtrue、しなかった場合にfalseを返す
	 */
	public boolean userChangeInfo(U_User user) {
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
			PreparedStatement ps = conn
					.prepareStatement("UPDATE user SET mail=?,name=?,gender=?,area_id=? WHERE id =?;");

			ps.setString(1, user.getMail());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getGender());
			ps.setInt(4, user.getArea_id());
			ps.setInt(5, user.getUser_id());

			// UPDATE実行
			int result = ps.executeUpdate();

			// 処理の結果を確認
			if (result != 1) {
				// 正常に完了しなかった場合(正常に処理が完了するのは result = 1 の場合のみ)
				return false;
			}
			return true;

		} catch (SQLException e) {
			System.out.println("ユーザーデータ変更に失敗しました");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 退会処理（退会フラグを立てる）
	 * @param user 退会したいユーザーのデータ
	 * @return 正常に処理が完了した場合にtrue、しなかった場合にfalseを返す
	 */
	public boolean userQuit(U_User user) {
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
			PreparedStatement ps = conn.prepareStatement("UPDATE user SET active_flag= ? WHERE id = ?;");

			// 退会状態である「0」でフラグをアップデート
			ps.setInt(1, 0);
			ps.setInt(2, user.getUser_id());

			// UPDATE実行
			int result = ps.executeUpdate();

			// 処理の結果を確認
			if (result != 1) {
				// 正常に完了しなかった場合(正常に処理が完了するのは result = 1 の場合のみ)
				return false;
			}
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * パスワード変更
	 * @param user パスワード変更したいユーザーのデータ
	 * @return 正常に処理が完了した場合にtrue、しなかった場合にfalseを返す
	 */
	public boolean userChangePass(U_User user) {
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
			PreparedStatement ps = conn.prepareStatement("UPDATE user SET PASS= ? WHERE id = ?;");

			ps.setString(1, user.getPass());
			ps.setInt(2, user.getUser_id());

			// UPDATE実行
			int result = ps.executeUpdate();

			// 処理の結果を確認
			if (result != 1) {
				// 正常に完了しなかった場合(正常に処理が完了するのは result = 1 の場合のみ)
				return false;
			}
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	//パスワード再発行のためのユーザ照合
	public U_User CollationUser(U_User user) {
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
			String sql = "SELECT * FROM user WHERE mail=? AND birthday=?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getMail());

			// Localdatetime型→Timestamp型(sql)に変換
			Timestamp birthday = Timestamp.valueOf(user.getBirthday());
			ps.setTimestamp(2, birthday);

			// SELECT実行
			ResultSet result = ps.executeQuery();

			// 合致した結果を格納
			if (result.next()) {
				// 合致する結果がある場合
				user.setUser_id(result.getInt("id"));
				user.setMail(result.getString("mail"));
				user.setPass(result.getString("pass"));
				user.setName(result.getString("name"));
				user.setGender(result.getInt("gender"));
				user.setBirthday(result.getTimestamp("birthday").toLocalDateTime());
				user.setArea_id(result.getInt("area_id"));
				user.setActive(result.getInt("active_flag"));
				return user;
			}else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
