package ir.customs.data;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ir.customs.domain.HibernateUtils;
import ir.customs.domain.User;

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
		User usr = (User) session.load(User.class, nid);
		session.close();
		return usr;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<User> list = session.createCriteria(User.class).list();
		session.close();
		return list;
	}
	
	public void update(User usr){
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(usr);
		tx.commit();
		session.close();
	}
}