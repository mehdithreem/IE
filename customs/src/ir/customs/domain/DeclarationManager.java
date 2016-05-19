package ir.customs.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ir.customs.data.DeclarationRepository;
import ir.customs.data.LicenseRepository;
import ir.customs.data.MerchantRepository;
import javafx.util.Pair;

public class DeclarationManager {
	
	private static DeclarationManager theManager = new DeclarationManager();
	public static DeclarationManager getManager() {
		return theManager;
	}
	
	public Integer submitNew(
			String merchantNID,
			String merchantFirstName,
			String merchantLastName,
			String sourceCountry,
			String transportPersianName,
			List<Map<String, String>> goods) 
					throws ArgumentNotFoundException {
		
		Set<Good> goodInsts = new HashSet<Good>();
		
		for(Map<String, String> gmap : goods) {
			if(
				gmap.get("goodName") == null ||
				gmap.get("goodProducer") == null ||
				gmap.get("goodWeight") == null ||
				gmap.get("goodCount") == null ||
				gmap.get("goodUnitPrice") == null) {
				throw new ArgumentNotFoundException("good map not complete");
			}
			
			goodInsts.add(new Good(
					gmap.get("goodName"),
					gmap.get("goodProducer"),
					Integer.valueOf(gmap.get("goodWeight")),
					Integer.valueOf(gmap.get("goodCount")),
					Integer.valueOf(gmap.get("goodUnitPrice"))));			
		}
		
		Merchant owner;
		MerchantRepository mrep = MerchantRepository.getRepository();
		
		owner = mrep.read(merchantNID);
		if (owner == null) {
			owner = new Merchant(merchantNID, 
					merchantFirstName == null ? "" : merchantFirstName,
					merchantLastName == null ? "" : merchantLastName);
			mrep.create(owner);
		}
		
		Transport type = Transport.getFromPersianName(transportPersianName);
		
		Declaration fin = new Declaration(owner,goodInsts,sourceCountry, type);
		fin.addReuiredLicense(LicenseRepository.getRepository().read(1));
		fin.addReuiredLicense(LicenseRepository.getRepository().read(2));
				
		DeclarationRepository.getRepository().create(fin);
		mrep = null;

		return fin.getId();
	}
	
	public Integer getDeclarationInfo(
			Integer id, Map<String,String> data,
			List<Map<String,String>> goodsData,
			List<String> requiredLicensesTitles,
			Map<Integer, String> issuedPermission) {
		
		Declaration dec = DeclarationRepository.getRepository().read(id);
		if(dec == null)
			return -1;
		
		data.putAll(dec.getInfoMap());
		
		for(Good g : dec.getGoods()){
			goodsData.add(g.getInfoMap());
		}
		
		for(License l : dec.getRequiredLicenses()){
			requiredLicensesTitles.add(l.getTitle());
		}
		
		for(Permission p : dec.getPendingPermission()){
			Pair<Integer, String> pr = p.getIdLicenseTitlePair();
			issuedPermission.put(pr.getKey(), pr.getValue());
		}
		
		return 0;
	}
	
	public Integer issuePermission(Integer decID, Integer permID) {
		Boolean permFound = false;
		Declaration dec = DeclarationRepository.getRepository().read(decID);
		if(dec == null)
			return -1;
		
		for(Permission p : dec.getPendingPermission()){
			if(p.getId() == permID){
				permFound = true;
			}
		}
		
		return 0;
	}
}
