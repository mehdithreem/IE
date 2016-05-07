package ir.customs.domain;

public class Agent extends User {
	private String organizationName;

	public Agent(String username, String password, String nationalID,
			String firstName, String lastName, String organizationName) {
		super(username, password, nationalID, firstName, lastName);
		this.organizationName = organizationName;
		
		super.access.replace(Access.IssuingPermission, true);
	}

}
