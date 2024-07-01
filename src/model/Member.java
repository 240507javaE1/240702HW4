package model;

import java.io.Serializable;

public class Member implements Serializable{
	private Integer id;
	private String name;
	private String userID;
	private String password;
	private String account;
	private Double savings;
	private String address;
	private String phone;
//Constructor,
	public Member() {
		super();
	}
	public Member(String name,String userID,String password,String address,String account,String phone) {
		super();
		this.name = name;
		this.userID = userID;
		this.password = password;
		this.address = address;
		this.account = account;
		this.phone = phone;
		this.savings = 0.0;
	}
//set-er&get-er
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Double getSavings() {
		return savings;
	}
	public void setSavings(Double savings) {
		this.savings = savings;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
//Methods,
}
