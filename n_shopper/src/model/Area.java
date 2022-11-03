package model;

import java.io.Serializable;

public class Area implements Serializable{
	private int area_id;
	private String area;
	private int prefecture_id;

	public Area() {}
	public Area(int area_id, String area, int prefecture_id) {
		this.area_id = area_id;
		this.area = area;
		this.prefecture_id = prefecture_id;
	}
	/**
	 * @return area_id
	 */
	public int getArea_id() {
		return area_id;
	}
	/**
	 * @param area_id セットする area_id
	 */
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	/**
	 * @return area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area セットする area
	 */
	public void setArea(String area) {
		this.area = area;
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
}
