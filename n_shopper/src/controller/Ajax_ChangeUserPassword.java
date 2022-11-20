package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dao.UserDAO;
import model.U_User;

/**
 * ajax通信のpostに対し、パスワード変更をするサーブレット
 * @author Haruka Sato
 */
@WebServlet("/Ajax_ChangeUserPassword")
public class Ajax_ChangeUserPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メールアドレスの存在確認処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//フォームのリクエストパラメータを取得
		String originalPass = request.getParameter("password");
		String newPass = request.getParameter("newpassword");

		//	ログインセッション確認
		HttpSession session = request.getSession();
		U_User loginUser = (U_User) session.getAttribute("loginUser");

		// パスワード照合
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Boolean passwordMatch = encoder.matches(originalPass, loginUser.getPass());
		System.out.println(passwordMatch);

		if(passwordMatch) {
			// セッションのパスワードを更新
			loginUser.setPass(newPass);

			// 更新したセッションでDB更新
			UserDAO userDAO = new UserDAO();
			userDAO.userChangePass(loginUser);

			//成功時のレスポンス
			response.getWriter().append("1");

		}else {
			//失敗時のレスポンス
			response.getWriter().append("0");

		}
	}

}
