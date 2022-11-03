package model;

import java.io.Serializable;

public class Store implements Serializable {

	int store_id; //店舗ID
	int area_id;//area_id
	String area_id_s;
	String storeName;//店舗名
	String subPostcode;//詳細住所address
	String businessHours; //営業時間
	String hp;//HP　URL
	int tel;//電話番号
	String tel_s;
	String payment;//支払方法
	String information; //施設情報
	String service; //実施サービス
	int corporationFlag;//法人管理フラグ
	int openFlag;//営業店舗フラグ
	int storeUser_id; //店舗ユーザーＩＤ
	int recipe_id; //レシピＩＤ
	int discount_id; //特売情報ＩＤ
	int coupon_id;//クーポンＩＤ
	int flyer_id;//チラシＩＤ
	int count;//ユーザー訪問数

	public Store() {
	}

	public Store(int store_id,
			String storeName ,
			String subPostcode ,
			String businessHours ,
			String hp ,
			int tel ,
			String payment ,
			String information ,
			String service ,
			int corporationFlag ,
			int openFlag ,
			int count ) {
		this.store_id = store_id;
		this.storeName = storeName;
		this.subPostcode = subPostcode;
		this.businessHours = businessHours;
		this.hp = hp;
		this.tel = tel;
		this.payment = payment;
		this.information = information;
		this.service = service;
		this.corporationFlag = corporationFlag;
		this.openFlag = openFlag;
		this.count = count;

	}

	/**
	 * @param store_id
	 * @param storeName
	 * @param subPostcode
	 * @param businessHours
	 * @param hP
	 * @param tel
	 * @param payment
	 * @param information
	 * @param service
	 * @param corporationFlag
	 * @param openFlag
	 * @param storeUser_id
	 * @param recipe_id
	 * @param discount_id
	 * @param coupon_id
	 * @param flyer_id
	 * @param count
	 */
	public Store(int store_id, String storeName, String subPostcode, String businessHours, String hp, int tel,
			String payment, String information, String service, int corporationFlag, int openFlag, int storeUser_id,
			int recipe_id, int discount_id, int coupon_id, int flyer_id, int count) {
		super();
		this.store_id = store_id;
		this.storeName = storeName;
		this.subPostcode = subPostcode;
		this.businessHours = businessHours;
		this.hp = hp;
		this.tel = tel;
		this.payment = payment;
		this.information = information;
		this.service = service;
		this.corporationFlag = corporationFlag;
		this.openFlag = openFlag;
		this.storeUser_id = storeUser_id;
		this.recipe_id = recipe_id;
		this.discount_id = discount_id;
		this.coupon_id = coupon_id;
		this.flyer_id = flyer_id;
		this.count = count;
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
	 * @return storeName
	 */
	public String getStoreName() {
		return storeName;
	}

	/**
	 * @param storeName セットする storeName
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/**
	 * @return subPostcode
	 */
	public String getSubPostcode() {
		return subPostcode;
	}

	/**
	 * @param subPostcode セットする subPostcode
	 */
	public void setSubPostcode(String subPostcode) {
		this.subPostcode = subPostcode;
	}

	/**
	 * @return businessHours
	 */
	public String getBusinessHours() {
		return businessHours;
	}

	/**
	 * @param businessHours セットする businessHours
	 */
	public void setBusinessHours(String businessHours) {
		this.businessHours = businessHours;
	}

	/**
	 * @return hP
	 */
	public String getHP() {
		return hp;
	}

	/**
	 * @param hP セットする hP
	 */
	public void setHP(String hP) {
		hp = hP;
	}

	/**
	 * @return tel
	 */
	public int getTel() {
		return tel;
	}

	/**
	 * @param tel セットする tel
	 */
	public void setTel(int tel) {
		this.tel = tel;
	}

	/**
	 * @return payment
	 */
	public String getPayment() {
		return payment;
	}

	/**
	 * @param payment セットする payment
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}

	/**
	 * @return information
	 */
	public String getInformation() {
		return information;
	}

	/**
	 * @param information セットする information
	 */
	public void setInformation(String information) {
		this.information = information;
	}

	/**
	 * @return service
	 */
	public String getService() {
		return service;
	}

	/**
	 * @param service セットする service
	 */
	public void setService(String service) {
		this.service = service;
	}

	/**
	 * @return corporationFlag
	 */
	public int getCorporationFlag() {
		return corporationFlag;
	}

	/**
	 * @param corporationFlag セットする corporationFlag
	 */
	public void setCorporationFlag(int corporationFlag) {
		this.corporationFlag = corporationFlag;
	}

	/**
	 * @return openFlag
	 */
	public int getOpenFlag() {
		return openFlag;
	}

	/**
	 * @param openFlag セットする openFlag
	 */
	public void setOpenFlag(int openFlag) {
		this.openFlag = openFlag;
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

	/**
	 * @return recipe_id
	 */
	public int getRecipe_id() {
		return recipe_id;
	}

	/**
	 * @param recipe_id セットする recipe_id
	 */
	public void setRecipe_id(int recipe_id) {
		this.recipe_id = recipe_id;
	}

	/**
	 * @return discount_id
	 */
	public int getDiscount_id() {
		return discount_id;
	}

	/**
	 * @param discount_id セットする discount_id
	 */
	public void setDiscount_id(int discount_id) {
		this.discount_id = discount_id;
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
	 * @return flyer_id
	 */
	public int getFlyer_id() {
		return flyer_id;
	}

	/**
	 * @param flyer_id セットする flyer_id
	 */
	public void setFlyer_id(int flyer_id) {
		this.flyer_id = flyer_id;
	}

	/**
	 * @return count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count セットする count
	 */
	public void setCount(int count) {
		this.count = count;
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
	 * @return hp
	 */
	public String getHp() {
		return hp;
	}

	/**
	 * @param hp セットする hp
	 */
	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getArea_id_s() {
		return area_id_s;
	}

	public void setArea_id_s(String area_id_s) {
		this.area_id_s = area_id_s;
	}

	public String getTel_s() {
		return tel_s;
	}

	public void setTel_s(String tel_s) {
		this.tel_s = tel_s;
	}



}
