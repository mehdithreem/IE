package ir.customs.domain;

public class PermissionManager {
	private static PermissionManager theManager = new PermissionManager();
	public static PermissionManager getManager() {
		return theManager;
	}
	
	public void createPermission(
			String merNID,
			Integer licenseID,
			Integer totalValue,
			String country,
			String transportPersianName,
			String goodName,
			String goodProducer,
			String goodCount
			) {
		
	}
}
