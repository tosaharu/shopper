package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class CouponList implements Serializable {

	//品目名
	private String subItem_id;
	//店舗ID
	private int store_id;
	//クーポン使用フラグ
	private int active;

	//クーポン詳細
	private String comment;
	//クーポン名
	private String name;
	//	クーポンID
	private int coupon_id;
	//	写真
	private String image;
	//	店舗名
	private String storename;
	//	表示開始
	private Timestamp displayStart;
	//	利用開始（期間的な意味）
	private Timestamp start_date;
//	利用開始（期間的な意味）
	private Timestamp end_date;
//	使用済みフラグ
	private int used_flag;
//	使用済みフラグ（NULL→1）
	private int used_flag2;

	public CouponList() {};

	/**
	 * @return subItem_id
	 */
	public String getSubItem_id() {
		return subItem_id;
	}

	/**
	 * @param subItem_id セットする subItem_id
	 */
	public void setSubItem_id(String subItem_id) {
		this.subItem_id = subItem_id;
	}

	/**
	 * @param subItem_id
	 * @param name
	 * @param coupon_id
	 * @param end_date
	 */
	public CouponList( int coupon_id,String subItem_id, Timestamp end_date, String name) {
		super();
		this.subItem_id = subItem_id;
		this.name = name;
		this.coupon_id = coupon_id;
		this.end_date = end_date;
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
	 * @return active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active セットする active
	 */
	public void setActive(int active) {
		this.active = active;
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
	 * @return coupon_id
	 */
	public int getCoupon_id() {
		return coupon_id;
	}

	/**
	 * @param coupon_id セットする coupon_id
	 */
	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}

	/**
	 * @return image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image セットする image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return storename
	 */
	public String getStorename() {
		return storename;
	}

	/**
	 * @param storename セットする storename
	 */
	public void setStorename(String storename) {
		this.storename = storename;
	}

	/**
	 * @return displayStart
	 */
	public Timestamp getDisplayStart() {
		return displayStart;
	}

	/**
	 * @param displayStart セットする displayStart
	 */
	public void setDisplayStart(Timestamp displayStart) {
		this.displayStart = displayStart;
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
	 * @return used_flag
	 */
	public int getUsed_flag() {
		return used_flag;
	}

	/**
	 * @param used_flag セットする used_flag
	 */
	public void setUsed_flag(int used_flag) {
		this.used_flag = used_flag;
	}

	/**
	 * @return used_flag2
	 */
	public int getUsed_flag2() {
		return used_flag2;
	}

	/**
	 * @param used_flag2 セットする used_flag2
	 */
	public void setUsed_flag2(int used_flag2) {
		this.used_flag2 = used_flag2;
	}

	public CouponList(int coupon_id, String subItem_id, String name, String comment, String image, String storename,
			Timestamp end_date,int used_flag2) {
		super();
		this.coupon_id = coupon_id;
		this.subItem_id = subItem_id;
		this.name = name;
		this.comment = comment;
		this.image = image;
		this.storename = storename;
		this.end_date = end_date;
		this.used_flag2 =used_flag2;
	}
}
//	public CouponList(int coupon_id, String subItem_id, String name, String comment, Timestamp stop,
//			String image, String storename, int active) {
//		this.coupon_id = coupon_id;
//		this.subItem_id = subItem_id;
//		this.name = name;
//		this.comment = comment;
//		this.stop = stop;
//		this.image = image;
//		this.storename = storename;
//		this.active = active;
//
//	}
//
//	/**
//	 * @param subItem_id
//	 * @param vaildPeriod
//	 * @param name
//	 * @param coupon_id
//	 */
//	public CouponList(int coupon_id, String subItem_id, Timestamp stop, String name) {
//		super();
//		this.subItem_id = subItem_id;
//		this.stop = stop;
//		this.name = name;
//		this.coupon_id = coupon_id;
//	}
//
//
//	public CouponList(int coupon_id, String subItem_id, String name, String comment, Timestamp stop,
//			String image, String storename) {
//		this.coupon_id = coupon_id;
//		this.subItem_id = subItem_id;
//		this.name = name;
//		this.comment = comment;
//		this.stop = stop;
//		this.image = image;
//		this.storename = storename;
//
//	}
//
//	public CouponList(int coupon_id, String subItem_id, Timestamp start, Timestamp stop,
//			String name) {
//		this.coupon_id = coupon_id;
//		this.subItem_id = subItem_id;
//		this.name = name;
//		this.start = start;
//		this.stop = stop;
//		this.name = name;
//	}



