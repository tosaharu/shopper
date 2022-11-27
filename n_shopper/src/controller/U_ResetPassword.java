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

/**
 * パスワード紛失時のパスワードリセット処理に関するサーブレット
 * @author Haruka Sato
 *
 */
@WebServlet("/U_ResetPassword")
public class U_ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * パスワードリセット画面を表示する
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エリアデータを取得してビーンズに入れる
		GetList.AreaPrefectureRegion(request);

		//		フォームにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_ResetPassword.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * パスワードリセット画面から送られてきたパスワードとセッションに保存したメールアドレスを、DBに再設定する
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エリアデータを取得してビーンズに入れる
		GetList.AreaPrefectureRegion(request);

		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータ取得
		String originalPass = request.getParameter("newpassword");

		// パスワードのハッシュ化
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pass = encoder.encode(originalPass);
		System.out.println(pass);

		// セッションからメールアドレスを取得
		HttpSession session = request.getSession();
		String mail = (String) session.getAttribute("resetPassMail");
		System.out.println(mail);

		if(mail != null) {
			UserDAO userDAO = new UserDAO();
			boolean result = userDAO.UpdatePass(pass, mail);
			System.out.println(result);

			if(result) {
				// ログイン画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Login");
				dispatcher.forward(request, response);
			}else {
				// エラー画面へフォワード
				// （少ないエラーだと思うのいまは仮でLOGINへ）
				RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Login");
				dispatcher.forward(request, response);
			}
		}else {
			// セッション切れ画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/U_SessionExpired");
			dispatcher.forward(request, response);
		}
	}
}
