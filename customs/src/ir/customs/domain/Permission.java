package ir.customs.domain;

import java.time.LocalDate;

public class Permission {
	private final LocalDate permitDate;
	private Integer totalValue;
	private Integer id;
	private Merchant owner;
	private License license;
	
	private String country;
	private Transport tarsnportType;
	private String goodName;
	private String goodProducer;
	private String goodCount;
	
	public Permission(Integer id, License license, Merchant owner, Integer totalValue) {
		this.permitDate = LocalDate.now();
		this.owner = owner;
		this.totalValue = totalValue;
		this.license = license;
	}
	
}
