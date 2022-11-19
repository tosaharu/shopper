package controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.JsonBean_U_Birthday;
import model.U_User;

/**
 * ajax通信のgetに対し、セッションから誕生日情報を返すサーブレット
 * @author Haruka Sato
 */
@WebServlet("/Ajax_GetUserBirthday")
public class Ajax_GetUserBirthday extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * メールアドレスの存在確認処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("ajaxサーバー側処理開始");
		//	ログインセッション確認
		HttpSession session = request.getSession();
		U_User user = (U_User) session.getAttribute("loginUser");

		//	ログイン判定
		if (user != null) {
			System.out.println("ログイン判定あり");
			LocalDateTime birthday = user.getBirthday();
		    int year = birthday.getYear();
		    int month = birthday.getMonth().getValue();
		    int day = birthday.getDayOfMonth();
		    System.out.println("時間処理完了");


		    JsonBean_U_Birthday jsonBirthday = new JsonBean_U_Birthday();
		    jsonBirthday.setYear(year);
		    jsonBirthday.setMonth(month);
		    jsonBirthday.setDay(day);
		    System.out.println(year);
		    System.out.println(month);
		    System.out.println(day);

		    ObjectMapper objectmapper = new ObjectMapper();
		    String jsonStringBirthday = objectmapper.writeValueAsString(jsonBirthday);
		    System.out.println("jsonデータ作成完了");
		    System.out.println(jsonStringBirthday);

		    response.getWriter().write(jsonStringBirthday);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
