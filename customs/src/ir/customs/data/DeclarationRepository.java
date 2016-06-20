package ir.customs.data;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ir.customs.domain.Declaration;
import ir.customs.tools.HibernateUtils;

public class DeclarationRepository {
	private static DeclarationRepository theRepository = new DeclarationRepository();
	public static DeclarationRepository getRepository() {
		return theRepository;
	}
	
	private DeclarationRepository() {
	}
	
	public void create(Declaration dec) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(dec);
		tx.commit();
		session.close();
	}
	
	public Declaration read(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Declaration dec = (Declaration) session.get(Declaration.class, id);
		session.close();
		return dec;
	}
	
	public void update(Declaration dec){
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(dec);
		tx.commit();
		session.close();
	}
	
	@SuppressWarnings("unchecked")
	public List<Declaration> getAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Declaration> list = session.createCriteria(Declaration.class).list();
		session.close();
		return list;
	}
}
