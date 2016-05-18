package ir.customs.data;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ir.customs.domain.HibernateUtils;
import ir.customs.domain.Permission;

public class PermissionRepository {
	
	private static PermissionRepository theRepository = new PermissionRepository();
	public static PermissionRepository getRepository() {
		return theRepository;
	}
	
	public void create(Permission prm) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(prm);
		tx.commit();
		session.close();
	}
	
	public Permission read(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Permission prm;
		prm = (Permission) session.get(Permission.class, id);
		session.close();
		return prm;
	}
	
	@SuppressWarnings("unchecked")
	public List<Permission> getAll() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		List<Permission> list = session.createCriteria(Permission.class).list();
		session.close();
		return list;
	}
	
	public void update(Permission prm){
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(prm);
		tx.commit();
		session.close();
	}
}
