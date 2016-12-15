package com.allergyiap.beans;

public class Allergy implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private long idallergy;
	private String allergy_name;
	private String allergy_description;
	private String allergy_code;

	public Allergy() {
	}

	public Allergy(long id, String allergyName, String allergyDescription, String allergyCode) {
		this.idallergy = id;
		this.allergy_name = allergyName;
		this.allergy_description = allergyDescription;
		this.allergy_code = allergyCode;
	}

	public long getId() {
		return idallergy;
	}

	public void setId(long id) {
		this.idallergy = id;
	}

	public String getAllergyName() {
		return allergy_name;
	}

	public void setAllergyName(String allergyName) {
		this.allergy_name = allergyName;
	}

	public String getAllergyDescription() {
		return allergy_description;
	}

	public void setAllergyDescription(String allergyDescription) {
		this.allergy_description = allergyDescription;
	}

	public String getAllergyCode() {
		return allergy_code;
	}

	public void setAllergyCode(String allergyCode) {
		this.allergy_code = allergyCode;
	}

}
