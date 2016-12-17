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

	public long getIduser() {
		return iduser;
	}

	public void setIduser(long id) {
		this.iduser = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		this.user_name = userName;
	}

	public String getUser_second_name() {
		return user_second_name;
	}

	public void setUser_second_name(String userSecondName) {
		this.user_second_name = userSecondName;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String userPassword) {
		this.user_password = userPassword;
	}
	
	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String userMail) {
		this.user_mail = userMail;
	}
}
