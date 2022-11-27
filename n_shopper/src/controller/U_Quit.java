package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.GetList;
import model.U_User;

/**
 * 退会処理に関するサーブレット
 * @author Haruka Sato
 */
@WebServlet("/U_Quit")
public class U_Quit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 画面表示処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// エリアデータを取得してリクエストスコープに保存
		GetList.AreaPrefectureRegion(request);

		// ログイン画面jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_QuitConfirm.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * 退会画面で退会ボタンを押下した際の処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// エリアデータを取得してリクエストスコープに保存
		GetList.AreaPrefectureRegion(request);

		// ログインセッションの取得
		HttpSession session = request.getSession();
		U_User loginUser = (U_User) session.getAttribute("loginUser");

		// ログインセッション切れの際の処理
		if(loginUser == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/U_SessionExpired");
			dispatcher.forward(request, response);
			return;

		}

		// DBの退会フラグを更新
		UserDAO userDAO = new UserDAO();
		userDAO.userQuit(loginUser);

		// 退会時に問題が発生した際の処理
		//TODO

		// セッションスコープを破棄
		session.invalidate();

		// 退会完了画面jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_QuitComplete.jsp");
		dispatcher.forward(request, response);

			}
		}

