package ir.customs.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ir.customs.data.DeclarationRepository;
import ir.customs.data.MerchantRepository;

public class DeclarationManager {
	private Integer idGen = 100;
	
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
		
		List<Good> goodInsts = new ArrayList<Good>();
		
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
		
		owner = mrep.get(merchantNID);
		if (owner == null) {
			owner = new Merchant(merchantNID, 
					merchantFirstName == null ? "" : merchantFirstName,
					merchantLastName == null ? "" : merchantLastName);
			mrep.add(merchantNID, owner);
		}
		
		Transport type = Transport.getFromPersianName(transportPersianName);
		Integer id = generateID();
		
		Declaration fin = new Declaration(LocalDate.now(),id,owner,goodInsts,sourceCountry, type);
		owner.addDeclaration(fin);
		
		DeclarationRepository.getRepository().add(id, fin);
		
		return id;
	}
	
	private Integer generateID() {
		return idGen++;
	}
}
