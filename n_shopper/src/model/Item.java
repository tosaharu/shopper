package model;

import java.io.Serializable;

/**
 * @author Jasmine
 */
public class Item implements Serializable{

	//プルダウンメニューで表示される用（テーブル名：Item）
	private int item_id;//小品目ID
	private String item;//小品目名前
	private int subItem_id;//中品目ID
	private int unit;//単位フラグ

	public Item() {
		//Beansである為のコンストラクタ
	}

	/**
	 * @param item_id
	 * @param item
	 * @param subItem_id
	 * @param unit
	 */
	public Item(int item_id, String item, int subItem_id, int unit) {
		super();
		this.item_id = item_id;
		this.item = item;
		this.subItem_id = subItem_id;
		this.unit = unit;
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
	 * @return item
	 */
	public String getItem() {
		return item;
	}

	/**
	 * @param item セットする item
	 */
	public void setItem(String item) {
		this.item = item;
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
	 * @return unit
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * @param unit セットする unit
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}

}
