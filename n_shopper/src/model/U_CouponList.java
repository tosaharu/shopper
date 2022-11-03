//クーポン使用のためのbeans

package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class U_CouponList implements Serializable{
	private  int user_id;
	private int coupon_id;
	public int used_flag;
	private Timestamp start_date;



	//	beans作成用空メソッド
	public U_CouponList() {
		}


	public U_CouponList(int user_id,int coupon_id,int used_flag,Timestamp start_date) {
		this.user_id=user_id;
		this.coupon_id=coupon_id;
		this.used_flag=used_flag;
		this.start_date=start_date;

	}

	/**
	 * @return user_id
	 */
	public  int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id セットする user_id
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
}