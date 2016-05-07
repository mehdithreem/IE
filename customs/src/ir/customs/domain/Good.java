package ir.customs.domain;

public class Good {
	private String name;
	private Integer weight;
	private Integer count;
	private Integer unitPrice;
	private String producer;
	
	public Good(String name, String producer, Integer weight, Integer count, Integer unitPrice) {
		this.name = name;
		this.weight = weight;
		this.count = count;
		this.unitPrice = unitPrice;
		this.producer = producer;
	}
}
