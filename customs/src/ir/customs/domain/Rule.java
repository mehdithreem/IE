package ir.customs.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Rule {
	@Convert(converter = ir.customs.tools.LocalDatePersistenceConverter.class)
    @Column(name = "CREATE_DATE")
	private LocalDate createDate;
	
	@Column(name = "RULE_ID")
	private Integer id;
	
	@Convert(converter = ir.customs.tools.LocalDatePersistenceConverter.class)
    @Column(name = "START_DATE")
	private LocalDate startDate;
	@Convert(converter = ir.customs.tools.LocalDatePersistenceConverter.class)
    @Column(name = "END_DATE")
	private LocalDate endDate;
	
	@Column(name = "MIN_W")
	private Integer minWeight;
	@Column(name = "MAX_W")
	private Integer maxWeight;
	
	@Column(name = "MIN_C")
	private Integer minCount;
	@Column(name = "MAX_C")
	private Integer maxCount;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> good;
	
	@OneToMany
	private List<License> licenses;
	
	@Column(name = "MAX_UNIT_PRICE")
	private Integer minUnitPrice;
	@Column(name = "MIN_UNIT_PRICE")
	private Integer maxUnitPrice;
	
	public Rule(LocalDate startDate, LocalDate endDate, Integer minWeight, Integer maxWeight, Integer minCount,
			Integer maxCount, List<String> good, List<License> licenses, Integer minUnitPrice, Integer maxUnitPrice) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.minWeight = minWeight;
		this.maxWeight = maxWeight;
		this.minCount = minCount;
		this.maxCount = maxCount;
		this.good = good;
		this.licenses = licenses;
		this.minUnitPrice = minUnitPrice;
		this.maxUnitPrice = maxUnitPrice;
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public Integer getMinWeight() {
		return minWeight;
	}

	public Integer getMaxWeight() {
		return maxWeight;
	}

	public Integer getMinCount() {
		return minCount;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public List<String> getGood() {
		return good;
	}

	public List<License> getLicenses() {
		return licenses;
	}

	public Integer getMinUnitPrice() {
		return minUnitPrice;
	}

	public Integer getMaxUnitPrice() {
		return maxUnitPrice;
	}
	
}
