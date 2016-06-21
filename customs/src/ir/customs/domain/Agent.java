package ir.customs.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AGENT")  
public class Agent extends User {
	@Column(name = "ORG_NAME")
	private String organizationName;

	protected Agent() {
		organizationName = null;
	}

	public String getOrganizationName() {
		return organizationName;
	}
	
	public void setOrganizationName(String name) {
		organizationName = name;
	}

	public Agent(String nationalID, String password,
			String firstName, String lastName) {
		super(nationalID, password, firstName, lastName);
		organizationName = null;
		
		super.access.replace(Access.CreateLicense, true);
		super.access.replace(Access.CreatePermission, true);
		super.access.replace(Access.ViewDeclration, true);
	}
	
	
}
