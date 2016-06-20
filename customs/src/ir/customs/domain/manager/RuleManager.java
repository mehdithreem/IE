package ir.customs.domain.manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ir.customs.data.LicenseRepository;
import ir.customs.data.RuleRepository;
import ir.customs.domain.Declaration;
import ir.customs.domain.Good;
import ir.customs.domain.License;
import ir.customs.domain.Rule;

public class RuleManager {
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
	
	public List<License> getRelatedLicenses(Declaration dec){
		List <License> ret = new ArrayList<License>();
			List <Rule> rl = RuleRepository.getRepository().getAll();
			LocalDate decDate = dec.getDeclareDate();
			Set <Good> decGoods = dec.getGoods();
			for(Rule r : rl){
				
				LocalDate rSDate = r.getStartDate();
				LocalDate rEDate = r.getEndDate();
				if( (rSDate!=null && decDate.isBefore(rSDate)) || (rEDate!=null && decDate.isAfter(rEDate)) )
					continue;
				
				
					
			}
		return ret;
	}
}
