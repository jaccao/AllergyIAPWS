package database;

public class AllergyLevelTable implements java.io.Serializable 
{
	private static final long serialVersionUID = 1L;
	private static final String tableName = "allergy_level";

	private long id;
	private long allergyIdAllergy;
	private float currentLevel;
	private String station;
	private String dateStart;
	private String dateEnd;
	private String forecastLevel;
	
	
	public AllergyLevelTable(long id, long allergyIdAllergy, float currentLevel, String station, String dateStart, String dateEnd, String forecastLevel ){
		this.id = id;
		this.allergyIdAllergy = allergyIdAllergy;
		this.currentLevel = currentLevel;
		this.station = station;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.forecastLevel = forecastLevel;
	}
	
	public long getId(){
		return this.id;
	}
	public long getAlleryID(){
		return this.allergyIdAllergy;
	}
	
	public float getCurrentLevel(){
		return this.currentLevel;
	}
	
	public String getStation(){
		return this.station;
	}
	
	public String getDateStart(){
		return this.dateStart;
	}
	
	public String getDateEnd(){
		return this.dateEnd;
	}
	
	public String getForecastLevel(){
		return this.forecastLevel;
	}
	
	public static long getSerialversionUId() {
		return serialVersionUID;
	}
	
	
	public void setId(long id){
		this.id = id;
	}
	
	public void setAlleryID(long allergyIdAllergy){
		this.allergyIdAllergy = allergyIdAllergy;
	}
	
	public void setCurrentLevel(float currentLevel){
		this.currentLevel = currentLevel;
	}
	
	public void setStation(String station){
		this.station = station;
	}
	
	public void setDateStart(String dateStart){
		this.dateStart = dateStart;
	}
	
	public void setDateEnd(String dateEnd){
		this.dateEnd = dateEnd;
	}
	
	public void setForecastLevel(String forecastLevel){
		this.forecastLevel = forecastLevel;
	}	
	
}
