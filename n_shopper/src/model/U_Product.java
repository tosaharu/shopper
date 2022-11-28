package model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Haruka Sato
 *
 */
public class U_Product implements Serializable {

	private int product_id;//商品登録ID 商品登録（product）

	private int user_id;//ユーザーID 商品登録（product）
	private String user_name;//ユーザーの名前 ユーザー（user）

	private int region_id;//地方ID
	private int prefecture_id;//都道府県ID
	private int area_id;//エリアID


	private int mainItem_id;//大品目ID 大品目(mainItem)
	private String mainItem_name;//大品目名 大品目(mainItem)
	private int subItem_id;//中品目ID 中品目(subItem)
	private String subItem_name;//中品目名 中品目(subItem)

	private int item_id;//小品目ID  商品登録（product）	 品目テーブル商品登録 商品区分ID）
	private String item_name;//小品目名 小品目（item）

	private String itemDetail;//品目詳細 商品登録（product）

	private int store_id;//店舗ID 商品登録（product）	店舗情報テーブル（商品登録 店舗ID）
	private String store_name;//店舗名 店舗情報（store）	店舗情報テーブル（商品登録 店舗ID）

	private int amount;//数量の入力 商品登録（product）
	private int price;//価格  商品登録（product）

	private Date date;//日付 商品登録（product）
	private int discount=100;//特別割引の有無 商品登録（product）
	private String comment;//コメント 商品登録（product）

	/**
	 * /JavaBeansである為のコンストラクタ
	 */
	public U_Product() {}

	/**
	 * @param item_id
	 * @param store_id
	 * @param mainItem_id
	 * @param subItem_id
	 */
	public U_Product(int mainItem_id, int subItem_id,int item_id, int store_id) {
		this.item_id = item_id;
		this.store_id = store_id;
		this.mainItem_id = mainItem_id;
		this.subItem_id = subItem_id;
	}

	// ↓使用しづらいのでsetter利用に移行したい
	/**
	 * @param product_id
	 * @param user_id
	 * @param user_name
	 * @param item_id
	 * @param item_name
	 * @param itemDetail
	 * @param store_id
	 * @param store_name
	 * @param amount
	 * @param price
	 * @param date
	 * @param discount
	 * @param comment
	 * @param mainItem_id
	 * @param mainItem_name
	 * @param subItem_id
	 * @param subItem_name
	 */
	public U_Product(int product_id, int user_id, String user_name, int item_id, String item_name, String itemDetail,
			int store_id, String store_name, int amount, int price, Date date, int discount, String comment,
			int mainItem_id, String mainItem_name, int subItem_id, String subItem_name) {
		this.product_id = product_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.item_id = item_id;
		this.item_name = item_name;
		this.itemDetail = itemDetail;
		this.store_id = store_id;
		this.store_name = store_name;
		this.amount = amount;
		this.price = price;
		this.date = date;
		this.discount = discount;
		this.comment = comment;
		this.mainItem_id = mainItem_id;
		this.mainItem_name = mainItem_name;
		this.subItem_id = subItem_id;
		this.subItem_name = subItem_name;
	}
	/**
	 * @return product_id
	 */
	public int getProduct_id() {
		return product_id;
	}
	/**
	 * @param product_id セットする product_id
	 */
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	/**
	 * @return user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id セットする user_id
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name セットする user_name
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return item_id
	 */
	public int getItem_id() {
		return item_id;
	}
	/**
	 * @param item_id セットする item_id
	 */
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	/**
	 * @return item_name
	 */
	public String getItem_name() {
		return item_name;
	}
	/**
	 * @param item_name セットする item_name
	 */
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	/**
	 * @return itemDetail
	 */
	public String getItemDetail() {
		return itemDetail;
	}
	/**
	 * @param itemDetail セットする itemDetail
	 */
	public void setItemDetail(String itemDetail) {
		this.itemDetail = itemDetail;
	}
	/**
	 * @return store_id
	 */
	public int getStore_id() {
		return store_id;
	}
	/**
	 * @param store_id セットする store_id
	 */
	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}
	/**
	 * @return store_name
	 */
	public String getStore_name() {
		return store_name;
	}
	/**
	 * @param store_name セットする store_name
	 */
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	/**
	 * @return amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount セットする amount
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	/**
	 * @return date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date セットする date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return discount
	 */
	public int getDiscount() {
		return discount;
	}
	/**
	 * @param discount セットする discount
	 */
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	/**
	 * @return comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment セットする comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return mainItem_id
	 */
	public int getMainItem_id() {
		return mainItem_id;
	}
	/**
	 * @param mainItem_id セットする mainItem_id
	 */
	public void setMainItem_id(int mainItem_id) {
		this.mainItem_id = mainItem_id;
	}
	/**
	 * @return mainItem_name
	 */
	public String getMainItem_name() {
		return mainItem_name;
	}
	/**
	 * @param mainItem_name セットする mainItem_name
	 */
	public void setMainItem_name(String mainItem_name) {
		this.mainItem_name = mainItem_name;
	}
	/**
	 * @return subItem_id
	 */
	public int getSubItem_id() {
		return subItem_id;
	}
	/**
	 * @param subItem_id セットする subItem_id
	 */
	public void setSubItem_id(int subItem_id) {
		this.subItem_id = subItem_id;
	}
	/**
	 * @return subItem_name
	 */
	public String getSubItem_name() {
		return subItem_name;
	}
	/**
	 * @param subItem_name セットする subItem_name
	 */
	public void setSubItem_name(String subItem_name) {
		this.subItem_name = subItem_name;
	}

}
