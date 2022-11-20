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
import model.GetList;
import model.U_User;

/**
 * Servlet implementation class U_Changelnfo
 */
@WebServlet("/U_ChangeInfo")
public class U_ChangeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_ChangeInfo.jsp");
		dispatcher.forward(request, response);
	}

	// ajaxで呼び出す仕様に変更
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//文字化け対策
		request.setCharacterEncoding("UTF-8");

		//フォームのリクエストパラメータを取得
		String mail = request.getParameter("email");
		String name = request.getParameter("name");
		int gender = Integer.parseInt(request.getParameter("gender"));
		int area_id = Integer.parseInt(request.getParameter("area"));

		// User型のインスタンスにユーザー情報を格納
		U_User user = new U_User();
		user.setMail(mail);
		user.setName(name);
		user.setGender(gender);
		user.setArea_id(area_id);

		// セッションからユーザーIDを取得
		HttpSession session = request.getSession();
		U_User loginUser = (U_User) session.getAttribute("loginUser");
		user.setUser_id(loginUser.getUser_id());

		UserDAO userDAO = new UserDAO();
		boolean changed = userDAO.userChangeInfo(user);

		if(changed) {
			// ユーザーデータ変更が成功した場合、新しいデータでセッションを更新
			user = userDAO.getUserByMail(user.getMail());
			session.setAttribute("loginUser", user);
			System.out.println("更新に成功しているので、成功した際の表示をjspに出す");
			response.getWriter().append("1");
		}else {
			System.out.println("更新に失敗しているので、失敗した際の表示をjspに出す");
			response.getWriter().append("0");
		}
	}

}
