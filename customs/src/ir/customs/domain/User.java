package ir.customs.domain;

import java.util.HashMap;
import java.util.Map;

public abstract class User {
	private String username;
	private String password;
	
	private String nationalID;

	private String firstName;
	private String lastName;
	
	protected Map<Access, Boolean> access;

	public User(String username, String password, String nationalID, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.nationalID = nationalID;
		this.firstName = firstName;
		this.lastName = lastName;
		
		this.access = new HashMap<Access, Boolean>();
		
		for(Access acc : Access.values()) {
			this.access.put(acc, false);
		}
	}
	
	public Boolean checkPassword(String query) {
		return password.equals(query);
	}	
	
}
