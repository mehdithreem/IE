package ir.customs.data;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ir.customs.domain.Rule;
import ir.customs.tools.HibernateUtils;

public class RuleRepository {
	private static RuleRepository theRepository = new RuleRepository();
	
	public static RuleRepository getRepository() {
		return theRepository;
	}
	
	public void create(Rule rule) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(rule);
		tx.commit();
		session.close();
	}
	
	public Rule read(String rid) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Rule rule;
		rule = (Rule) session.get(Rule.class, rid);
		session.close();
		return rule;
	}
	
	public void update(Rule rule){
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(rule);
		tx.commit();
		session.close();
	}
	
	public List<Rule> getAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Query query = session.createQuery("from Rule");
		@SuppressWarnings("unchecked")
		List<Rule> list = query.list();
		session.close();
		return list;
	}
}
