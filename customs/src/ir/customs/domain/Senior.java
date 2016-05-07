package ir.customs.domain;

public class Senior extends User {

	public Senior(String username, String password, String nationalID, String firstName, String lastName) {
		super(username, password, nationalID, firstName, lastName);
		
		super.access.replace(Access.CreateRule, true);
		super.access.replace(Access.ViewRule, true);
		super.access.replace(Access.ViewMerchant, true);
	}

}
