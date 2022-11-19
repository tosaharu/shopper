package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class U_User implements Serializable {
	private int user_id;
	private String mail;
	private String pass;
	private String name;
	private int gender;
	private LocalDateTime birthday;
	private int region_id;
	private String region_name;
	private int prefecture_id;
	private String prefecture_name;
	private int area_id;
	private String area_name;
	private int active;

	public U_User() {}

	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public LocalDateTime getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public int getPrefecture_id() {
		return prefecture_id;
	}
	public void setPrefecture_id(int prefecture_id) {
		this.prefecture_id = prefecture_id;
	}
	public String getPrefecture_name() {
		return prefecture_name;
	}
	public void setPrefecture_name(String prefecture_name) {
		this.prefecture_name = prefecture_name;
	}
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}

}
