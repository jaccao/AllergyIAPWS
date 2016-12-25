package com.allergyiap.beans;

public class Pharmacy implements java.io.Serializable{
	
	private static final long serialVersionUID = 2L;

	private long id_pharmacy;
	private String name_pharmacy;
	private String latitude;
	private String longitude;
	
	public Pharmacy(){}
	
	public Pharmacy(long id_pharmacy, String name_pharmacy, String latitude, String longitude){
		this.id_pharmacy = id_pharmacy;
		this.name_pharmacy = name_pharmacy;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Pharmacy(String name_pharmacy, String latitude, String longitude){
		this.name_pharmacy = name_pharmacy;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setId_pharmacy(long id_pharmacy){
		this.id_pharmacy = id_pharmacy;
	}
	
	public long getId_pharmacy(){
		return this.id_pharmacy;
	}
	
	public void setName_pharmacy(String name_pharmacy){
		this.name_pharmacy = name_pharmacy;
	}
	
	public String getName_pharmacy(){
		return this.name_pharmacy;
	}
	
	public void setLatitude(String latitude){
		this.latitude = latitude;
	}
	
	public String getLatitude(){
		return this.latitude;
	}
	
	public void setLongitude(String longitude){
		this.longitude = longitude;
	}
	
	public String getLongitude(){
		return this.longitude;
	}
}
