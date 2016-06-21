package ir.customs.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ir.customs.data.DeclarationRepository;
import ir.customs.domain.Agent;
import ir.customs.domain.Declaration;
import ir.customs.domain.Good;
import ir.customs.domain.Transport;
import ir.customs.domain.manager.DeclarationManager;
import ir.customs.domain.manager.LicenseManager;
import ir.customs.domain.manager.RuleManager;
import ir.customs.domain.manager.UserManager;
import ir.customs.tools.ArgumentNotFoundException;

@SuppressWarnings("unused")
public class RuleRelatedLicenseTest {
	private static RuleManager rulem = RuleManager.getManager();
	private static DeclarationManager decm = DeclarationManager.getManager();
	private static LicenseManager licm = LicenseManager.getManager();
	private static UserManager usrm = UserManager.getManager();

	@Test
	public void testGetRelatedLicenses() {
		usrm.createUser(
				"testAgent" /*NID*/,
				null /*firstName*/,
				null /*lastName*/,
				null /*password*/,
				null /*orgName*/,
				Agent.class /*userClass*/);
		
		Integer licid1 = -1;
		try {
			licid1 = licm.createLicense(
					"test license 1" /*title*/,
					10 /*validityDuration*/,
					"testAgent" /*agentID*/);
		} catch (Exception e) {
			fail("Cannot create license");
			e.printStackTrace();
			return;
		}
		
		List<String> rule1Goods = new ArrayList<String>();
		rule1Goods.add("good1");
		rule1Goods.add("good2");
		rule1Goods.add("good3");
		
		List<Integer> rule1Licenses = new ArrayList<Integer>();
		rule1Licenses.add(licid1);
		
		Integer ruleid1 = rulem.submitRule(
				null /*startDate*/, 
				null /*endDate*/, 
				null /*minW*/, 
				null /*maxW*/,
				null /*minCount*/,
				null /*maxCount*/,
				rule1Goods /*goods*/,
				rule1Licenses /*licenseIds*/,
				null /*minValue*/,
				null /*maxValue*/);

		List<Map<String,String>> dec1Goods = new ArrayList<Map<String, String>>();
		
		Map<String,String> good1map = new HashMap<String,String>();
		good1map.put("goodName", "good1");
		good1map.put("goodProducer", "unkown");
		good1map.put("goodWeight", "10");
		good1map.put("goodCount", "10");
		good1map.put("goodUnitPrice", "10");
		
		Map<String,String> good2map = new HashMap<String,String>();
		good2map.put("goodName", "good2");
		good2map.put("goodProducer", "unkown");
		good2map.put("goodWeight", "20");
		good2map.put("goodCount", "20");
		good2map.put("goodUnitPrice", "10");
		
		dec1Goods.add(good1map);
		dec1Goods.add(good2map);
		
		Integer decid1 = -1;
		try {
			decid1 = decm.submitNew(
				"testMerch" /*merchantNID*/,
				null /*merchantFirstName*/,
				null /*merchantLastName*/,
				"China" /*sourceCountry*/,
				Transport.Land.getPersianName() /*transportPersianName*/,
				dec1Goods /*goods*/);
		} catch (ArgumentNotFoundException e) {
			e.printStackTrace();
			fail("Cannot create declaration");
			return;
		}
		
		Declaration dec1 = DeclarationRepository.getRepository().read(decid1);
		
		assertEquals("Required Licenses is not 1", 1, dec1.getRequiredLicenses().size());
		assertEquals("Required License is different", licid1, dec1.getRequiredLicenses().get(0).getId());
	}

}
