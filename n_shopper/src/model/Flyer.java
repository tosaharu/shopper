package model;

import java.sql.Timestamp;

public class Flyer {


	int id;//	チラシＩＤ
	int store_id;//	店舗ID
	String name;//チラシ名
	int click;//チラシクリック数
	Timestamp start_date;//表示開始日
	Timestamp end_date;//表示終了日
	String main_image;//表写真
	String sub_image;//裏写真

	public Flyer() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	/**
	 * @param id
	 * @param name
	 * @param main_image
	 * @param sub_image
	 */
	public Flyer(int id, String name, String main_image, String sub_image) {
		super();
		this.id = id;
		this.name = name;
		this.main_image = main_image;
		this.sub_image = sub_image;
	}

	/**
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return click
	 */
	public int getClick() {
		return click;
	}

	/**
	 * @param click セットする click
	 */
	public void setClick(int click) {
		this.click = click;
	}

	/**
	 * @return start_date
	 */
	public Timestamp getStart_date() {
		return start_date;
	}

	/**
	 * @param start_date セットする start_date
	 */
	public void setStart_date(Timestamp start_date) {
		this.start_date = start_date;
	}

	/**
	 * @return end_date
	 */
	public Timestamp getEnd_date() {
		return end_date;
	}

	/**
	 * @param end_date セットする end_date
	 */
	public void setEnd_date(Timestamp end_date) {
		this.end_date = end_date;
	}

	/**
	 * @return main_mage
	 */
	public String getMain_image() {
		return main_image;
	}

	/**
	 * @param main_mage セットする main_mage
	 */
	public void setMain_image(String main_mage) {
		this.main_image = main_mage;
	}

	/**
	 * @return sub_image
	 */
	public String getSub_image() {
		return sub_image;
	}

	/**
	 * @param sub_image セットする sub_image
	 */
	public void setSub_image(String sub_image) {
		this.sub_image = sub_image;
	}

}
