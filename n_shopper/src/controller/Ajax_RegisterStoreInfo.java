package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDAO;
import model.Store;

/**
 * ajax通信のpostに対し、ワンタイムパスをチェックするサーブレット
 * @author Haruka Sato
 */
@WebServlet("/Ajax_RegisterStoreInfo")
public class Ajax_RegisterStoreInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ワンタイムパスをチェックする処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// フォームのリクエストパラメータを取得
		String storeName = request.getParameter("store_name");
		int storeArea = Integer.parseInt(request.getParameter("store_area"));
		String storeAddress = request.getParameter("store_address");
		String storeStartHour = request.getParameter("store_start_hour");
		String storeEndHour = request.getParameter("store_end_hour");
		String storeURL = request.getParameter("store_URL");
		String storeTel = request.getParameter("store_tel");
		String storePayment = request.getParameter("store_payment");
		String storeFacility = request.getParameter("store_facility");
		String storeService = request.getParameter("store_service");

		// store型のインスタンスを生成し、情報を格納
		Store store=new Store();

		store.setStoreName(storeName);
		store.setArea_id(storeArea);
		store.setStartHour(storeStartHour);
		store.setEndHour(storeEndHour);
		store.setSubPostcode(storeAddress);
		store.setHp(storeURL);
		store.setTel_s(storeTel);
		store.setPayment(storePayment);
		store.setInformation(storeFacility);
		store.setService(storeService);

		//DB側でnullが許されず、ユーザが入力しない値をセットする

		store.setCorporationFlag(0);//店舗側の登録ないので固定で0
		store.setOpenFlag(1);//開店してるはずなので1

		StoreDAO storeDAO = new StoreDAO();
		int storeId = storeDAO.create(store);

		if(storeId > 0) {
			// 処理が成功し、storeIdが返ってきている
				String id = String.valueOf(storeId);
				response.getWriter().append(id);
		}else {
			// 処理が失敗し、storeIdが返ってきていない
			response.getWriter().append("0");

		}
	}

}
