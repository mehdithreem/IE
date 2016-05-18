package ir.customs.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Embeddable
public class Good {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "NAME")
	private final String name;
	
	@Column(name = "WEIGHT")
	private final Integer weight;
	
	@Column(name = "CNT")
	private final Integer count;
	
	@Column(name = "UNIT_PRICE")
	private final Integer unitPrice;
	
	@Column(name = "PRODUCER")
	private final String producer;
	
	protected Good() {
		name = null;
		weight = null;
		count = null;
		producer = null;
		unitPrice = null;
	}

	public Good(String name, String producer, Integer weight, Integer count, Integer unitPrice) {
		this.name = name;
		this.weight = weight;
		this.count = count;
		this.unitPrice = unitPrice;
		this.producer = producer;
	}

	public String getName() {
		return name;
	}

	public Integer getWeight() {
		return weight;
	}

	public Integer getCount() {
		return count;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public String getProducer() {
		return producer;
	}
	
}
