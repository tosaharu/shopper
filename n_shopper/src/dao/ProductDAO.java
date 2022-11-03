package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.U_Product;

public class ProductDAO {

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shopper";
	private final String DB_USER = "root";
	private final String DB_PASS = "root";

	public boolean create(U_Product u_product) {
		//util date ⇒ sql date
		long timeInMilliSeconds = u_product.getDate().getTime();
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

		//データベースに接続する。
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SQL文の準備:ProductIDは自動連番なので気にしないでいい
			String sql = "INSERT INTO product (user_id,item_id,detail,store_id,amount,price,date,discount,comment) "
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			//				+ "VALUES(?,?,\'?\',?,?,?,\'?\',?,\'?\')";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			System.out.println("\n*****************\nDAO内の値の取り方");
			System.out.println("getUser_id:" + u_product.getUser_id() + "◆\n+getItem_id:" + u_product.getItem_id()
					+ "◆\ngetItemDetail:" + u_product.getItemDetail() + "◆\ngetStore_id:" + u_product.getStore_id()
					+ "◆\ngetAmount:" + u_product.getAmount() +
					"◆\ngetPrice:" + u_product.getPrice() + "◆\ngetDate:" + u_product.getDate() + "◆\ngetDiscount:"
					+ u_product.getDiscount() + "◆\ngetComment:" + u_product.getComment());

			//INSERT文の？に使用する値を設定しSQL文を完成させる。
			pStmt.setInt(1, u_product.getUser_id());
			pStmt.setInt(2, u_product.getItem_id());
			pStmt.setString(3, u_product.getItemDetail());
			pStmt.setInt(4, u_product.getStore_id());
			pStmt.setInt(5, u_product.getAmount());
			pStmt.setInt(6, u_product.getPrice());
			pStmt.setDate(7, sqlDate);
			pStmt.setInt(8, u_product.getDiscount());
			pStmt.setString(9, u_product.getComment());

			//INSERT文を実行する（resultには追加された行数が代入される。）
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @param userId
	 * @return
	 */
	public List<U_Product> selectProductMainSubItemByUser(int userId){
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

			}catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}

			List<U_Product> productList = new ArrayList<>();

			//データベースに接続
			try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

				//SQL文の準備
				String sql = "SELECT * from product AS a \r\n"
						+ "LEFT JOIN item AS b \r\n"
						+ "ON a.item_id = b.id \r\n"
						+ "LEFT JOIN sub_item AS c \r\n"
						+ "ON b.sub_item_id = c.id \r\n"
						+ "LEFT JOIN main_item AS d \r\n"
						+ "ON c.main_item_id = d.id \r\n"
						+ "LEFT JOIN store AS e \r\n"
						+ "ON a.store_id = e.id \r\n"
						+ "LEFT JOIN user AS f \r\n"
						+ "ON a.user_id = f.id \r\n"
						+ "WHERE a.user_id = ?";

				PreparedStatement pStmt = conn.prepareStatement(sql);

				//INSERT文の？に使用する値を設定しSQL文を完成
				pStmt.setInt(1,userId);

				//INSERT文を実行する（resultには追加された行数が代入される。）
				ResultSet result =pStmt.executeQuery();
				System.out.println("SQLの実行に成功");

				while (result.next()) {
					int product_id = result.getInt("a.id");
					int user_id = result.getInt("a.user_id");
					String user_name = result.getString("f.name");
					int item_id = result.getInt("a.item_id");
					String item_name = result.getString("b.name");
					String itemDetail = result.getString("a.detail");
					int store_id = result.getInt("a.store_id");
					String store_name = result.getString("e.name");
					int amount = result.getInt("a.amount");
					int price = result.getInt("a.price");
					Date date = result.getDate("a.date");
					int discount = result.getInt("a.discount");
					String comment = result.getString("a.comment");
					int mainItem_id = result.getInt("d.id");
					String mainItem_name = result.getString("d.name");
					int subItem_id = result.getInt("c.id");
					String subItem_name = result.getString("c.name");

					U_Product product = new U_Product(product_id,user_id,user_name,item_id,item_name,itemDetail,store_id,store_name,amount,price,date,discount,comment,mainItem_id,mainItem_name,subItem_id,subItem_name);
					productList.add(product);
				}
				System.out.println("リストのリターンに成功");
				System.out.println(productList);
				return productList;
			}
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("DB接続に失敗");
				return null;
			}
		}

	public List<U_Product> selectMyCategorizedProductList(int userId){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<U_Product> productList = new ArrayList<>();

		//データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SQL文の準備
			String sql = "SELECT * FROM shopper.product AS a\r\n"
					+ "LEFT JOIN shopper.item AS b\r\n"
					+ "ON a.item_id = b.id\r\n"
					+ "LEFT JOIN shopper.sub_item AS c\r\n"
					+ "ON b.sub_item_id = c.id\r\n"
					+ "LEFT JOIN shopper.main_item AS d\r\n"
					+ "ON c.main_item_id = d.id\r\n"
					+ "LEFT JOIN shopper.store AS e\r\n"
					+ "ON a.store_id = e.id\r\n"
					+ "LEFT JOIN shopper.user AS f\r\n"
					+ "ON a.user_id = f.id\r\n"
					+ "JOIN (\r\n"
					+ "	SELECT item_id,MIN(price) AS min_price\r\n"
					+ "FROM shopper.product\r\n"
					+ "WHERE user_id = ?\r\n"
					+ "GROUP BY item_id) AS g\r\n"
					+ "ON a.item_id = g.item_id\r\n"
					+ "AND a.price = g.min_price\r\n"
					+ "GROUP BY A.item_id\r\n"
					+ "ORDER BY date";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文の？（ユーザーID）に使用する値を設定しSQL文を完成
			pStmt.setInt(1,userId);

			//INSERT文を実行する（resultには追加された行数が代入される。）
			ResultSet result =pStmt.executeQuery();
			System.out.println("SQLの実行に成功");

			while (result.next()) {
				int product_id = result.getInt("a.id");
				int user_id = result.getInt("a.user_id");
				String user_name = result.getString("f.name");
				int item_id = result.getInt("a.item_id");
				String item_name = result.getString("b.name");
				String itemDetail = result.getString("a.detail");
				int store_id = result.getInt("a.store_id");
				String store_name = result.getString("e.name");
				int amount = result.getInt("a.amount");
				int price = result.getInt("a.price");
				Date date = result.getDate("a.date");
				int discount = result.getInt("a.discount");
				String comment = result.getString("a.comment");
				int mainItem_id = result.getInt("d.id");
				String mainItem_name = result.getString("d.name");
				int subItem_id = result.getInt("c.id");
				String subItem_name = result.getString("c.name");

				U_Product product = new U_Product(product_id,user_id,user_name,item_id,item_name,itemDetail,store_id,store_name,amount,price,date,discount,comment,mainItem_id,mainItem_name,subItem_id,subItem_name);
				productList.add(product);
			}
			System.out.println("リストのリターンに成功");
			System.out.println(productList);
			return productList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続に失敗");
			return null;
		}
	}

	public List<U_Product> selectAreaLowestPriceProductList(int areaId){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		}catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		List<U_Product> productList = new ArrayList<>();

		//データベースに接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SQL文の準備
			String sql = "SELECT * FROM shopper.product AS a\r\n"
					+ "LEFT JOIN shopper.item AS b\r\n"
					+ "ON a.item_id = b.id\r\n"
					+ "LEFT JOIN shopper.sub_item AS c\r\n"
					+ "ON b.sub_item_id = c.id\r\n"
					+ "LEFT JOIN shopper.main_item AS d\r\n"
					+ "ON c.main_item_id = d.id\r\n"
					+ "LEFT JOIN shopper.store AS e\r\n"
					+ "ON a.store_id = e.id\r\n"
					+ "LEFT JOIN shopper.user AS f\r\n"
					+ "ON a.user_id = f.id\r\n"
					+ "JOIN (\r\n"
					+ "	select item_id,MIN(price) AS min_price\r\n"
					+ "    FROM shopper.product\r\n"
					+ "    GROUP BY item_id) AS g\r\n"
					+ "ON a.item_id = g.item_id\r\n"
					+ "AND a.price = g.min_price\r\n"
					+ "WHERE store_id IN (\r\n"
					+ "	SELECT id\r\n"
					+ "    FROM shopper.store\r\n"
					+ "    WHERE area_id = ? )\r\n"
					+ "GROUP BY a.item_id\r\n"
					+ "ORDER BY date";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文の？（ユーザーID）に使用する値を設定しSQL文を完成
			pStmt.setInt(1,areaId);

			//INSERT文を実行する（resultには追加された行数が代入される。）
			ResultSet result =pStmt.executeQuery();
			System.out.println("SQLの実行に成功");

			while (result.next()) {
				int product_id = result.getInt("a.id");
				int user_id = result.getInt("a.user_id");
				String user_name = result.getString("f.name");
				int item_id = result.getInt("a.item_id");
				String item_name = result.getString("b.name");
				String itemDetail = result.getString("a.detail");
				int store_id = result.getInt("a.store_id");
				String store_name = result.getString("e.name");
				int amount = result.getInt("a.amount");
				int price = result.getInt("a.price");
				Date date = result.getDate("a.date");
				int discount = result.getInt("a.discount");
				String comment = result.getString("a.comment");
				int mainItem_id = result.getInt("d.id");
				String mainItem_name = result.getString("d.name");
				int subItem_id = result.getInt("c.id");
				String subItem_name = result.getString("c.name");

				U_Product product = new U_Product(product_id,user_id,user_name,item_id,item_name,itemDetail,store_id,store_name,amount,price,date,discount,comment,mainItem_id,mainItem_name,subItem_id,subItem_name);
				productList.add(product);
			}
			System.out.println("リストのリターンに成功");
			System.out.println(productList);
			return productList;
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DB接続に失敗");
			return null;
		}
	}

}
