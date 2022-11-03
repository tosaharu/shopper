package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDAO;
import model.GetList;
import model.Store;

/**
 *@author Jasmine マジ
 */
@WebServlet("/X_StoreInfoEdit")
public class X_StoreInfoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("★★★現在地★★★：X_StoreInfoEdit :doGet");

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		//店舗のidをリクエストパラメーターで取得する必要がある。
		int store_id = Integer.parseInt(request.getParameter("id"));
		Store store = new Store();
		StoreDAO store_DAO = new StoreDAO();
		store.setStore_id(store_id);//テスト用
		store=store_DAO.findTheStore(store);

		System.out.println("リクエストパラメーターを取得：店舗id after");
		//受け取った店舗のidのリクエストパラメーターをリクエストスコープに保存する。
		request.setAttribute("store_id", store_id);
		request.setAttribute("store", store);

		//店舗情報画面からやってきて店舗情報編集画面へ送る
		System.out.println("★★★現在地★★★：X_StoreInfoEdit :doGet");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/x_EditStore.jsp");
		dispatcher.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		//TODO:店舗のidをリクエストパラメーターで取得する必要がある。-hiddenでいれるかな？
		int store_id = Integer.parseInt(request.getParameter("id"));

		//リクエストパラメータ:本来であればStrimg型
				String name_tx = request.getParameter("name");
				String address_tx = request.getParameter("address");
				//リクエストパラメータ:本来であればint型 変換が必要
				String business_h_tx = request.getParameter("business_h");
				String price_tx = request.getParameter("price_text");
				String amount_tx = request.getParameter("amount_text");
				String discount_tx = request.getParameter("discount_text");





		//店舗情報編集画面からやってきて店舗情報画面へ送る
		System.out.println("★★★現在地★★★：X_StoreInfoEdit :doPost");
		System.out.println("店舗情報画面へ移動する。");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/shopper/u_StoreInfo.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
