package com.allergyiap.beans;

public class Allergy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String allergyName;
	private String allergyDescription;
	private String allergyCode;

	public Allergy(long id, String allergyName, String allergyDescription, String allergyCode) {
		this.id = id;
		this.allergyName = allergyName;
		this.allergyDescription = allergyDescription;
		this.allergyCode = allergyCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAllergyName() {
		return allergyName;
	}

	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}

	public String getAllergyDescription() {
		return allergyDescription;
	}

	public void setAllergyDescription(String allergyDescription) {
		this.allergyDescription = allergyDescription;
	}

	public String getAllergyCode() {
		return allergyCode;
	}

	public void setAllergyCode(String allergyCode) {
		this.allergyCode = allergyCode;
	}

}
