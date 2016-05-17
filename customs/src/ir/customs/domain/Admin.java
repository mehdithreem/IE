package ir.customs.domain;

public class Admin extends User {

	public Admin(String nationalID, String password, String firstName, String lastName) {
		super(nationalID, password, firstName, lastName);
		
		super.access.replace(Access.CreateUser, true);
		super.access.replace(Access.EditUser, true);
		super.access.replace(Access.ViewUser, true);
	}

}
