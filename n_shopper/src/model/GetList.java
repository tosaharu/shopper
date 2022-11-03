package model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.AreaDAO;
import dao.ItemDAO;
import dao.MainItemDAO;
import dao.PrefectureDAO;
import dao.RegionDAO;
import dao.StoreDAO;
import dao.SubItemDAO;

public class GetList {

	public static void AreaPrefectureRegion(HttpServletRequest request) {
		//		DAOでエリアデータ取得
		RegionDAO regionDAO = new RegionDAO();
		List<Region> regionList = regionDAO.getRegionList();
		PrefectureDAO prefectureDAO = new PrefectureDAO();
		List<Prefecture> prefectureList = prefectureDAO.getPrefectureList();
		AreaDAO areaDAO = new AreaDAO();
		List<Area> areaList = areaDAO.getAreaList();

		//		エリア一覧データをビーンズに
		request.setAttribute("regionList", regionList);
		request.setAttribute("prefectureList", prefectureList);
		request.setAttribute("areaList", areaList);
	}

	public static void MainSubItemStore(HttpServletRequest request) {
		//商品登録画面(u_RegisterItem.jsp)へ行くために必要な事前情報の取得（大中小品目・店舗名）
		//	DAOでエリアデータを取得する
		MainItemDAO main_DAO = new MainItemDAO();
		List<MainItem> mainItem_list = main_DAO.findAll();
		SubItemDAO subItem_DAO = new SubItemDAO();
		List<SubItem> subItem_list = subItem_DAO.findAll();
		ItemDAO item_DAO = new ItemDAO();
		List<Item> item_list = item_DAO.findAll();
		StoreDAO store_dao = new StoreDAO();
		List<Store> store_list = store_dao.findAll();

		//大中小品目、店舗名一覧をリクエストパラメーターにセットする。
		request.setAttribute("mainItem_list", mainItem_list);
		request.setAttribute("subItem_list", subItem_list);
		request.setAttribute("item_list", item_list);
		request.setAttribute("store_list", store_list);
	}

}
