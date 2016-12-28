package com.allergyiap.beans;

public class Station implements java.io.Serializable{

	private static final long serialVersionUID = 2L;
	
	private String name_station;
	private float latitude;
	private float longitude;
	
	public Station(){}
	
	public Station(String name_station, float latitude, float longitude){
		this.name_station = name_station;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setName_station(String name_station){
		this.name_station = name_station;
	}
	public String getName_station(){
		return this.name_station;
	}
	
	public void setLatitude(float latitude){
		this.latitude = latitude;
	}
	public float getLatitude(){
		return this.latitude;
	}
	
	public void setLongitude(float longitude){
		this.longitude = longitude;
	}
	public float getLongitude(){
		return this.longitude;
	}
}
