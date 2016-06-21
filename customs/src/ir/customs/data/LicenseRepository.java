package ir.customs.data;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ir.customs.domain.License;
import ir.customs.tools.HibernateUtils;

public class LicenseRepository {
	private static LicenseRepository theRepository = new LicenseRepository();
	public static LicenseRepository getRepository() {
		return theRepository;
	}
	
	public void create(License lcs) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(lcs);
		tx.commit();
		session.close();
	}
	
	public License read(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		License lcs;
		lcs = (License) session.get(License.class, id);
		session.close();
		return lcs;
	}

	public List<License> getAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Query query = session.createQuery("from License");
		@SuppressWarnings("unchecked")
		List<License> list = query.list();
		session.close();
		return list;
	}
	
	public void update(License lcs){
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(lcs);
		tx.commit();
		session.close();
	}
}
