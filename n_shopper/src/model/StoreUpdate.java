package model;

import java.io.Serializable;
import java.util.Date;


public class StoreUpdate implements Serializable {
	//JavaBeans

	int storeUpdate_id; //店舗情報更新ＩＤ
	Date date;//更新日時
	int user_id;//ユーザーID
	int storeUser_id;//店舗ユーザーＩＤ

	public StoreUpdate() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	/**
	 * @return storeUpdate_id
	 */
	public int getStoreUpdate_id() {
		return storeUpdate_id;
	}

	/**
	 * @param storeUpdate_id セットする storeUpdate_id
	 */
	public void setStoreUpdate_id(int storeUpdate_id) {
		this.storeUpdate_id = storeUpdate_id;
	}

	/**
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date セットする date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id セットする user_id
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return storeUser_id
	 */
	public int getStoreUser_id() {
		return storeUser_id;
	}

	/**
	 * @param storeUser_id セットする storeUser_id
	 */
	public void setStoreUser_id(int storeUser_id) {
		this.storeUser_id = storeUser_id;
	}

}
