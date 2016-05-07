package ir.customs.domain;

public class Admin extends User {

	public Admin(String username, String password, String nationalID, String firstName, String lastName) {
		super(username, password, nationalID, firstName, lastName);
		
		super.access.replace(Access.CreateUser, true);
		super.access.replace(Access.EditUser, true);
		super.access.replace(Access.ViewUser, true);
	}

}
