package ir.customs.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public abstract class User {
	@Id
	@GeneratedValue
	@Column( name = "NID")
	private final String nationalID;
	
	@Column( name = "PASSWORD")
	private String password;
	
	@Column( name = "FIRST_NAME")
	private String firstName;
	
	@Column( name = "LAST_NAME")
	private String lastName;
	
	@ElementCollection
    @CollectionTable(name = "ACCESS_LIST")
    @MapKeyColumn(name = "ACCESS")
    @Column(name = "ACC_BOOLEAN")
	protected Map<Access, Boolean> access;

//	protected User() {
//	}

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
