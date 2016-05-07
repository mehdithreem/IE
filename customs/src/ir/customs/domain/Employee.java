package ir.customs.domain;

public class Employee extends User {

	public Employee(String username, String password, String nationalID, String firstName, String lastName) {
		super(username, password, nationalID, firstName, lastName);
		
		super.access.replace(Access.CreateDeclration, true);
		super.access.replace(Access.ViewDeclration, true);
		super.access.replace(Access.ViewMerchant, true);
	}

}
