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

		HttpSession session = request.getSession();

		//		//		U_User user = session.getAttribute("loginUser");
		//
		//		session.removeAttribute("result");//テスト用にsessionを破棄する。
		//
		//		U_User user = new U_User();
		//
		////		user.setUser_id(2);//仮でデータベースuser_idの二番を取得※セッションで値がとれたなら消去
		//		System.out.println(user.getUser_id());//コンソールで値がとってこれてるかテスト
		//
		////		TODO loginUserのセッションはログイン時に作りますが、ここで何のために呼び出してますか？
		//		UserDAO dao = new UserDAO();
		//		U_User result = dao.selectUser(user);
		//
		//		System.out.println(dao);
		//		session.setAttribute("loginUser", result);


		RequestDispatcher dispatcher = request.getRequestDispatcher("u_ChangeInfo.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("★★★/U_ChangeInfo doPost★★★");

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		request.setCharacterEncoding("UTF-8");

		System.out.println("パラメーター受け取る");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		System.out.println("受け取りつつ型変換");

		int gender = Integer.parseInt(request.getParameter("gender"));

		System.out.println("birthday 召喚?");
	//	java.util.Date birthday = new Date();

		System.out.println("型変換 めっちゃ引っ越す前提");
		String area_tx=request.getParameter("area");
		System.out.println(area_tx);
		int area_id = Integer.parseInt(request.getParameter("area"));
		System.out.println(area_id);
//	    int region_id= Integer.parseInt region.getRegion_id()
//		prefecture.getPrefecture_id()
		System.out.println("メールの値：" + mail);


//
//		if ( mail == null || "".equals(mail) ) {//mailが空白なら戻す
//			System.out.println("エラー文mail：null");
//			request.setAttribute("errorMessage", "適切なメールアドレスを入力してください");
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/u_ChangeInfo.jsp");
//			dispatcher.forward(request, response);
//
//		}else if ( pass == null || "".equals(pass) ) {//mailが空白なら戻す
//				System.out.println("エラー文pass：null");
//				request.setAttribute("errorMessage", "適切なパスワードを入力してください");
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/u_ChangeInfo.jsp");
//				dispatcher.forward(request, response);
//
//		}else if ( name == null || "".equals(name) ) {//mailが空白なら戻す
//					System.out.println("エラー文name：null");
//					request.setAttribute("errorMessage", "名前を入力してください");
//					RequestDispatcher dispatcher = request.getRequestDispatcher("/u_ChangeInfo.jsp");
//					dispatcher.forward(request, response);
//
//		}else if ( request.getParameter("area") == null || "".equals(request.getParameter("area")) ) {//mailが空白なら戻す
//						System.out.println("エラー文area：null");
//						request.setAttribute("errorMessage", "適切なメールアドレスを入力してください");
//						RequestDispatcher dispatcher = request.getRequestDispatcher("/u_ChangeInfo.jsp");
//						dispatcher.forward(request, response);
//		} else {//mailが空白以外なら


			HttpSession session = request.getSession();
			U_User loginUser = (U_User) session.getAttribute("loginUser");
			U_User user = new U_User(mail, pass, name, gender, area_id);
//			U_User user = new U_User(mail, pass, name, gender, birthday, area_id);
			//			 データベースに接続
			user.setUser_id(loginUser.getUser_id());
			UserDAO userDAO = new UserDAO();
			System.out.println("コントローラー" + user);
			userDAO.userChangeInfo(user);

			//セッションの更新　※エリアの都道府県・市町村が反映されない
			session.setAttribute("loginUser", user);
//			loginUser = (U_User) session.getAttribute("loginUser");
//			user = new U_User(mail, pass, name, gender, birthday, area_id);
//			userDAO.CheckUser(user);
//			System.out.println("sessionの更新:" + mail);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/u_ChangeInfo.jsp");
			dispatcher.forward(request, response);


		}

//	}

}
