package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		String mail = request.getParameter("mail");

		// メールアドレスがDBに存在するかチェック
		UserDAO userDAO = new UserDAO();
		Boolean checkMail = userDAO.checkMail(mail);

		// DBチェックの結果がtrueかfalseで分岐
		if(checkMail) {
			// trueの場合
			response.getWriter().append("1");
		}else {
			// falseの場合
			response.getWriter().append("0");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
