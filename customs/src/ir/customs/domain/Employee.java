package ir.customs.domain;

public class Employee extends User {

	public Employee(String nationalID, String password, String firstName, String lastName) {
		super(nationalID, password, firstName, lastName);
		
		super.access.replace(Access.CreateDeclration, true);
		super.access.replace(Access.ViewDeclration, true);
		super.access.replace(Access.ViewMerchant, true);
	}

}
