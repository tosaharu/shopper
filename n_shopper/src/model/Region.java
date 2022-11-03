package model;

import java.io.Serializable;

public class Region implements Serializable{
	private int region_id;
	private String region;

	public Region() {}
	public Region(int region_id, String region) {
		this.region_id = region_id;
		this.region = region;
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
	/**
	 * @return region
	 */
	public String getRegion() {
		return region;
	}
	/**
	 * @param region セットする region
	 */
	public void setRegion(String region) {
		this.region = region;
	}
}
