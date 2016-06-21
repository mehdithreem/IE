package ir.customs.domain.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ir.customs.data.LicenseRepository;
import ir.customs.data.RuleRepository;
import ir.customs.domain.Declaration;
import ir.customs.domain.Good;
import ir.customs.domain.License;
import ir.customs.domain.Rule;

public class RuleManager {
	private int inf = Integer.MAX_VALUE;
	private static RuleManager theManager = new RuleManager();
	public static RuleManager getManager() {
		return theManager;
	}
	
	public Integer submitRule(
			LocalDate startDate ,
			LocalDate endDate ,
			Integer minW ,
			Integer maxW ,
			Integer minCount ,
			Integer maxCount ,
			List<String> goods ,
			List<Integer> licenseIds ,
			Integer minValue ,
			Integer maxValue) {
		
		List<String> gds =  new ArrayList<String>();
		if (goods != null)
			gds.addAll(goods);
		
		List<License> lcs =  new ArrayList<License>();;
		if (licenseIds != null)
			for(Integer id : licenseIds )
				lcs.add(LicenseRepository.getRepository().read(id));
		
		Rule rule = new Rule(startDate, endDate, minW, maxW, minCount, maxCount, 
							gds, lcs, minValue, maxValue);
		RuleRepository.getRepository().create(rule);
		return rule.getId();
	}
	
	public Collection<License> getRelatedLicenses(Declaration dec){
		List <License> ret = new ArrayList<License>();
		List <Rule> rl = RuleRepository.getRepository().getAll();
//		System.out.println("# of loaded rules : " + rl.size());
		LocalDate decDate = dec.getDeclareDate();
		Set <Good> decGoods = dec.getGoods();
		for(Rule r : rl){
			LocalDate rSDate = r.getStartDate();
			LocalDate rEDate = r.getEndDate();
//			System.out.println("rule id :" + r.getId().toString());
			if( (rSDate!=null && decDate.isBefore(rSDate)) || (rEDate!=null && decDate.isAfter(rEDate)) )
				continue;
			
//			System.out.println("-- date range passed");
			Integer maxC = (r.getMaxCount() ==null) ? inf : r.getMaxCount();
			Integer minC = (r.getMinCount() ==null) ? 0 : r.getMinCount();
			Integer maxUP = (r.getMaxUnitPrice() ==null) ? inf : r.getMaxUnitPrice();
			Integer minUP = (r.getMinUnitPrice() ==null) ? 0 : r.getMinUnitPrice();
			Integer maxW = (r.getMaxWeight() ==null) ? inf : r.getMaxWeight();
			Integer minW = (r.getMinWeight() ==null) ? 0 : r.getMinWeight();
			
//			System.out.println("count " + minC.toString() + " -> " + maxC.toString());
//			System.out.println("weight " + minW.toString() + " -> " + maxW.toString());
//			System.out.println("unit price " + minUP.toString() + " -> " + maxUP.toString());

			List <String> gl = r.getGood();
			for(String rg : gl)
				for(Good dg : decGoods) {
					if(dg.getName().equals(rg)){
//						System.out.println("rule: " + rg + " dec: " + dg.getName());
						if( ( dg.getCount() <= maxC ) && (dg.getCount() >= minC ) &&
							(dg.getUnitPrice() <= maxUP ) && (dg.getUnitPrice() >= minUP ) &&
							(dg.getWeight() <= maxW ) && (dg.getWeight() >= minW )) {
							ret.addAll(r.getLicenses());	
//							System.out.println("licenses added, count: " + r.getLicenses().size());
						}
					}
				}
		}
		return new HashSet<License>(ret);
	}
}
