package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import model.GetList;
import model.U_Product;
import model.U_Product_String;
import model.U_User;

/**
 * 商品登録処理に関するサーブレット
 * @author Haruka Sato
 */
@WebServlet("/U_RegisterItem")
public class U_RegisterItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 画面表示処理
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッションスコープにインスタンスを保存-初期化されてるものが入ってるので間違った取り出しが行われないようにしている。
		//お気に入りリストのセッションはここに入れてほしい。

		U_Product_String u_p_s = new U_Product_String();
		request.setAttribute("u_p_s", u_p_s);

		//		ログイン済みかどうかを判定
		HttpSession session = request.getSession();
		U_User user = (U_User) session.getAttribute("loginUser");

		//		ログイン済の場合
		if (user != null) {
			System.out.println("ユーザーIDは" + user.getUser_id());
		}

//				未ログイン時の処理はいったんスルー　
				else {
					//	未ログインの場合
					System.out.println("セッションがありません");
//					U_User guest = new U_User(0, "ゲスト");
//
//					//確認用
//					System.out.println("ユーザーIDは" + guest.getUser_id()+"◆"+guest.getName()+"さん");
//
//					//未ログイン状況をリクエストパラメーターへ送信
//					request.setAttribute("guest", guest);
//
					//テストの為
					RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Login");
					dispatcher.forward(request, response);
					return;//事故防止のreturn;

				}

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		//大中小品目、店舗名のArrayListを取り出す。
		GetList.MainSubItemStore(request);

		System.out.println();
		System.out.println("エリアと品目と店舗をDBから取得し保存");

		//セッションスコープに保存するインスタンスの生成。
		U_Product check_id = new U_Product();
		request.setAttribute("check_id", check_id);
		System.out.println("チェック用の値を保存最初はnull");

		//値の取得確認用
		/**
		System.out.println("取得できた値を表示する。");
		for (int i = 0; i < store_list.size(); i++) {
			System.out.println(store_list.get(i).getStoreName());
		}*/

		System.out.println("check_idの取得" + check_id.getStore_id());
		System.out.println(u_p_s.getItemDetail_s());
		System.out.println("********jspへforwardする");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterItem.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
	 * 商品投稿処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//		文字化け対策
		request.setCharacterEncoding("UTF-8");

		//エラーメッセージの中身：2回以上使うので一括で編集するためにここで宣言する。
		String errorMsg = "※項目ごとに正しい形式で入力をしてください。※";
		String errorMsg_price = "1円以上の値段を入力して下さい。";
		String errorMsg_amount = "1つ以上の数量を入力して下さい。";

		//		ログイン済みかどうかを判定
		HttpSession session = request.getSession();
		//		ログインセッションの取得
		U_User user = (U_User) session.getAttribute("loginUser");
		//		ログイン済の場合
		if (user != null) {
			System.out.println("ユーザーIDは" + user.getUser_id());

		} else {
			//	未ログインの場合
			System.out.println("セッションがありません");
			U_User guest = new U_User();
			guest.setUser_id(0);//ゲスト用のIDを入れる
			guest.setName("ゲスト");//ゲスト用のIDを入れる
			System.out.println("ユーザーIDは" + guest.getUser_id());
			request.setAttribute("guest", guest);

			//テストの為
			RequestDispatcher dispatcher = request.getRequestDispatcher("/U_Login");
			dispatcher.forward(request, response);
			return;//事故防止のreturn;

		}

		//エリアデータを取得してリクエストスコープに入れる
		GetList.AreaPrefectureRegion(request);

		//大中小品目、店舗名を取得してリクエストスコープに入れる
		GetList.MainSubItemStore(request);

		//		TODO [[[佐藤メモ]]]
		//		投稿する際、ログイン済かどうかを判別
		//		ifログイン済み→普通にDBにインサート
		//		else if未ログイン→未ログイン時の登録情報を入れるセッションを作り、その中にList<U_Product>でプロダクトを入れていく
		//		その際に、itemtDAOでsubitemDAO、mainitemDAO、storeDAO、useraccountDAO

		//TODO：前提条件：店舗の選択がされている。一旦おいておく
		//TODO:フォームを入力する。（Main画面のお気に入りから登録された場合先攻入力あり。）こっちで登録済みのやつをセッションに登録して送り込む。

		/** "*"で括られた部分はｊｓｐでの名前です(リクエストパラメータで受け取る際に用いります)。
		 *	サーブレット側にて変換する際の変数名は実施の際に記します。（一律"text"を"tx"に変換する。本来Stringのものは_textを削除）
		 * TODO Parametaから受け取ったものをJavaとして使える状態に変換します。（String型なので各々の型に変換します。）
		 * 受け取られる要素(順番はテーブル定義書を準拠しているが、コードでは則ってはいない。)
		 *@product_id;//+インサートする際にオートインクリメントされる+商品登録ID
		 *@user_id;//*user_id_text*ユーザーID
		 *@item_id;//*item_id_text*小品目区分ID
		 *@itemDetail;//*itemDetail_text*品目詳細
		 *@store_id;//*store_id_text*店舗ID
		 *@amount;//*amount_text*数量の入力
		 *@price;//*price_text*価格
		 *@date;//*date_text*日付 商品登録（product）
		 *@discount;//*discount_text*特別割引の有無 商品登録（product）
		 *@comment;//*comment_text*コメント 商品登録（product）
		 */
		request.setCharacterEncoding("UTF-8");
		//まず、リクエストパラメータから値を受け取る。
		//リクエストパラメータ:本来であればStrimg型
		String itemDetail = request.getParameter("detail");
		String comment = request.getParameter("comment");
		//リクエストパラメータ:本来であればint型 変換が必要
		String item_id_tx = request.getParameter("item");
		String price_tx = request.getParameter("price");
		String amount_tx = request.getParameter("amount");
		String discount_tx = request.getParameter("discount");
		String store_id_tx = request.getParameter("store_id");
		String user_id_tx = request.getParameter("user_id");

		String mainItem_id_tx = request.getParameter("mainitem");//大品目
		String subItem_id_tx = request.getParameter("subitem");//中品目

		//リクエストパラメータ:本来であればDate型 変換が必要
		String date_tx = request.getParameter("date");

		System.out.println("動作確認2");

		System.out.println("◆itemDetail:" + itemDetail + "\n◆comment:" + comment + "\n◆item_id_tx:" + item_id_tx
				+ "\n◆price_tx:" + price_tx + "\n◆amount_tx:" + amount_tx + "\n◆discount_tx:"
				+ discount_tx + "\n◆store_id_tx:" + store_id_tx + "\n◆user_id_tx:" + user_id_tx + "\n◆date_tx:"
				+ date_tx + "\n◆mainItem_id_tx:" + mainItem_id_tx + "\n◆subItem_id_tx:"
				+ subItem_id_tx + "リクエストパラメータから出す");

		U_Product_String u_p_s = new U_Product_String();

		u_p_s.setUser_id_s(user_id_tx);//ユーザーID 商品登録（product）
		u_p_s.setItemDetail_s(itemDetail);//品目詳細 商品登録（product）
		u_p_s.setStore_id_s(store_id_tx);//店舗ID 商品登録（product）	店舗情報テーブル（商品登録 店舗ID）
		u_p_s.setAmount_s(amount_tx);//数量の入力 商品登録（product）
		u_p_s.setPrice_s(price_tx);//価格  商品登録（product）

		u_p_s.setDate_s(date_tx);//日付 商品登録（product）
		u_p_s.setDiscount_s(discount_tx);//特別割引の有無 商品登録（product）
		u_p_s.setComment_s(comment);//コメント

		u_p_s.setMainItem_id_s(mainItem_id_tx);//大品目-tx
		u_p_s.setSubItem_id_s(subItem_id_tx);//中品目-tx
		u_p_s.setItem_id_s(item_id_tx);//小品目区分ID  商品登録（product）	 品目テーブル商品登録 商品区分ID）

		request.setAttribute("u_p_s", u_p_s);

		//セッションスコープに保存するインスタンスの生成。
		U_Product check_id = new U_Product();
		int item_id;
		int store_id;
		try {
			//Selectedの判定でint型が必要
			int mainItem_id = Integer.parseInt(mainItem_id_tx);
			int subItem_id = Integer.parseInt(subItem_id_tx);
			item_id = Integer.parseInt(item_id_tx);
			store_id = Integer.parseInt(store_id_tx);
			int discount = Integer.parseInt(discount_tx);

			check_id.setMainItem_id(mainItem_id);//大品目
			check_id.setSubItem_id(subItem_id);//中品目
			check_id.setItem_id(item_id);//小品目
			check_id.setStore_id(store_id);//店舗ID
			check_id.setDiscount(discount);//discount

			System.out.println("************test********************");
			System.out.println("◆MainItem_id:" + check_id.getMainItem_id() + "\n◆SubItem_id:" +
					check_id.getSubItem_id() + "\n◆Item_id:" +
					check_id.getItem_id() + "\n◆Store_id:" +
					check_id.getStore_id() + "\n◆Discount:" + check_id.getDiscount());

			request.setAttribute("check_id", check_id);

		} catch (NumberFormatException e) {

			System.out.println("大中小品目、店舗いずれかの値が入力されていない");
			request.setAttribute("errorMsg", errorMsg);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterItem.jsp");
			dispatcher.forward(request, response);
			return;
		}

		try {
			item_id = Integer.parseInt(item_id_tx);
			int price = Integer.parseInt(price_tx);
			int amount = Integer.parseInt(amount_tx);
			int discount = Integer.parseInt(discount_tx);
			store_id = Integer.parseInt(store_id_tx);
			Date date = Date.valueOf(date_tx);//String型の日付をSQL文に使えるように整える。

			if (price == 0 && amount == 0) {
				System.out.println(errorMsg_price + "\n" + errorMsg_amount);

				request.setAttribute("errorMsg_price", errorMsg_price);
				request.setAttribute("errorMsg_amount", errorMsg_amount);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterItem.jsp");
				dispatcher.forward(request, response);
				return;

			} else if (price == 0) {
				request.setAttribute("errorMsg_price", errorMsg_price);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterItem.jsp");
				dispatcher.forward(request, response);
				return;
			} else if (amount == 0) {
				request.setAttribute("errorMsg_amount", errorMsg_amount);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterItem.jsp");
				dispatcher.forward(request, response);
				return;
			}

			//		ログイン済の場合
			if (user != null) {
				System.out.println("ユーザーIDは" + user.getUser_id());
				int user_id = user.getUser_id();
				//（ログイン時）DAOに送るor（未ログイン時）セッションに保存する商品インスタンスの生成。
				U_Product u_p = new U_Product();

				u_p.setUser_id(user_id);//ユーザーID 商品登録（product）
				/**
				 * 商品インスタンスに上記要素を入れる。(:DBにINSERTできる形にする。)
				 */

				u_p.setItem_id(item_id);//小品目区分ID  商品登録（product）	 品目テーブル商品登録 商品区分ID）
				u_p.setItemDetail(itemDetail);//品目詳細 商品登録（product）
				u_p.setStore_id(store_id);//店舗ID 商品登録（product）	店舗情報テーブル（商品登録 店舗ID）
				u_p.setAmount(amount);//数量の入力 商品登録（product）
				u_p.setPrice(price);//価格  商品登録（product）
				u_p.setDate(date);//日付 商品登録（product）
				u_p.setDiscount(discount);//特別割引の有無 商品登録（product）
				u_p.setComment(comment);//コメント

				//TODO:DAOつかってインサートする。list:u_pをInsertする。
				ProductDAO productDAO = new ProductDAO();
				boolean result = productDAO.create(u_p);

				if (result) {//DBへのinsertが成功した場合

					System.out.println("★★★INSERT成功★★★");
					System.out.println();

					//ユーザー入力の分のセッションを破棄する
					session.removeAttribute("u_p_s");

				} else {//DBへのInsertが失敗した場合
						//型変換失敗した時と同じ挙動をして欲しいです。
					request.setAttribute("errorMsg", errorMsg);

					System.out.println("★★★INSERT失敗★★★");

					//RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterItem.jsp");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
					dispatcher.forward(request, response);
					return;//事故防止のreturn;

				}

			}
			//			else{//今は死んでるけど生き返る。
			//				//			未ログインの場合
			//				System.out.println("未ログイン時用のセッションに投稿情報をいれる");
			//				//			TODO
			//				//			未ログイン時の投稿リストセッションを取得してフォワード
			//			}

		} catch (NumberFormatException e) {
			e.printStackTrace();
			System.out.println("リクエストパラメータの取得⇒型変換失敗 int型");

			/*errorの箱を作ってエラーを入れる：判定は入力しなおしてください。NGの方：エラーボックスがnullかエラーなしの方かだったら
			 * そのページに遷移してもエラーのボックスにはエラーの判定がない。
			 */

			request.setAttribute("errorMsg", errorMsg);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/u_RegisterItem.jsp");
			dispatcher.forward(request, response);
			return;

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/shopper/U_Main");
		dispatcher.forward(request, response);
		return;

	}
}
