package ir.customs.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Declaration {
	private final LocalDate declareDate;
	private final Integer id;
	private Status currStatus;
	
	private Merchant merchant;
	private List<Good> goods;
	private String country;
	private Transport tarsnportType;
	
	private List<Permission> issuedPermission;
	private List<Permission> pendingPermission;
	
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
	
	
}
