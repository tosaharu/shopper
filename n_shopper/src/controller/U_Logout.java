package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetList;

/**
 * ログアウト処理に関するサーブレット
 * @author Haruka Sato
 */
@WebServlet("/U_Logout")
public class U_Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ログアウト処理をして完了画面へ遷移する
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		// セッションスコープを破棄
		HttpSession session = request.getSession();
		session.invalidate();

		// ログアウト画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_Logout.jsp");
		dispatcher.forward(request, response);

	}
}
