package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.U_User;

/**
 * ajax通信のgetで送られてきたメールアドレスが、DB内に存在するかを確認して返すサーブレット
 * @author Haruka Sato
 */
@WebServlet("/Ajax_CheckUserMail")
public class Ajax_CheckUserMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メールアドレスの存在確認処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		String mail = request.getParameter("mail");

		// メールアドレスがDBに存在するかチェック
		UserDAO userDAO = new UserDAO();
		int userId = userDAO.checkMail(mail);

		// DBチェックの結果で分岐（userIdは、メールアドレスが存在すれば対応するユーザーIDを、存在しなければ0を返す）
		if (userId > 0) {
			// メールアドレスが存在する場合

			//	ログインセッション確認
			HttpSession session = request.getSession();
			U_User user = (U_User) session.getAttribute("loginUser");

			//	ログイン判定
			if (user != null) {
				// ログインしている場合

				if (userId == user.getUser_id()) {
					// セッションIDと同じ場合
					response.getWriter().append("2");
				} else {
					// セッションIDと異なる場合
					response.getWriter().append("1");
				}
			}else {
				// ログインしていない場合
				response.getWriter().append("1");

			}
		} else {
			// メールアドレスが存在しない場合
			response.getWriter().append("0");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
