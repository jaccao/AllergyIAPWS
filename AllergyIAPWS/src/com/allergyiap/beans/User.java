package com.allergyiap.beans;

public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long iduser;
	private String user_name;
	private String user_second_name;
	private String user_password;
	private String user_mail;

	public User() {
	}

	public User(long id, String userName, String userSecondName, String userMail, String userPassword) {
		this.iduser = id;
		this.user_name = userName;
		this.user_second_name = userSecondName;
		this.user_password = userPassword;
		this.user_mail = userMail;
	}
	
	public User(String userName, String userSecondName, String userMail, String userPassword) {
		this.user_name = userName;
		this.user_second_name = userSecondName;
		this.user_password = userPassword;
		this.user_mail = userMail;
	}

	public long getId() {
		return iduser;
	}

	public void setId(long id) {
		this.iduser = id;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUserName(String userName) {
		this.user_name = userName;
	}

	public String getUserSecondName() {
		return user_second_name;
	}

	public void setUserSecondName(String userSecondName) {
		this.user_second_name = userSecondName;
	}

	public String getUserPassword() {
		return user_password;
	}

	public void setUserPassword(String userPassword) {
		this.user_password = userPassword;
	}
	
	public String getUserMail() {
		return user_mail;
	}

	public void setUserMail(String userMail) {
		this.user_mail = userMail;
	}
}
