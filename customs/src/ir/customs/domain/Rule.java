package ir.customs.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Rule {
	// null values indicated not set
	@Convert(converter = ir.customs.tools.LocalDatePersistenceConverter.class)
    @Column(name = "CREATE_DATE")
	private LocalDate createDate;
	
	@Id
	@GeneratedValue
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
	
	@ManyToMany
	private List<License> licenses;
	
	@Column(name = "MAX_UNIT_PRICE")
	private Integer minUnitPrice;
	@Column(name = "MIN_UNIT_PRICE")
	private Integer maxUnitPrice;
	
	public Rule(LocalDate startDate, LocalDate endDate, Integer minWeight, Integer maxWeight, Integer minCount,
			Integer maxCount, List<String> good, List<License> licenses, Integer minUnitPrice, Integer maxUnitPrice) {
		super();
		createDate = LocalDate.now();
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
	
	protected Rule() {
		
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setMinWeight(Integer minWeight) {
		this.minWeight = minWeight;
	}

	public void setMaxWeight(Integer maxWeight) {
		this.maxWeight = maxWeight;
	}

	public void setMinCount(Integer minCount) {
		this.minCount = minCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public void setGood(List<String> good) {
		this.good = good;
	}

	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
	}

	public void setMinUnitPrice(Integer minUnitPrice) {
		this.minUnitPrice = minUnitPrice;
	}

	public void setMaxUnitPrice(Integer maxUnitPrice) {
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
