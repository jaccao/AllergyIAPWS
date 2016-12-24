package com.allergyiap.beans;

public class Pharmacy implements java.io.Serializable{
	
	private static final long serialVersionUID = 2L;

	private long id_pharmacy;
	private long id_customer;
	private String name_pharmacy;
	private String latitude;
	private String longitude;
	
	public Pharmacy(){}
	
	public Pharmacy(long id_pharmacy, long id_customer, String name_pharmacy, String latitude, String longitude){
		this.id_pharmacy = id_pharmacy;
		this.id_customer= id_customer;
		this.name_pharmacy = name_pharmacy;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Pharmacy(long id_customer, String name_pharmacy, String latitude, String longitude){
		this.id_customer= id_customer;
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
	
	public void setId_customer(long id_customer){
		this.id_customer = id_customer;
	}
	
	public long getId_customer(){
		return this.id_customer;
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
