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
	
	@Column(name = "TITLE")
	private final String title;
	
	@Column(name = "VALID_DURATION")
	private final Integer validityDuration; // in days
	
	@ManyToOne
	private final Agent issuer;
	
//	protected License() {
//	}

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

	public Agent getIssuer() {
		return issuer;
	}
	
	
}
