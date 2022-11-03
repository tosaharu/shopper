//＜クーポン一覧を表示するサーブレット＞

//テスト
package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CouponListDAO;
import model.CouponList;


/**
 * Servlet implementation class UseCoupon
 */
@WebServlet("/CouponListS")
public class CouponListS extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CouponListS() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


//とりあえずuser_id 1にしている
//		U_User user = (U_User) request.getAttribute("loginUser");
//		List<CouponList> list= CouponListDAO.selectCouponList(user.getUser_id());
		List<CouponList> list= CouponListDAO.selectCouponList(1);


		HttpSession session =request.getSession();
		session.setAttribute("couponList", list);



		RequestDispatcher disp = request.getRequestDispatcher("WEB-INF/u_couponList.jsp");
		disp.forward(request, response);
		return;
	}
}

