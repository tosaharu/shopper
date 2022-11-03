//＜クーポン詳細画面→使用　サーブレット＞
//テスト
package controller;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CouponUseDAO;
import model.CouponList;
import model.U_User;

/**
 * Servlet implementation class UseCoupon
 */
@WebServlet("/UseCouponS")
public class UseCouponS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UseCouponS() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("★★★現在地★★★：UseCouponS :doGet");

//		u_couponList.jspからcoupon_idが送られる
		CouponList deteal = new CouponList();

		request.setCharacterEncoding("UTF-8");
		String couponid = request.getParameter("id");
		U_User user = (U_User) request.getAttribute("loginUser");
		try {

			int coupon_id=Integer.parseInt(couponid);

			deteal = dao.CouponListDAO.selectDetailCoupon(1,coupon_id);


          //ユーザーIDを１に固定しているが、ログインと繋げたら戻す

//		int isCoupon = CouponUseDAO.selectCouponList(user.getUser_id(),coupon_id);


			int isCoupon = dao.CouponUseDAO.selectCouponList(1,coupon_id);

			//使用状況をリクエストスコープに保存

			if(isCoupon == 0) {
//				User user = (User) request.getAttribute("loginUser");
//			int isCoupon = CouponUseDAO.selectCouponList(user.getUser_id(),coupon_id);
                CouponUseDAO.updateU_CouponList(coupon_id);
                request.setAttribute("couponActive", "使用済み");}

			if(isCoupon == 1) {request.setAttribute("couponActive", "使用中");}
			if(isCoupon == 2) {request.setAttribute("couponActive", "使用する");};

			}catch(NumberFormatException e) {
				e.printStackTrace();
				}

       HttpSession session =request.getSession();
		session.setAttribute("coupondeteal", deteal);


		RequestDispatcher disp = request.getRequestDispatcher("/u_CouponDetail.jsp");
		disp.forward(request, response);
		return;
		}




	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		request.setCharacterEncoding("UTF-8");
		String couponid = request.getParameter("id");
		try {

			int coupon_id=Integer.parseInt(couponid);

//ユーザーIDを１に固定しているが、ログインと繋げたら戻す

			U_User user = (U_User) request.getAttribute("loginUser");

////			CouponUseDAO.insertU_CouponList(user.getUser_id(),coupon_id);
//>>>>>>> refs/remotes/origin/main
			CouponUseDAO.insertU_CouponList(coupon_id,1);

			}catch(NumberFormatException e) {
				e.printStackTrace();
				}

		String referer = request.getHeader("REFERER");
		if(referer.contains("UseCouponS")) {
			RequestDispatcher disp = request.getRequestDispatcher("/u_CouponDetail.jsp");
		disp.forward(request, response);
		}
//		エラー時
		else {RequestDispatcher disp = request.getRequestDispatcher("WEB-INF/error.jsp");
		disp.forward(request, response);}



////		遷移元URL
//		System.out.println(referer);
////		遷移元URLにサーブレット名が含まれるかどうか
//
//		if (referer.contains("UseCouponS")) {
//			RequestDispatcher disp = request.getRequestDispatcher("WEB-INF/u_couponList.jsp");
//			disp.forward(request, response);
//
//		}else {
////			仮でメインに遷移するようにしていますが、合体後、店舗画面に戻す
//			RequestDispatcher disp = request.getRequestDispatcher("WEB-INF/u_Main.jsp");
//		disp.forward(request, response);
//
//
//		}
//		HttpSession session = request.getSession();
//		User user =(User)session.getAttribute("loginUser");

}
}
