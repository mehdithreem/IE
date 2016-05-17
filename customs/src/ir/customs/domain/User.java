package ir.customs.domain;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public abstract class User {
	@Id
	@GeneratedValue
	private final String nationalID;
	private String password;

	private String firstName;
	private String lastName;
	
	@OneToMany
	protected Map<Access, Boolean> access;

	protected User() {
	}

	public User(String nationalID, String password, String firstName, String lastName) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationalID() {
		return nationalID;
	}	
}
