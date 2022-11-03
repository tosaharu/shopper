package model;

import java.io.Serializable;

public class Prefecture implements Serializable{
	private int prefecture_id;
	private String prefecture;
	private int region_id;

	public Prefecture() {}
	public Prefecture(int prefecture_id, String prefecture, int region_id) {
		this.prefecture_id = prefecture_id;
		this.prefecture = prefecture;
		this.region_id = region_id;
	}
	/**
	 * @return prefecture_id
	 */
	public int getPrefecture_id() {
		return prefecture_id;
	}
	/**
	 * @param prefecture_id セットする prefecture_id
	 */
	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}
	/**
	 * @return prefecture
	 */
	public String getPrefecture() {
		return prefecture;
	}
	/**
	 * @param prefecture セットする prefecture
	 */
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}
	/**
	 * @return region_id
	 */
	public int getRegion_id() {
		return region_id;
	}
	/**
	 * @param region_id セットする region_id
	 */
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
}
