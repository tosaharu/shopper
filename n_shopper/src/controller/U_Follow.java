package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.U_OtherUser;
import model.U_User;

/**
 * Servlet implementation class U_Follow
 */
@WebServlet("/U_Follow")
public class U_Follow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//String id = request.getParameter("id");

		HttpSession session = request.getSession();

		U_User loginUser = (U_User) session.getAttribute("loginUser");

		U_OtherUser u_OtherUser = new U_OtherUser();

		//int follower_id = loginUser.getUser_id();


		String id ="2";//テスト

		try {
			int follow_id = Integer.parseInt(id);


			int follower_id = 3;//テスト



			System.out.println(follow_id);
			System.out.println(follower_id);

			u_OtherUser.setFollow_user_id(follow_id);//フォローワーが増える人()
			u_OtherUser.setFollower_user_id(follower_id);//フォロワーに追加されるID()

			UserDAO dao = new UserDAO();
			U_OtherUser result = dao.FollowInsert(follow_id, follower_id);

			List<U_OtherUser> followSelectList = dao.FollowSelect(follow_id,follower_id);

			if (follow_id == follower_id && followSelectList == null || followSelectList.size() == 0) {

				for (int i = 0; i < followSelectList.size(); i++) {
					System.out.println(followSelectList.get(i).getFollow_user_id());

					RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Main");
					dispatcher.forward(request, response);
					return;
				}

			} else {


				RequestDispatcher dispatcher = request.getRequestDispatcher("/u_OtherUserList.jsp");
				dispatcher.forward(request, response);
				return;

			}
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
