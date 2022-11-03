package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetList;
import model.U_ChangePasswordLogic;
import model.U_User;

@WebServlet("/U_ChangePassword")
public class U_ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//エリアデータを取得してビーンズに入れる
		GetList.AreaPrefectureRegion(request);

		//		フォームにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/u_ForgetPassword.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		//String day = request.getParameter("birthday");//00000122

		// 想定される入力文字列の例
		//				String test="19990101";

		String date = request.getParameter("birthday");
		//String day = "20000101";//テスト用の固定の値
		String mail = request.getParameter("mail");
		//String mail ="123@acb.jp";//テスト用の固定の値

		// 正規表現による パターン設定
		// ^	：文字の始まり
		// $	：文字の終わり
		// \\d	：数値
		// {4}	：直前のパターンが必ず4回続く
		// {2}	：直前のパターンが必ず2回続く …
		// ()	：文字をキャプチャする
		java.util.regex.Pattern pt = java.util.regex.Pattern.compile("^(\\d{4})(\\d{2})(\\d{2})$");

		// 文字列をマッチングさせて、必要部分をキャプチャする
		java.util.regex.Matcher mt = pt.matcher(date);
		// キャプチャした情報をもとに、必要な文字列に加工する
		// $0	: 元になる文字列本体
		// $1	: 1つ目のキャプチャ結果
		// $2	: 2つ目の ……
		String test_out = mt.replaceAll("$1-$2-$3");
		//System.out.println(test_out);

		//フォームからの情報を解析
		try {
			// 加工した文字列をSQL日付型に変換する
			java.sql.Date birthday = java.sql.Date.valueOf(test_out);
			//System.out.println(birthday);
			//			int birthday =Integer.parseInt(day);
			//			int birthday =20000101;//テスト用の固定の値
			//			 mail ="123@acb.jp";//テスト用の固定の値
			//インスタンス生成
			U_User user = new U_User(mail, date);

			user.setMail(mail);
			user.setBirthday(birthday);

			//照会処理
			U_ChangePasswordLogic u_UserChangePasswordLogic = new U_ChangePasswordLogic();
			U_User changePass = U_ChangePasswordLogic.execute(user);

			//照会が成功したときの処理
			if (changePass != null) {
				// ユーザー情報をセッションスコープに保存
				HttpSession session = request.getSession();
				session.setAttribute("cahangePass", user);
				System.out.println(changePass);
				// 完了画面にフォワード
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/u_ForgetPasswordComplete.jsp");
				dispatcher.forward(request, response);
				return;
			} else {
				// エラーメッセージをリクエストスコープに保存
				request.setAttribute("errorMessage", "失敗しました");

				//再度エラー文付き入力画面
				RequestDispatcher dispatcher = request.getRequestDispatcher("/u_ForgetPassword.jsp");
				dispatcher.forward(request, response);

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			// エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMessage", "失敗しました");
		} catch (IllegalArgumentException e) {
			// TODO: 入力文字列がSQL日付に変換できない場合の処理を追加する必要がある
			e.printStackTrace();
		}
	}
}
