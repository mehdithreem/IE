package ir.customs.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")  
public class Admin extends User {

	public Admin(String nationalID, String password, String firstName, String lastName) {
		super(nationalID, password, firstName, lastName);
		
		for(Access a : Access.values()) {
			super.access.replace(a, true);
		}
	}
	
	protected Admin() {

	}

}
