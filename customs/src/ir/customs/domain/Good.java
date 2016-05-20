package ir.customs.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Good {
	
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
	
	public Map<String, String> getInfoMap() {
		Map<String,String> retMap = new HashMap<String,String>();
		
		retMap.put("goodName", name);
		retMap.put("goodWeight", String.valueOf(weight));
		retMap.put("goodCount", String.valueOf(count));
		retMap.put("goodUnitPrice", String.valueOf(unitPrice));
		retMap.put("goodProducer", producer);
		
		return retMap;
	}

	public Integer getValue() {
		return unitPrice * count;
	}
	
}
