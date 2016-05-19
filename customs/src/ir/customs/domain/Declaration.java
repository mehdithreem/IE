package ir.customs.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "DECLARATION")
public class Declaration {
	@Convert(converter = ir.customs.data.LocalDatePersistenceConverter.class)
    @Column(name = "TIME")
	private final LocalDate declareDate;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Status currStatus;
	
	@ManyToOne
	private Merchant merchant;
	
	@ElementCollection
	private Set<Good> goods;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSPORT_TYPE")
	private Transport tarsnportType;
	
	@OneToMany
	private List<License> requiredLicenses;
	@OneToMany
	private List<Permission> issuedPermissions;
	
	protected Declaration() {
		declareDate = null;
	}
	
	public Declaration(Merchant merchant, Set<Good> goods,
			String country, Transport tarsnportType) {
		this.declareDate = LocalDate.now();;
		this.currStatus = Status.PENDING;
		this.merchant = merchant;
		this.goods = goods;
		this.country = country;
		this.tarsnportType = tarsnportType;
		this.requiredLicenses = new ArrayList<License>();
		this.issuedPermissions = new ArrayList<Permission>();
	}

	public Map<String, String> getInfoMap(){
		Map<String,String> retMap = new HashMap<String,String>();
		
		retMap.put("declareDate", declareDate.toString());
		retMap.put("id", String.valueOf(id));
		retMap.put("currStatus", currStatus.getPersianName());
		retMap.put("merchant", merchant.getNationalID());
		retMap.put("country", country);
		
		return retMap;
	}
	
	public void addGood(Good newGood) {
		goods.add(newGood);
	}

	public Status getCurrStatus() {
		return currStatus;
	}

	public void setCurrStatus(Status currStatus) {
		this.currStatus = currStatus;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Permission> getPendingPermission() {
		return issuedPermissions;
	}

	public void setPendingPermission(List<Permission> pendingPermission) {
		this.issuedPermissions = pendingPermission;
	}

	public LocalDate getDeclareDate() {
		return declareDate;
	}

	public Integer getId() {
		return id;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public Set<Good> getGoods() {
		return goods;
	}

	public Transport getTarsnportType() {
		return tarsnportType;
	}

	public List<License> getRequiredLicenses() {
		return requiredLicenses;
	}
	
	public void addReuiredLicense(License l) {
		requiredLicenses.add(l);
	}
	
}
