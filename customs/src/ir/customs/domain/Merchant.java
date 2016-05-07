package ir.customs.domain;

import java.util.ArrayList;
import java.util.List;

public class Merchant {
	private final String nationalID;
	private String firstName;
	private String lastName;
	private List<Declaration> declarations;
	
	public Merchant(String nationalID, String firstName, String lastName) {
		this.nationalID = nationalID;
		this.firstName = firstName;
		this.lastName = lastName;
		
		this.declarations = new ArrayList<Declaration>();
	}
	
	public void addDeclaration(Declaration dec) {
		this.declarations.add(dec);
	}
}
