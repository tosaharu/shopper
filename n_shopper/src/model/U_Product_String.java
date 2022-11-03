package model;

public class U_Product_String {

	//取り出したい変数を作成する。
	private String user_id_s;//ユーザーID 商品登録（product）
	private String item_id_s;//小品目区分ID  商品登録（product）	 品目テーブル商品登録 商品区分ID）
	private String itemDetail_s;//品目詳細 商品登録（product）
	private String store_id_s;//店舗ID 商品登録（product）	店舗情報テーブル（商品登録 店舗ID）
	private String amount_s ;//数量の入力 商品登録（product）
	private String price_s ;//価格  商品登録（product）
	private String date_s;//日付 商品登録（product）
	private String discount_s ;//特別割引の有無 商品登録（product）
	private String comment_s ;//コメント 商品登録（product）
	private String mainItem_id_s;//mainItemのID 大品目(mainItem)
	private String subItem_id_s;//subItemのID 中品目(subItem)
	private int mainItem_id;//mainItemのID 大品目(mainItem)
	private int subItem_id;//subItemのID 中品目(subItem)
	private int item_id;//subItemのID 中品目(subItem)

	public U_Product_String() {
		//JavaBeansである為のコンストラクタ
		//		this.user_id_s = user_id_s;
		//		this.item_id_s = item_id_s;
		//		this.itemDetail_s = itemDetail_s;
		//		this.store_id_s = store_id_s;
		//		this.amount_s = amount_s;
		//		this.price_s = price_s;
		//		this.date_s = date_s;
		//		this.discount_s = discount_s;
		//		this.comment_s = comment_s;
	}

	/**
	 * @return user_id_s
	 */
	public String getUser_id_s() {
		return user_id_s;
	}

	/**
	 * @param user_id_s セットする user_id_s
	 */
	public void setUser_id_s(String user_id_s) {
		this.user_id_s = user_id_s;
	}

	/**
	 * @return item_id_s
	 */
	public String getItem_id_s() {
		return item_id_s;
	}

	/**
	 * @param item_id_s セットする item_id_s
	 */
	public void setItem_id_s(String item_id_s) {
		this.item_id_s = item_id_s;
	}

	/**
	 * @return itemDetail_s
	 */
	public String getItemDetail_s() {
		return itemDetail_s;
	}

	/**
	 * @param itemDetail_s セットする itemDetail_s
	 */
	public void setItemDetail_s(String itemDetail_s) {
		this.itemDetail_s = itemDetail_s;
	}

	/**
	 * @return store_id_s
	 */
	public String getStore_id_s() {
		return store_id_s;
	}

	/**
	 * @param store_id_s セットする store_id_s
	 */
	public void setStore_id_s(String store_id_s) {
		this.store_id_s = store_id_s;
	}

	/**
	 * @return amount_s
	 */
	public String getAmount_s() {
		return amount_s;
	}

	/**
	 * @param amount_s セットする amount_s
	 */
	public void setAmount_s(String amount_s) {
		this.amount_s = amount_s;
	}

	/**
	 * @return price_s
	 */
	public String getPrice_s() {
		return price_s;
	}

	/**
	 * @param price_s セットする price_s
	 */
	public void setPrice_s(String price_s) {
		this.price_s = price_s;
	}

	/**
	 * @return date_s
	 */
	public String getDate_s() {
		return date_s;
	}

	/**
	 * @param date_s セットする date_s
	 */
	public void setDate_s(String date_s) {
		this.date_s = date_s;
	}

	/**
	 * @return discount_s
	 */
	public String getDiscount_s() {
		return discount_s;
	}

	/**
	 * @param discount_s セットする discount_s
	 */
	public void setDiscount_s(String discount_s) {
		this.discount_s = discount_s;
	}

	/**
	 * @return comment_s
	 */
	public String getComment_s() {
		return comment_s;
	}

	/**
	 * @param comment_s セットする comment_s
	 */
	public void setComment_s(String comment_s) {
		this.comment_s = comment_s;
	}

	/**
	 * @return mainItem_id_s
	 */
	public String getMainItem_id_s() {
		return mainItem_id_s;
	}

	/**
	 * @param mainItem_id_s セットする mainItem_id_s
	 */
	public void setMainItem_id_s(String mainItem_id_s) {
		this.mainItem_id_s = mainItem_id_s;
	}

	/**
	 * @return subItem_id_s
	 */
	public String getSubItem_id_s() {
		return subItem_id_s;
	}

	/**
	 * @param subItem_id_s セットする subItem_id_s
	 */
	public void setSubItem_id_s(String subItem_id_s) {
		this.subItem_id_s = subItem_id_s;
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



}