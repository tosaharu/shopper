package controller;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dao.UserDAO;
import model.GetList;
import model.U_User;

/**
 * 新規会員登録処理に関するサーブレット
 * @author Haruka Sato
 */
@WebServlet("/U_RegisterUser")
public class U_RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 画面表示処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		// 新規会員登録画面jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterForm.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * 新規会員登録処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// リクエストパラメーター取得
		String mail = request.getParameter("email");
		String originalPass = request.getParameter("password");
		String name = request.getParameter("name");

		// パスワードのハッシュ化
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pass = encoder.encode(originalPass);
		System.out.println(pass);

		// 変換必要なデータ用の箱を用意
		int gender = 0;
		LocalDateTime birthday = LocalDateTime.of(0000, 1, 1, 00, 00, 00);
		int area_id = 0;

		// データ変換処理
		try {
			gender = Integer.parseInt(request.getParameter("gender"));
			area_id = Integer.parseInt(request.getParameter("area"));

			// フォーム入力された日付をdate型に変換
//			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			int year = Integer.parseInt(request.getParameter("year"));
			int month = Integer.parseInt(request.getParameter("month"));
			int day = Integer.parseInt(request.getParameter("day"));
//			String strBirthday = year + "/" + month + "/" + day + " 00:00:00";
//			birthday = sdFormat.parse(strBirthday);
			birthday = birthday.withYear(year).withMonth(month).withDayOfMonth(day);

		} catch (NumberFormatException e) {
			// データ変換失敗の例外を取得
			e.printStackTrace();
		}

		// User型のインスタンスにユーザー情報を格納
		U_User user = new U_User();
		user.setMail(mail);
		user.setPass(pass);
		user.setName(name);
		user.setGender(gender);
		user.setArea_id(area_id);
		user.setBirthday(birthday);

		// データベースにinsert
		UserDAO userDAO = new UserDAO();
		boolean isInserted = userDAO.userInsert(user);

		if(isInserted) {
			// INSERT成功した場合
			user = userDAO.getUserByMail(mail);

			// ユーザー情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);

			// メイン画面サーブレットにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Main");
			dispatcher.forward(request, response);

		}else {
			// ログイン失敗時の処理

			// エラーメッセージをリクエストスコープに保存
			System.out.println("新規会員登録に失敗");

			// 新規会員登録画面jspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterForm.jsp");
			dispatcher.forward(request, response);
		}
	}
}