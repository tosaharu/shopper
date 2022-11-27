package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.U_User;

/**
 * 選択中のエリアをヘッダーに表示させる処理に関するサーブレット
 * @author Haruka Sato
 */
@WebServlet("/U_HeaderAreaSetter")
public class U_HeaderAreaSetter extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * セッションからエリア情報を取得し、その情報でヘッダーのセレクトボタンを選択済みにする
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String str_region_id = request.getParameter("header_region");
		int region_id = Integer.parseInt(str_region_id);
		System.out.println(region_id);

		String str_prefecture_id = request.getParameter("header_prefecture");
		int prefecture_id = Integer.parseInt(str_prefecture_id);
		System.out.println(prefecture_id);

		String str_area_id = request.getParameter("header_area");
		int area_id = Integer.parseInt(str_area_id);
		System.out.println(area_id);

		HttpSession session = request.getSession();
		U_User user = (U_User) session.getAttribute("loginUser");

		user.setRegion_id(region_id);
		user.setPrefecture_id(prefecture_id);
		user.setArea_id(area_id);

		session.setAttribute("loginUser", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Main");
		dispatcher.forward(request, response);	}
}
