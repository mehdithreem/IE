package ir.customs.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AGENT")  
public class Agent extends User {
	@Column(name = "ORG_NAME")
	private final String organizationName;

	protected Agent() {
		organizationName = null;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public Agent(String username, String password, String nationalID,
			String firstName, String lastName, String organizationName) {
		super(nationalID, password, firstName, lastName);
		this.organizationName = organizationName;
		
		super.access.replace(Access.IssuingPermission, true);
	}
	
	
}
