package ir.customs.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MERCHANT")
public class Merchant {
	@Id
	@Column(name = "NID")
	private final String nationalID;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
	private List<Declaration> declarations;
	
//	protected Merchant() {
//	}

	public Merchant(String nationalID, String firstName, String lastName) {
		this.nationalID = nationalID;
		this.firstName = firstName;
		this.lastName = lastName;
		
		this.declarations = new ArrayList<Declaration>();
	}
	
	public void addDeclaration(Declaration dec) {
		// need changes
		this.declarations.add(dec);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Declaration> getDeclarations() {
		return declarations;
	}

	public void setDeclarations(List<Declaration> declarations) {
		this.declarations = declarations;
	}

	public String getNationalID() {
		return nationalID;
	}
	
	
}
