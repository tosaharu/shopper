package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GetList;

/**
 * セッション切れの際のエラー画面表示に関するサーブレット
 * @author Haruka Sato
 */
@WebServlet("/U_SessionExpired")
public class U_SessionExpired extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 画面表示処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エリアデータを取得してリクエストスコープに保存
		GetList.AreaPrefectureRegion(request);

		// エラーページにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_SessionExpired.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 退会画面等、post通信時にセッション切れ処理に以降する際のための記載
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get処理に移行
		doGet(request, response);
	}

}
