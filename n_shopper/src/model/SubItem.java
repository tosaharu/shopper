package model;

import java.io.Serializable;

/**
 * @author Jasmine
 */
public class SubItem implements Serializable{

	//プルダウンメニューで表示される用（テーブル名：subItem）
	private int subItem_id;//中品目ID
	private String subItem;//中品目名前
	private int mainItem_id;//大品目ID

	public SubItem() {
		//JavaBeansである為のコンストラクタ
	}

	/**
	 * @param subItem_id
	 * @param subItem
	 * @param mainItem_id
	 */
	public SubItem(int subItem_id, String subItem, int mainItem_id) {
		super();
		this.subItem_id = subItem_id;
		this.subItem = subItem;
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
	 * @return subItem
	 */
	public String getSubItem() {
		return subItem;
	}

	/**
	 * @param subItem セットする subItem
	 */
	public void setSubItem(String subItem) {
		this.subItem = subItem;
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

}
