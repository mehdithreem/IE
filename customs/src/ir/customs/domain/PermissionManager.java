package ir.customs.domain;

import ir.customs.data.LicenseRepository;
import ir.customs.data.MerchantRepository;
import ir.customs.data.PermissionRepository;

public class PermissionManager {
	private static PermissionManager theManager = new PermissionManager();
	public static PermissionManager getManager() {
		return theManager;
	}
	
	public Integer createNew(
			String merNID,
			String merFirstName,
			String merLastName,
			Integer licenseID,
			Integer totalValue,
			String country,
			String transportPersianName,
			String goodName,
			String goodProducer,
			Integer goodCount
			) {
		
		Merchant owner;
		MerchantRepository mrep = MerchantRepository.getRepository();
		
		owner = mrep.read(merNID);
		if (owner == null) {
			owner = new Merchant(merNID,
					merFirstName == null ? "" : merFirstName,
					merLastName == null ? "" : merLastName);
			mrep.create(owner);
		}
		
		Transport type = Transport.getFromPersianName(transportPersianName);
		
		License license;
		LicenseRepository lrep = LicenseRepository.getRepository();
		
		license =lrep.read(licenseID);
		if (license == null)
			return -1;
		
		Permission prm = new Permission(owner, license, totalValue, country, 
										type, goodName, goodProducer, goodCount);
		
		PermissionRepository.getRepository().create(prm);
		mrep = null;
		lrep = null;
		
		return prm.getId();
	}
}
