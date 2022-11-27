package controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.U_User;

/**
 * ajax通信のpostに対し、メールと誕生日の合致するユーザーが存在するかどうかで別のレスポンスを返すサーブレット
 * @author Haruka Sato
 */
@WebServlet("/Ajax_CheckUserMailAndBirthday")
public class Ajax_CheckUserMailAndBirthday extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 送られてきたメールと誕生日に合致するユーザーデータがあれば送信ボタン付の確認POP表示用のレスポンスを返し、正しくなければメッセージ表示用のレスポンスを返す
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメーター取得
		String mail = request.getParameter("email");

		// 変換必要なデータ用の箱を用意
		LocalDateTime birthday = LocalDateTime.of(0000, 1, 1, 00, 00, 00);

		// データ変換処理
		try {
			// フォーム入力された日付をdate型に変換
			int year = Integer.parseInt(request.getParameter("year"));
			int month = Integer.parseInt(request.getParameter("month"));
			int day = Integer.parseInt(request.getParameter("day"));
			birthday = birthday.withYear(year).withMonth(month).withDayOfMonth(day);

		} catch (NumberFormatException e) {
			// データ変換失敗の例外を取得
			e.printStackTrace();
		}


		// User型のインスタンスにユーザー情報を格納
		U_User user = new U_User();
		user.setMail(mail);
		user.setBirthday(birthday);

		// データベースに照合
		UserDAO userDAO = new UserDAO();
		user = userDAO.CollationUser(user);

		if(user != null) {
			//合致データがある際のレスポンス
			response.getWriter().append("1");

		}else {
			//合致データが無い際のレスポンス
			response.getWriter().append("0");

		}
	}

}
