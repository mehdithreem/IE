package ir.customs.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Permission {
	@Convert(converter = ir.customs.data.LocalDatePersistenceConverter.class)
    @Column(name = "PERMIT_DATE")
	private final LocalDate permitDate;
	
	@Column(name = "TOTAL_VALUE")
	private Integer totalValue;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	
	@ManyToOne
	private Merchant owner;
	@ManyToOne
	private License license;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "TRANSPORT_TYPE")
	private Transport tarsnportType;
	
	@Column(name = "GOOD_NAME")
	private String goodName;
	@Column(name = "GOOD_PRODUCER")
	private String goodProducer;
	@Column(name = "GOOD_COUNT")
	private String goodCount;
	
	protected Permission() {
		permitDate = null;
	}

	public Permission(Merchant owner, License license, Integer totalValue, String country,
			Transport tarsnportType, String goodName, String goodProducer, String goodCount) {
		this.permitDate = LocalDate.now();
		this.owner = owner;
		this.license = license;
		this.totalValue = totalValue;
		this.country = country;
		this.tarsnportType = tarsnportType;
		this.goodName = goodName;
		this.goodProducer = goodProducer;
		this.goodCount = goodCount;
	}

	public LocalDate getPermitDate() {
		return permitDate;
	}

	public Integer getTotalValue() {
		return totalValue;
	}

	public Integer getId() {
		return id;
	}

	public Merchant getOwner() {
		return owner;
	}

	public License getLicense() {
		return license;
	}

	public String getCountry() {
		return country;
	}

	public Transport getTarsnportType() {
		return tarsnportType;
	}

	public String getGoodName() {
		return goodName;
	}

	public String getGoodProducer() {
		return goodProducer;
	}

	public String getGoodCount() {
		return goodCount;
	}
	
	
}
