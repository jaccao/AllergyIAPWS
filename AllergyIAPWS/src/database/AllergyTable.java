package database;

public class AllergyTable implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private static final String tableName = "allergy";

	private long id;
	private String allergyName;
	private String allergyDescription;
	private String allergyCode;
	
	
	public AllergyTable(long id, String allergyName, String allergyDescription, String allergyCode){
		this.id = id;
		this.allergyName = allergyName;
		this.allergyDescription = allergyDescription;
		this.allergyCode = allergyCode;
	}
	
	public long getId(){
		return this.id;
	}
	
	public String getUserName(){
		return this.allergyName;
	}
	
	public String getUserDescription(){
		return this.allergyDescription;
	}
	
	public String getAllergyCode(){
		return this.allergyCode;
	}
	
	public static long getSerialversionUId() {
		return serialVersionUID;
	}
	
	public void setgetId(long id){
		this.id = id;
	}
	
	public void setUserName(String allergyName){
		this.allergyName = allergyName;
	}
	
	public void setUserDescription(String allergyDescription){
		this.allergyDescription = allergyDescription;
	}
	
	public void setAllergycode(String allergyCode){
		this.allergyCode = allergyCode;
	}

}
