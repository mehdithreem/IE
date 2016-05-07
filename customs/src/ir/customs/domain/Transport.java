package ir.customs.domain;

public enum Transport {
	Land("زمینی"),
	Maritime("دریایی"),
	Aerial("هوایی");
	
	private String persianName;
	
	Transport(String name) {
        this.persianName = name;
    }

	public String getPersianName() {
		return persianName;
	}
	
	public static Transport getFromPersianName(String name) {
		for(Transport t : Transport.values()) {
			if(t.getPersianName().equals(name))
				return t;
		}
		return null;
	}
}
