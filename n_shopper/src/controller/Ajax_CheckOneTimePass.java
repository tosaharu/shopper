package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ajax通信のpostに対し、ワンタイムパスをチェックするサーブレット
 * @author Haruka Sato
 */
@WebServlet("/Ajax_CheckOneTimePass")
public class Ajax_CheckOneTimePass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * ワンタイムパスをチェックする処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// フォームのリクエストパラメータを取得
		String inputPass = request.getParameter("oneTimePass");

		// パスワードを保存したセッションを取得
		HttpSession session = request.getSession();
		String correctPass = (String) session.getAttribute("oneTimePass");
		System.out.println(inputPass);
		System.out.println(correctPass);

		//
		if(correctPass != null) {
			if(inputPass.equals(correctPass)) {
				// パスワードが正しい場合のレスポンス
				response.getWriter().append("2");
			}else {
				// パスワードが正しくない場合のレスポンス
				response.getWriter().append("1");
			}
		}else {
			// セッションが切れている場合のレスポンス
			response.getWriter().append("0");

		}
	}

}
