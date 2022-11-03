package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.U_OtherUser;

/**
 * 辻さん
 * Servlet implementation class U_Ranking
 */
@WebServlet("/U_Ranking")
public class U_Ranking extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			HttpSession session = request.getSession();
			//
			session.removeAttribute("result");//テスト用にsessionを破棄する。

			UserDAO dao = new UserDAO();
//			U_User result = dao.selectUser(user);
//			session.setAttribute("loginUser", result);
			List<U_OtherUser> rankingUserList = dao.rankingUserList();


//			U_User loginUser = (U_User) session.getAttribute("loginUser");
			//フォロワー一覧取得
//			 int user_id = loginUser.getUser_id();
//			List<U_OtherUser> followerUserList = dao.followerUserList(user_id);
//

//			}
			System.out.println("CON 0");


			if (rankingUserList == null || rankingUserList.size() == 0 ) {
				System.out.println("Listが空です");
			} else {

				System.out.println("CON 1リストあり");

				for (int i = 0; i < rankingUserList.size(); i++) {
					System.out.println(rankingUserList.get(i).getCount_ranking());
					System.out.println(rankingUserList.get(i).getUser_name());
					System.out.println(rankingUserList.get(i).getCount_follower());

					System.out.println(rankingUserList.get(i).getFollow_user_id());

				}

				request.setAttribute("rankingUserList", rankingUserList);
				request.getRequestDispatcher("/u_OtherUserList.jsp").forward(request, response);
			}

		} catch (NullPointerException e) {
			//			リストが取得できない場合
			e.printStackTrace();
			System.out.println("リストが取得できませんでした");
		}
	}
}