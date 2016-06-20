package ir.customs.domain.manager;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ir.customs.data.DeclarationRepository;
import ir.customs.data.LicenseRepository;
import ir.customs.data.MerchantRepository;
import ir.customs.data.PermissionRepository;
import ir.customs.domain.Declaration;
import ir.customs.domain.Good;
import ir.customs.domain.License;
import ir.customs.domain.Merchant;
import ir.customs.domain.Permission;
import ir.customs.domain.Transport;
import ir.customs.tools.ArgumentNotFoundException;
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
<<<<<<< HEAD
		Boolean permFound = false;
=======
		// return -1 : declaration or permission not found
		// return -2 : corresponding license not found
		// return -3 : merchant is not owner
		// return -4 : permission is expired
		// return -5 : total value
		// return -6 : transport type
		// return -7 : country
		// return -8 : good
		// return -9 : permission already added
		
>>>>>>> d356826ebbe19c698e67a740e1dfe69a59bb56e6
		Declaration dec = DeclarationRepository.getRepository().read(decID);
		if(dec == null)
			return -1;
		
<<<<<<< HEAD
		for(Permission p : dec.getPendingPermission()){
			if(p.getId() == permID){
				permFound = true;
			}
		}
=======
		Permission perm = PermissionRepository.getRepository().read(permID);
		if(perm == null)
			return -1;
		
		if(dec.hasPermission(perm))
			return -9;
		
		Boolean hasLicense = false;
		for(License l : dec.getRequiredLicenses()) {
			if (perm.getLicense().getId().equals(l.getId())) {
				hasLicense = true;
				break;
			}
		}
		if (!hasLicense) {
			return -2;
		}
		
		if(!dec.getMerchant().getNationalID().equals(perm.getOwner().getNationalID()))
			return -3;
		
		if(perm.isExpired())
			return -4;
		
		if(perm.getTotalValue() != null && perm.getTotalValue() > 0)
			if(perm.getTotalValue() < dec.getTotalValue())
				return -5;
		
		if(perm.getTarsnportType() != null)
			if(!perm.getTarsnportType().equals(dec.getTarsnportType()))
				return -6;
		
		if(perm.getCountry() != null && !perm.getCountry().equals(""))
			if(!perm.getCountry().equals(dec.getCountry()))
				return -7;
		
		Good target = null;
		Boolean permHasGood = false;
		if(perm.getGoodName() != null && !perm.getGoodName().equals("")) {
			permHasGood = true;
			for(Good g : dec.getGoods()) {
				if(!g.getName().equals(perm.getGoodName()))
					continue;
				if(perm.getGoodCount() != null && perm.getGoodCount() < g.getCount())
					continue;
				if(perm.getGoodProducer() != null && !perm.getGoodProducer().equals("") &&
						!perm.getGoodProducer().equals(g.getProducer()))
					continue;
				target = g;
			}
		}
		if (target == null && permHasGood)
			return -8;
		
		if (permHasGood) {
			perm.decreaseTotalValue(target.getCount(), target.getUnitPrice());
		}
		dec.addIssuedPermissions(perm);
		PermissionRepository.getRepository().update(perm);
		DeclarationRepository.getRepository().update(dec);
>>>>>>> d356826ebbe19c698e67a740e1dfe69a59bb56e6
		
		return 0;
	}
}
