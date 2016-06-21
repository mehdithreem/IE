package ir.customs.data;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ir.customs.domain.Merchant;
import ir.customs.tools.HibernateUtils;

public class MerchantRepository {
	private static MerchantRepository theRepository = new MerchantRepository();
	public static MerchantRepository getRepository() {
		return theRepository;
	}
	
	private MerchantRepository() {
	}
	
	public void create(Merchant mer) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(mer);
		tx.commit();
		session.close();
	}
	
	public Merchant read(String nid) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Merchant mer = (Merchant) session.get(Merchant.class, nid);
		session.close();
		return mer;
	}
	
	public void update(Merchant mer){
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(mer);
		tx.commit();
		session.close();
	}
}