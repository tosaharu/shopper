package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Recipe;

public class RecipeDAO {
	public static final String URL = "jdbc:mysql://localhost:3306/shopper";
	public static final String NAME = "root";
	public static final String PASS = "root";

	public List<Recipe> selectRecipeList(Recipe recipe) {
		//@author Jasmine

		//		データベースにクーポンデータ一覧を持ってくる
		List<Recipe> recipeList = new ArrayList<Recipe>();
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL, NAME, PASS);
			//レシピの名前とimgが欲しい
			String sql = "select * from recipe where store_id=? and start_date <= CURRENT_TIMESTAMP and  end_date >= CURRENT_TIMESTAMP;";


			PreparedStatement stat = conn.prepareStatement(sql);

			//SQL文の？に使用する値を設定しSQL文を完成させる。
			stat.setInt(1, recipe.getStore_id());
			System.out.println("テスト-RecipeDAOの値："+recipe.getStore_id());

			//SQL実行
			System.out.println();
			System.out.println("RecipeDAO SQL実行前");
			ResultSet result = stat.executeQuery();
			System.out.println("RecipeDAO SQL実行後");

			//一行ずつ確認　.nextで次の行→繰り返し
			while (result.next()) {

				int id = result.getInt("id");
				String name = result.getString("name");
				String image1 = result.getString("image_1");
				String image2 = result.getString("image_2");
				String image3 = result.getString("image_3");



				recipeList.add(new Recipe(id,name,image1,image2,image3));
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

		return recipeList;
	}

}
