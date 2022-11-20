package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import model.GetList;
import model.U_Product;
import model.U_User;

/**
 * メイン画面関連のサーブレット
 * @author Haruka Sato
 */
@WebServlet("/U_Main")
public class U_Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メイン画面表示処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("U_Mainのget実行");

		// エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		// jspに渡す各種商品リストの型を用意
		ArrayList<ArrayList<U_Product>> myCategorisedProductLists;
		ArrayList<ArrayList<U_Product>> areaLowestPriceProductLists;

		/*
		 * 旧仕様
		 */
		//	List<U_Product> myCategorisedProductList;
		//	List<U_Product> areaLowestPriceProductList;

		List<U_Product> productList;

		//	ログインセッション確認
		HttpSession session = request.getSession();
		U_User user = (U_User) session.getAttribute("loginUser");

		//	ログイン判定
		if (user != null) {
			//	ログイン済の場合

			System.out.println("ユーザーIDは" + user.getUser_id());

			//自分が投稿したことのある品目の、品目別最安5投稿を取得
			ProductDAO myCategorisedProductDAO = new ProductDAO();
			myCategorisedProductLists = myCategorisedProductDAO.selectMyCategorizedProductLists(user.getUser_id());
			session.setAttribute("myCategorisedProductLists", myCategorisedProductLists);

			//選択中エリアで投稿されたことのある品目の、品目別最安5投稿を取得
			ProductDAO areaLowestPriceProductDAO = new ProductDAO();
			areaLowestPriceProductLists = areaLowestPriceProductDAO
					.selectAreaLowestPriceProductLists(user.getArea_id());
			session.setAttribute("areaLowestPriceProductLists", areaLowestPriceProductLists);

			/*
			 * 旧仕様
			 */
			//	自分の品目別最安値投稿を取得
			//	ProductDAO myCategorisedProductDAO = new ProductDAO();
			//	myCategorisedProductList = myCategorisedProductDAO.selectMyCategorizedProductList(user.getUser_id());
			//	session.setAttribute("myCategorisedProductList", myCategorisedProductList);
			//
			//	選択中エリアの、各品目の最安値情報を取得
			//	ProductDAO areaLowestPriceProductDAO = new ProductDAO();
			//	areaLowestPriceProductList = areaLowestPriceProductDAO.selectAreaLowestPriceProductList(user.getArea_id());
			//	session.setAttribute("areaLowestPriceProductList", areaLowestPriceProductList);

			//自分の投稿履歴を取得
			ProductDAO productDAO = new ProductDAO();
			productList = productDAO.selectProductMainSubItemByUser(user.getUser_id());
			session.setAttribute("productList", productList);

		} else {
			//	未ログインの場合

			System.out.println("セッションがありません");
			//	TODO
			//	未ログイン時の投稿リストセッションを取得してフォワード
		}

		//	メイン画面jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_Main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	      //doGetメソッドへ処理を渡す
	      doGet(request, response);
	}
}
