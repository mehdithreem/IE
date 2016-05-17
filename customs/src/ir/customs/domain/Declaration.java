package ir.customs.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "DECLARATION")
public class Declaration {
	@Convert(converter = ir.customs.data.LocalDatePersistenceConverter.class)
    @Column(name = "TIME")
	private final LocalDate declareDate;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private final Integer id;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
	private Status currStatus;
	
	@ManyToOne
	@Column(name = "MERCHANT")
	private Merchant merchant;
	
	@ElementCollection
	private List<Good> goods;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TRANSPORT_TYPE")
	private Transport tarsnportType;
	
	@OneToMany
	private List<Permission> issuedPermission;
	@OneToMany
	private List<Permission> pendingPermission;
	
//	protected Declaration() {
//	}

	public Declaration(Integer newID, Merchant merchant) {
		this.declareDate = LocalDate.now();
		this.id = newID;
	}
	
	public Declaration(LocalDate declareDate, Integer id, Merchant merchant, List<Good> goods,
			String country, Transport tarsnportType) {
		this.declareDate = declareDate;
		this.id = id;
		this.currStatus = Status.PENDING;
		this.merchant = merchant;
		this.goods = goods;
		this.country = country;
		this.tarsnportType = tarsnportType;
		this.issuedPermission = new ArrayList<Permission>();
		this.pendingPermission = new ArrayList<Permission>();
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
		return pendingPermission;
	}

	public void setPendingPermission(List<Permission> pendingPermission) {
		this.pendingPermission = pendingPermission;
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

	public List<Good> getGoods() {
		return goods;
	}

	public Transport getTarsnportType() {
		return tarsnportType;
	}

	public List<Permission> getIssuedPermission() {
		return issuedPermission;
	}
	
	
}
