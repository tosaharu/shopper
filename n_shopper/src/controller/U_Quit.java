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
 * Servlet implementation class U_ChangeInfoPass
 */
@WebServlet("/U_Quit")
public class U_Quit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//パスワードを変更をクリックするとパスワード変更jspにフォワードするのでゲットはいらない？
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		
//		
//		
//		session.removeAttribute("result");//テスト用にsessionを破棄する。
//		
//
//
//		U_User user = new U_User();
//
//		user.setUser_id();//仮でデータベースuser_idの二番を取得※セッションで値がとれたなら消去
//		System.out.println(user.getUser_id());//コンソールで値がとってこれてるかテスト
//
//		UserDAO dao = new UserDAO();
//		U_User result = dao.selectUser(user);
//
//		System.out.println(dao);
//		session.setAttribute("loginUser", result);
//
//		RequestDispatcher dispatcher = request.getRequestDispatcher("u_ChangeInfo.jsp");
//		dispatcher.forward(request, response);
//	}
//
//	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("現在地：U_Quit");
		
		int active  = Integer.parseInt(request.getParameter("active"));
		
		
		HttpSession session = request.getSession();
		U_User loginUser = (U_User) session.getAttribute("loginUser");
		U_User user = new U_User(active);

		//			 データベースに接続
		user.setUser_id(loginUser.getUser_id());
		
		UserDAO userDAO = new UserDAO();
		userDAO.userQuit(user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/TestShima.jsp");
		dispatcher.forward(request, response);

			}
		}
	
