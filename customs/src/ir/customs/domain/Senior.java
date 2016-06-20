package ir.customs.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SENIR")  
public class Senior extends User {

	public Senior(String nationalID, String password, String firstName, String lastName) {
		super(nationalID, password, firstName, lastName);
		
		super.access.replace(Access.CreateRule, true);
		super.access.replace(Access.ViewRule, true);
		super.access.replace(Access.ViewMerchant, true);
	}

	protected Senior() {
		
	}
}
