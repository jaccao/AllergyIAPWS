package com.allergyiap.beans;

public class Station implements java.io.Serializable{

	private static final long serialVersionUID = 2L;
	
	private long id_station;
	private String name_station;
	private String latitude;
	private String longitude;
	
	public Station(){}
	
	public Station(long id_station, String name_station, String latitude, String longitude){
		this.id_station = id_station;
		this.name_station = name_station;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Station(String name_station, String latitude, String longitude){
		this.name_station = name_station;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setId_station(long id_station){
		this.id_station = id_station;
	}
	public long getId_station(){
		return this.id_station;
	}
	
	public void setName_station(String name_station){
		this.name_station = name_station;
	}
	public String getName_station(){
		return this.name_station;
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
