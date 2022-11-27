package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CreateOneTimePass;
import model.GetList;
import model.SendMail;

/**
 * パスワード紛失時の再設定認証処理に関するサーブレット
 * @author Haruka Sato
 */
@WebServlet("/U_ResetPasswordAuth")
public class U_ResetPasswordAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 画面表示処理
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エリアデータを取得してビーンズに入れる
		GetList.AreaPrefectureRegion(request);

		//		フォームにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_ResetPasswordAuth1.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 再設定認証画面から送られてきたメールアドレス宛にパスワードを送信しつつ、パスワード入力フォームへ遷移する
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//エリアデータを取得してビーンズに入れる
		GetList.AreaPrefectureRegion(request);

		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメータ取得
		String mail = request.getParameter("email");

		// ワンタイムパスワードを発行してセッションとメールに仕込む
		String pass = CreateOneTimePass.create();

		HttpSession session = request.getSession();
		session.setAttribute("oneTimePass", pass);

		// 再登録のためメールもセッションに保管
		session.setAttribute("resetPassMail", mail);

		// メールアドレス宛にwebmasterからメールを送る処理
		SendMail.sendOneTimePass(mail, pass);

		// ワンタイムパスワード入力フォームへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_ResetPasswordAuth2.jsp");
		dispatcher.forward(request, response);
	}
}
