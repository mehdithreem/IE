package ir.customs.domain;

public enum Status {
	PENDING("در حال بررسی"),
	DONE("اخذ شده");
	
	private String persianName;
	
	Status(String name) {
        this.persianName = name;
    }

	public String getPersianName() {
		return persianName;
	}
}
