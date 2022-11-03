package model;

import java.io.Serializable;

/**
 * @author Jasmine
 */
public class MainItem implements Serializable{

	//プルダウンメニューで表示される用（テーブル名：mainItem）
	private int mainItem_id;//大品目ID
	private String mainItem;//大品目名前

	public MainItem() {
		// Beansである為のコンストラクタ
	}

	/**
	 * @param mainItem_id
	 * @param mainItem
	 */
	public MainItem(int mainItem_id, String mainItem) {
		this.mainItem_id = mainItem_id;
		this.mainItem = mainItem;
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
	 * @return mainItem
	 */
	public String getMainItem() {
		return mainItem;
	}

	/**
	 * @param mainItem セットする mainItem
	 */
	public void setMainItem(String mainItem) {
		this.mainItem = mainItem;
	}

}
