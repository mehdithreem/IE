package ir.customs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class License {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "TITLE",unique=true)
	private final String title;
	
	@Column(name = "VALID_DURATION")
	private final Integer validityDuration; // in days
	
	@ManyToOne
	private final Agent issuer;
	
	protected License() {
		validityDuration = null;
		title = null;
		issuer = null;
	}

	public License(String title, Integer validityDuration, Agent issuer) {
		this.title = title;
		this.validityDuration = validityDuration;
		this.issuer = issuer;
	}

	public String getTitle() {
		return title;
	}

	public Integer getValidityDuration() {
		return validityDuration;
	}
	
	public Integer getId() {
		return id;
	}

	public Agent getIssuer() {
		return issuer;
	}
	
	
}
