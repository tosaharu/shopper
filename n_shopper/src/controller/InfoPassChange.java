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
 * Servlet implementation class InfoPassChange
 */
@WebServlet("/InfoPassChange")
public class InfoPassChange extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("test");
		
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		int id =  Integer.parseInt(request.getParameter("id"));
		String pass = request.getParameter("pass");//現在のパスワード確認
		String oldpass = request.getParameter("oldpass");//現在の入力パスワード確認
		String pass1 =request.getParameter("pass1");//再入力側pass
		String pass2 =request.getParameter("pass2");//再入力側pass確認
//		String mail = request.getParameter("mail");
	System.out.println(pass+oldpass+pass1+pass2);
	try {
			HttpSession session = request.getSession();
			U_User user = new U_User();
			user.setUser_id(id);
//		U_User user =(U_User)session.getAttribute("cahangePass");
//		user.setPass(pass);
		
		//ログインユーザーのpassと入力値のpassが一致していたら、次の処理へ,,,
		if(pass.equals(oldpass)) {
			System.out.println(pass);
			System.out.println(oldpass);//値をチェック
			System.out.println("パスワードの一致確認");
			
		}else {
			System.out.println("パスワードの不一致");
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMessage", "現在のパスワードと一致しません。");
			//再度エラー文付き入力画面
			RequestDispatcher dispatcher = request.getRequestDispatcher("/infoPass.jsp");
			dispatcher.forward(request, response);
		}
		
		user.setPass(pass1);//Updateしたい値

		System.out.println(pass1);
		 System.out.println(pass2);

		 System.out.println("b");

		 if (pass1.equals(pass2)) {
			 System.out.println("c");

			 System.out.println("d");
			 user.setPass(pass1);
			 UserDAO.userNewPass(user);


			 System.out.println("e");
			 // 完了画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/TestShima.jsp");
			dispatcher.forward(request, response);

		}else {

			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMessage", "失敗しました");
			//再度エラー文付き入力画面
			RequestDispatcher dispatcher = request.getRequestDispatcher("/infoPass.jsp");
			dispatcher.forward(request, response);

		}
	}catch (NumberFormatException e) {
		 e.printStackTrace();
	 }


	}

}
