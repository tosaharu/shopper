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

import dao.CouponListDAO;
import dao.FlyerDAO;
import dao.RecipeDAO;
import dao.StoreDAO;
import model.CouponList;
import model.Flyer;
import model.GetList;
import model.Recipe;
import model.Store;

/**
 * @author Jasmine
 */
@WebServlet("/U_StoreInfo")
public class U_StoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("★★★現在地★★★：U_StoreInfo :doGet");
		//いろんなところからやってきて店舗情報画面へ行ける。

		//HttpSessionインスタンスの取得
		//HttpSession session = request.getSession();

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);


		//TODO 店舗情報画面で必要な情報をDBから取り出す。
		//画面の構成の整理。

		//店舗の情報：

		//店舗のidをリクエストパラメーターで取得する必要がある。
		int store_id = Integer.parseInt(request.getParameter("id"));
		//受け取った店舗のidのリクエストパラメーターをリクエストスコープに保存する。
		request.setAttribute("store_id", store_id);

		//店舗の情報を取り出す
		//storeの情報を取り出す。
		Store store = new Store();
		StoreDAO store_DAO = new StoreDAO();
		store.setStore_id(store_id);//テスト用
		store=store_DAO.findTheStore(store);
		request.setAttribute("store", store);

		System.out.println("Storeに入る値");
		System.out.println(store.getStoreName());


//		//storeの更新 の情報を取り出す。
//		StoreUpdate store_update=new StoreUpdate();
//		StoreUpdateDAO storeUpdate_DAO=new StoreUpdateDAO();
//		store_update.setStoreUpdate_id(2);
//		store_update=storeUpdate_DAO.findTheUpdate(store_update);
//		request.setAttribute("store_update", store_update);


		//その店舗のエリアの最安値 の情報を取り出す。

		//その店舗が公開しているクーポン の情報を取り出す。増やす

		System.out.println("その店舗が公開しているクーポン の情報を取り出す。");

		CouponList coupon=new CouponList();
		CouponListDAO coupon_DAO=new CouponListDAO();
		List<CouponList> couponList=new ArrayList<>();

		coupon.setStore_id(store_id);//テスト用
		System.out.println("テスト："+coupon.getStore_id());//テスト用

		couponList=coupon_DAO.selectCouponList(coupon);

// 		テスト用の分 Nullのテストの時は邪魔
//		System.out.println(couponList.get(0).getName());
//		System.out.println(couponList.get(0).getEnd_date());
		System.out.println();

		request.setAttribute("couponList", couponList);


		//その店舗が公開しているチラシ の情報を取り出す。 何か作ろうかな...
		System.out.println("その店舗が公開しているチラシ の情報を取り出す。");
		Flyer flyer =new Flyer();
		FlyerDAO flyer_DAO=new FlyerDAO();
		List<Flyer> flyerList=new ArrayList<>();

		flyer.setStore_id(store_id);
		flyerList=flyer_DAO.selectFlyerList(flyer);


//		System.out.println("テスト："+flyer.getStore_id());//テスト用
		request.setAttribute("flyerList", flyerList);
//		System.out.println("テスト-チラシの値テスト取得"+flyerList.get(0).getId());

		//その店舗が公開しているレシピ の情報を取り出す。
		System.out.println("その店舗が公開しているレシピ の情報を取り出す。");
		Recipe recipe=new Recipe();
		RecipeDAO recipe_DAO=new RecipeDAO();
		List<Recipe> recipeList=new ArrayList<>();


		recipe.setStore_id(store_id);
		recipeList=recipe_DAO.selectRecipeList(recipe);
		//System.out.println(recipe.getStore_id());

		request.setAttribute("recipeList", recipeList);


		//店舗情報画面：/u_StoreInfo.jsp へ遷移する。

		System.out.println("店舗情報画面へ移動する。");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/u_StoreInfo.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
