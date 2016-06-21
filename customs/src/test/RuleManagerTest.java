/**
 * 
 */
package test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ir.customs.domain.Declaration;
import ir.customs.domain.Good;
import ir.customs.domain.License;
import ir.customs.domain.Merchant;
import ir.customs.domain.Transport;
import ir.customs.domain.manager.RuleManager;
import junit.framework.TestCase;

/**
 * @author nahal
 *
 */
public class RuleManagerTest extends TestCase {
	
	public void testFindLicenses(){
		Merchant mer = new Merchant("1234","nahal", "mir");
		Set<Good> goods = new HashSet<Good>();
	
		for(int i=0 ; i<5 ; i+=1){
			Good g = new Good("cotton" , "producer"+i , i , i , i);
			goods.add(g);
		}
		Declaration dec = new Declaration(mer , goods ,"ir", Transport.Land);
		List<License> lls = RuleManager.getManager().getRelatedLicenses(dec);
		assertFalse("list has no element", lls.isEmpty());
		assertEquals("something1", lls.get(0).getTitle());
		assertEquals("something2", lls.get(1).getTitle());
	}
}
