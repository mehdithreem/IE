package ir.customs.data;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ir.customs.domain.User;
import ir.customs.tools.HibernateUtils;

public class UserRepository {
	
	
	private static UserRepository theRepository = new UserRepository();
	public static UserRepository getRepository() {
		return theRepository;
	}
	
	public void create(User usr) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(usr);
		tx.commit();
		session.close();
	}
	
	public User read(String nid) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		User usr;
		usr = (User) session.get(User.class, nid);
		session.close();
		return usr;
	}
	
//	public List<User> getAll() {
//		Session session = HibernateUtils.getSessionFactory().openSession();
//		List<User> list = session.createCriteria(User.class).list();
//		session.close();
//		return list;
//	}
	
	public void update(User usr){
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(usr);
		tx.commit();
		session.close();
	}
	
}