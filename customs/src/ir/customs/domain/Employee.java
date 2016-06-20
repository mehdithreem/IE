package ir.customs.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EMPL")  
public class Employee extends User {

	public Employee(String nationalID, String password, String firstName, String lastName) {
		super(nationalID, password, firstName, lastName);
		
		super.access.replace(Access.CreateDeclration, true);
		super.access.replace(Access.ViewDeclration, true);
		super.access.replace(Access.ViewMerchant, true);
	}
	
	protected Employee() {
		
	}

}
