package com.allergyiap.beans;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String userName;
	private String userSecondName;
	private String userPassword;
	private String userMail;

	public User() {
	}

	public User(long id, String userName, String userSecondName, String userMail, String userPassword) {
		this.id = id;
		this.userName = userName;
		this.userSecondName = userSecondName;
		this.userPassword = userPassword;
		this.userMail = userMail;
	}
	
	public User(String userName, String userSecondName, String userMail, String userPassword) {
		this.userName = userName;
		this.userSecondName = userSecondName;
		this.userPassword = userPassword;
		this.userMail = userMail;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSecondName() {
		return userSecondName;
	}

	public void setUserSecondName(String userSecondName) {
		this.userSecondName = userSecondName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
}
