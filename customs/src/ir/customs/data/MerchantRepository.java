package ir.customs.data;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ir.customs.domain.Merchant;
import ir.customs.domain.HibernateUtils;

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
	
	@SuppressWarnings("unchecked")
	public List<Merchant> getAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Merchant> list = session.createCriteria(Merchant.class).list();
		session.close();
		return list;
	}
	
	public void update(Merchant mer){
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(mer);
		tx.commit();
		session.close();
	}
}