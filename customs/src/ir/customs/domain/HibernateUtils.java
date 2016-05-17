package ir.customs.domain;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!
        try {
        	return new Configuration()
        				.addPackage("ir.customs.domain")
        					.addAnnotatedClass(Admin.class)
        					.addAnnotatedClass(Agent.class)
        					.addAnnotatedClass(Declaration.class)
        					.addAnnotatedClass(Good.class)
        					.addAnnotatedClass(License.class)
        					.addAnnotatedClass(Merchant.class)
        					.addAnnotatedClass(Permission.class)
        					.addAnnotatedClass(User.class)
        				.addResource("hibernate.cfg.xml")
        				.configure()
        				.buildSessionFactory();
        } catch (Exception ex) {
            throw new ExceptionInInitializerError("Unable to start hibernate " + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}


