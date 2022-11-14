//＜ログイン処理　サーブレット＞

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

import model.GetList;
import model.U_LoginLogic;
import model.U_User;

@WebServlet("/U_Login")
public class U_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		//		フォームにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/u_Login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String originalPass = request.getParameter("pass");
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    String pass = encoder.encode(originalPass);

		// U_Userインスタンス（ユーザー情報）の生成
		U_User user = new U_User(mail, pass);

		// ログイン処理
		U_LoginLogic loginLogic = new U_LoginLogic();
		U_User isLogin = loginLogic.execute(user);
		System.out.println(isLogin);

		// ログイン成功時の処理(-1のときは失敗）
		if (isLogin != null) {

			// ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);

			// メイン画面（サーブレット）にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Main");
			dispatcher.forward(request, response);

		} else {
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMessage", "ログインできません");

			//再度ログイン画面に遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("/u_Login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
