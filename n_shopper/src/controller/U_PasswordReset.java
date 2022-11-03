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
import model.U_User;

/**
 * Servlet implementation class U_PasswordReset
 */
@WebServlet("/U_PasswordReset")
public class U_PasswordReset extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String pass =request.getParameter("pass");
		String pass2 =request.getParameter("pass2");

		try {

			HttpSession session = request.getSession();

		U_User u_user =(U_User)session.getAttribute("cahangePass");

		u_user.setPass(pass);

		//System.out.println(pass);
		//System.out.println(pass2);


		 if (pass.equals(pass2)) {
			 int user_id = u_user.getUser_id();
			// String mail= u_user.getMail();
			// Date birthday = (Date) u_user.getBirthday();
			UserDAO dao = new UserDAO();
			U_User result = dao.UpdatePass(pass,user_id);


			 // 完了画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Login");
			dispatcher.forward(request, response);

		}else {

			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMessage", "失敗しました");
			//再度エラー文付き入力画面
			RequestDispatcher dispatcher = request.getRequestDispatcher("/u_passwordReset.jsp");
			dispatcher.forward(request, response);

		}
		}catch (NumberFormatException e) {
			 e.printStackTrace();
		 }


	}
}