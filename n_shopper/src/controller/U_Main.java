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
 * Servlet implementation class U_UserMain
 */
@WebServlet("/U_Main")
public class U_Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		//		商品リストの型を用意

//		List<U_Product> myCategorisedProductList;
//		List<U_Product> areaLowestPriceProductList;

		ArrayList<ArrayList<U_Product>> myCategorisedProductLists;
		ArrayList<ArrayList<U_Product>> areaLowestPriceProductLists;
		List<U_Product> productList;

		//		ログイン済みかどうかを判定
		HttpSession session = request.getSession();
		U_User user = (U_User) session.getAttribute("loginUser");

		//		ログイン済の場合
		if (user != null) {
			System.out.println("ユーザーIDは" + user.getUser_id());

//			//★★★旧仕様★★★
//
//			//自分の品目別最安値投稿を取得
//			ProductDAO myCategorisedProductDAO = new ProductDAO();
//			myCategorisedProductList = myCategorisedProductDAO.selectMyCategorizedProductList(user.getUser_id());
//			session.setAttribute("myCategorisedProductList", myCategorisedProductList);

			//自分の品目別最安5投稿を取得
			ProductDAO myCategorisedProductDAO = new ProductDAO();
			myCategorisedProductLists = myCategorisedProductDAO.selectMyCategorizedProductLists(user.getUser_id());
			session.setAttribute("myCategorisedProductLists", myCategorisedProductLists);

//			//★★★旧仕様★★★
//
//			//選択中エリアの、各品目の最安値情報を取得
//			ProductDAO areaLowestPriceProductDAO = new ProductDAO();
//			areaLowestPriceProductList = areaLowestPriceProductDAO.selectAreaLowestPriceProductList(user.getArea_id());
//			session.setAttribute("areaLowestPriceProductList", areaLowestPriceProductList);

			//選択中エリアの、各品目の最安5投稿を取得
			ProductDAO areaLowestPriceProductDAO = new ProductDAO();
			areaLowestPriceProductLists = areaLowestPriceProductDAO.selectAreaLowestPriceProductLists(user.getArea_id());
			session.setAttribute("areaLowestPriceProductLists", areaLowestPriceProductLists);

			//自分の投稿履歴を取得
			ProductDAO productDAO = new ProductDAO();
			productList = productDAO.selectProductMainSubItemByUser(user.getUser_id());
			session.setAttribute("productList", productList);


		} else {
			//			未ログインの場合
			System.out.println("セッションがありません");
			//			TODO
			//			未ログイン時の投稿リストセッションを取得してフォワード
		}

		//		フォームにフォワード
		//		RequestDispatcher dispatcher = request.getRequestDispatcher("/test.jsp");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/u_Main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
