package ir.customs.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;

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
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> country;
	
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
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> merchantNationalID;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Transport> transportType;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<License> licenses;
	
	@Column(name = "MAX_TOTAL")
	private Integer minTotalValue;
	@Column(name = "MIN_TOTAL")
	private Integer maxTotalValue;
}
