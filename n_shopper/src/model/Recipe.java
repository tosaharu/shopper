package model;

import java.sql.Timestamp;

public class Recipe {

	int id;//レシピＩＤ
	int store_id; //店舗ID
	String name;//レシピ名
	int click;//レシピクリック数
	Timestamp start;//表示開始日
	Timestamp stop;//表示終了日
	String image1;//写真1
	String image2;//写真2
	String image3;//写真3

	public Recipe() {
		//Beansであるためのコンストラクタ
	}
	/**
	 * @param id
	 * @param name
	 */
	public Recipe(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @param id
	 * @param name
	 * @param image1
	 * @param image2
	 * @param image3
	 */
	public Recipe(int id, String name, String image1, String image2, String image3) {
		super();
		this.id = id;
		this.name = name;
		this.image1 = image1;
		this.image2 = image2;
		this.image3 = image3;
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
	 * @return start
	 */
	public Timestamp getStart() {
		return start;
	}

	/**
	 * @param start セットする start
	 */
	public void setStart(Timestamp start) {
		this.start = start;
	}

	/**
	 * @return stop
	 */
	public Timestamp getStop() {
		return stop;
	}

	/**
	 * @param stop セットする stop
	 */
	public void setStop(Timestamp stop) {
		this.stop = stop;
	}

	/**
	 * @return image1
	 */
	public String getImage1() {
		return image1;
	}

	/**
	 * @param image1 セットする image1
	 */
	public void setImage1(String image1) {
		this.image1 = image1;
	}

	/**
	 * @return image2
	 */
	public String getImage2() {
		return image2;
	}

	/**
	 * @param image2 セットする image2
	 */
	public void setImage2(String image2) {
		this.image2 = image2;
	}

	/**
	 * @return image3
	 */
	public String getImage3() {
		return image3;
	}

	/**
	 * @param image3 セットする image3
	 */
	public void setImage3(String image3) {
		this.image3 = image3;
	}

}
