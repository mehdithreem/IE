package ir.customs.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
public class Good {
	@Column(name = "NAME")
	private final String name;
	
	@Column(name = "WEIGHT")
	private final Integer weight;
	
	@Column(name = "COUNT")
	private final Integer count;
	
	@Column(name = "UNIT_PRICE")
	private final Integer unitPrice;
	
	@Column(name = "PRODUCER")
	private final String producer;
	
//	protected Good() {
//	}

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
