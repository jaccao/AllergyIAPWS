package com.allergyiap.test;

public class UserBean {
	private String user_name;
    private String user_password;
    private String firstName;
    private String lastName;
    public boolean valid;
	
	
    public String getFirstName() {
       return firstName;
	}

    public void setFirstName(String newFirstName) {
       firstName = newFirstName;
	}

	
    public String getLastName() {
       return lastName;
			}

    public void setLastName(String newLastName) {
       lastName = newLastName;
			}
			

    public String getPassword() {
       return user_password;
	}

    public void setPassword(String newPassword) {
       user_password = newPassword;
	}
	
			
    public String getUsername() {
       return user_name;
			}

    public void setUserName(String newUsername) {
       user_name = newUsername;
			}

				
    public boolean isValid() {
       return valid;
	}

    public void setValid(boolean newValid) {
       valid = newValid;
	}	

}
