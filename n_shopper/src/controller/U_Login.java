package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dao.UserDAO;
import model.GetList;
import model.U_User;

/**
 * ログイン画面関連のサーブレット
 * @author Haruka Sato
 */
@WebServlet("/U_Login")
public class U_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ログイン画面表示処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// エリアデータを取得してリクエストスコープに保存
		GetList.AreaPrefectureRegion(request);

		// ログイン画面jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_Login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * ログイン処理
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		//エリアデータを取得してリクエストスコープに保存
		GetList.AreaPrefectureRegion(request);

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String originalPass = request.getParameter("pass");

		// 入力されたメールアドレスをもとにユーザーデータを取得
		UserDAO dao = new UserDAO();
		U_User loginUser = dao.getUserByMail(mail);

		// ユーザーデータが存在するかチェック）
		if (loginUser != null) {
			// ログイン処理
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			Boolean isLogin = encoder.matches(originalPass, loginUser.getPass());
			System.out.println(isLogin);
			// ログイン判定
			if (isLogin && loginUser.getActive() == 1) {
				// ログイン成功時の処理

				// ユーザー情報をセッションスコープに保存
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);

				// メイン画面サーブレットにフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Main");
				dispatcher.forward(request, response);
			}

		}
		// ログイン失敗時の処理

		// エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMessage", "メールまたはパスワードが間違っています");

		//ログイン画面jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_Login.jsp");
		dispatcher.forward(request, response);
	}

}
