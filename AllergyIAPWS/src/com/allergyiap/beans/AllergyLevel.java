package com.allergyiap.beans;

public class AllergyLevel implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;

	private long idallergy_level;
	private long allergy_idallergy;
	private float current_level;
	private String station;
	private String date_start;
	private String date_end;
	private String forecast_level;
	
	
	public AllergyLevel(long id, long allergyIdAllergy, float currentLevel, String station, String dateStart, String dateEnd, String forecastLevel ){
		this.idallergy_level = id;
		this.allergy_idallergy = allergyIdAllergy;
		this.current_level = currentLevel;
		this.station = station;
		this.date_start = dateStart;
		this.date_end = dateEnd;
		this.forecast_level = forecastLevel;
	}
	
	public long getId(){
		return this.idallergy_level;
	}
	public long getAlleryID(){
		return this.allergy_idallergy;
	}
	
	public float getCurrentLevel(){
		return this.current_level;
	}
	
	public String getStation(){
		return this.station;
	}
	
	public String getDateStart(){
		return this.date_start;
	}
	
	public String getDateEnd(){
		return this.date_end;
	}
	
	public String getForecastLevel(){
		return this.forecast_level;
	}
	
	public static long getSerialversionUId() {
		return serialVersionUID;
	}
	
	
	public void setId(long id){
		this.idallergy_level = id;
	}
	
	public void setAlleryID(long allergyIdAllergy){
		this.allergy_idallergy = allergyIdAllergy;
	}
	
	public void setCurrentLevel(float currentLevel){
		this.current_level = currentLevel;
	}
	
	public void setStation(String station){
		this.station = station;
	}
	
	public void setDateStart(String dateStart){
		this.date_start = dateStart;
	}
	
	public void setDateEnd(String dateEnd){
		this.date_end = dateEnd;
	}
	
	public void setForecastLevel(String forecastLevel){
		this.forecast_level = forecastLevel;
	}	
	
}
