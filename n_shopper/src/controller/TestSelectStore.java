package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StoreDAO;
import model.Store;

@WebServlet("/TestSelectStore")
public class TestSelectStore extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//テスト用...最終的に捨てて大丈夫です。
	//2022/10/28 使ってる
	//店舗ページへ遷移する元のページにおいててほしい...idが送信できないと開くことができない為。

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StoreDAO store_DAO = new StoreDAO();
		List<Store> store_list = new ArrayList<>();
		store_list = store_DAO.findAll();
		request.setAttribute("store_list", store_list);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/test.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
