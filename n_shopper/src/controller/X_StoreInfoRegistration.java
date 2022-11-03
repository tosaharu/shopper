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
 * Servlet implementation class StoreInfoRegistration
 */
@WebServlet("/X_StoreInfoRegistration")
public class X_StoreInfoRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//商品投稿画面

		/*TODO：飛んできたページの名前（数字をセッションに入れて持ち出す）をifで判定して戻り先を特定する。
		商品投稿画面	StoreInfoRegistration	doGet	店舗情報新規登録画面
		店舗情報新規登録画面	StoreInfoRegistration	doPost	商品投稿画面

		店舗情報画面	StoreInfoEdit	doGet	店舗情報編集画面
		店舗情報編集画面	StoreInfoEdit	doPost	店舗情報画面

		各所	StoreInfo	doGet	店舗情報画面
		*/

		/*
		//		ログイン済みかどうかを判定
		HttpSession session = request.getSession();
		U_User user = (U_User) session.getAttribute("loginUser");


		//		ログイン済の場合
		if (user != null) {
			System.out.println("ユーザーIDは" + user.getUser_id());
		} else {
			//	未ログインの場合
			System.out.println("セッションがありません");
			U_User guest = new U_User(0, "ゲスト");

			//確認用
			System.out.println("ユーザーIDは" + guest.getUser_id()+"◆"+guest.getName()+"さん");

			//TODO このまま使わなければ消去
			//未ログイン状況をリクエストパラメーターへ送信
		//			request.setAttribute("guest", guest);

			//テストの為
			RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Login");
			dispatcher.forward(request, response);
			return;//事故防止のreturn;

		}
		*/

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		//大中小品目、店舗名のArrayListを取り出す。
		GetList.MainSubItemStore(request);

		//登録はDATE:Util insertの直前にSQLにする。

		System.out.println("★★★現在地★★★：X_StoreInfoRegistration :doGet");
		//商品登録画面へ移動する、
		System.out.println("********店舗情報・新規登録画面jspへforwardする");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/x_RegisterStore.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("★★★現在地★★★：X_StoreInfoRegistration :doPost");

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		//パラメーターを受け取る
		request.setCharacterEncoding("UTF-8");

		String store_name = request.getParameter("store");
		String area_id_s = request.getParameter("area");
		String address = request.getParameter("address");
		String start_hours = request.getParameter("start_hours");
		String end_hour = request.getParameter("end_hour");
		String hp = request.getParameter("hp");
		String tel_s = request.getParameter("tel");
		String payment = request.getParameter("payment");
		String info = request.getParameter("info");
		String service = request.getParameter("service");


		System.out.println(
				"◆store_name " + store_name + "\n◆area_id_s " + area_id_s + "\n◆address " + address + "\n◆start_hours "
						+ start_hours + "\n◆end_hour " + end_hour + "\n◆hp " + hp + "\n◆tel " + tel_s + "\n◆payment "
						+ payment + "\n◆info "+info + "\n◆service "+service);

		Store store=new Store();

		store.setStoreName(store_name);
		store.setArea_id_s(area_id_s);
		store.setSubPostcode(address);
		String business_hours =start_hours+"から"+end_hour+"まで";
		store.setBusinessHours(business_hours);
		store.setHp(hp);
		store.setTel_s(tel_s);
		store.setPayment(payment);
		store.setInformation(info);
		store.setService(service);

		request.setAttribute("store", store);

		try {
			int area_id = Integer.parseInt(area_id_s);
			int tel = Integer.parseInt(tel_s);

			store.setArea_id(area_id);
			store.setTel(tel);

			//ユーザが入力しない値をセットする。
			store.setCorporationFlag(0);//店舗側の登録ないので固定で0
			store.setOpenFlag(1);//開店してるはずなので1

			StoreDAO storeDAO=new StoreDAO();
			boolean result = storeDAO.create(store);
			if (result) {//DBへのinsertが成功した場合

				System.out.println("★★★INSERT成功★★★");
				System.out.println();
			}else {
				System.out.println("★★★INSERT失敗★★★");

				//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterItem.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				return;//事故防止のreturn;
			}



		}catch (NumberFormatException e) {
			// TODO: handle exception
			System.out.println("型変換失敗");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/x_RegisterStore.jsp");
			dispatcher.forward(request, response);
			return;
		}



		//		pStmt.setInt(10, store.getCorporationFlag());
		//		pStmt.setInt(11, store.getOpenFlag());

		//移動先を判断する為の値。セッションに入れる。
		/**TODO ★★★　店舗画面から編集画面へ行くみたいなURLの保持がある。それを参考に★
		 * 店舗情報登録画面のページ：0
		 * 他のページは!=0
		 */
		int judgeWhereFrom = 0;

		if (judgeWhereFrom == 1) {
			//店舗情報画面へ移動
			System.out.println("********店舗情報メイン画面jspへforwardする");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/u_StoreInfo.jsp");
			dispatcher.forward(request, response);
			return;
		} else {
			//商品登録画面へ移動する、
			System.out.println("********商品登録画面jspへforwardする");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterItem.jsp");
			dispatcher.forward(request, response);
			return;
		}

	}

}
