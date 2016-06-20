package ir.customs.domain.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.customs.data.LicenseRepository;
import ir.customs.data.UserRepository;
import ir.customs.domain.Agent;
import ir.customs.domain.License;

public class LicenseManager {
	private static LicenseManager theManager = new LicenseManager();
	public static LicenseManager getManager() {
		return theManager;
	}
	
	public Integer createLicense(
			String title,
			Integer validityDuration,
			String agentID
			) throws Exception {

		Agent agent;
		UserRepository urep = UserRepository.getRepository();
		
		try{
			agent = (Agent)urep.read(agentID);
		}catch(java.lang.ClassCastException e){
			throw ArgumentNotFoundException("User is not an Agent");
		}
		if(agent == null)
			throw ArgumentNotFoundException("Agent not found in db");
		
		License l = new License(title, validityDuration, agent);
		LicenseRepository.getRepository().create(l);
		urep = null;
		
		return l.getId();
	}
	
	private Exception ArgumentNotFoundException(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String ,Integer > getLicenseTitles() {
		LicenseRepository lrep = LicenseRepository.getRepository();
		Map<String ,Integer> rtm = new HashMap<String ,Integer>();
		List<License> ll = lrep.getAll();
		for(License l : ll){
			rtm.put(l.getTitle() , l.getId());
		}
		return rtm;
	}
}
