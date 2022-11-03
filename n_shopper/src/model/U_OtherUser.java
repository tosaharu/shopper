package model;

import java.io.Serializable;

public class U_OtherUser implements Serializable {

	private int id;
	private int follower_user_id;
	private int follow_user_id;
	private String user_name;
	private int count_follower;
	private int count_ranking;

	public U_OtherUser() {
	}
	public U_OtherUser(String user_name,int count_follower) {
		this.user_name = user_name;
		this.count_follower=count_follower;
	}

	public U_OtherUser(int count_ranking, String user_name,int count_follower) {
		this.count_ranking= count_ranking;
		this.user_name=user_name;
		this.count_follower=count_follower;
	}


	public U_OtherUser(int count_ranking, String user_name,int count_follower,int follow_user_id) {
		this.count_ranking= count_ranking;
		this.user_name=user_name;
		this.count_follower=count_follower;
		this.follow_user_id=follow_user_id;
	}
	public U_OtherUser(int follow_user_id) {
		this.follow_user_id=follower_user_id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFollow_user_id() {
		return follow_user_id;
	}
	public void setFollow_user_id(int follow_user_id) {
		this.follow_user_id = follow_user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getCount_follower() {
		return count_follower;
	}
	public void setCount_follower(int count_follower) {
		this.count_follower = count_follower;
	}
	public int getCount_ranking() {
		return count_ranking;
	}
	public void setCount_ranking(int count_ranking) {
		this.count_ranking = count_ranking;
	}
	public int getFollower_user_id() {
		return follower_user_id;
	}
	public void setFollower_user_id(int follower_user_id) {
		this.follower_user_id = follower_user_id;
	}
}
