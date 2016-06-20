package ir.customs.tools;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ir.customs.domain.Admin;
import ir.customs.domain.Agent;
import ir.customs.domain.Declaration;
import ir.customs.domain.Employee;
import ir.customs.domain.Good;
import ir.customs.domain.License;
import ir.customs.domain.Merchant;
import ir.customs.domain.Permission;
import ir.customs.domain.Senior;
import ir.customs.domain.User;

public class HibernateUtils {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!
    	try {
	    	StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
	    		    .configure( "hibernate.cfg.xml" )
	    		    .build();

    		Metadata metadata = new MetadataSources( standardRegistry )
    				.addPackage("ir.customs.domain")
						.addAnnotatedClass(Admin.class)
						.addAnnotatedClass(Agent.class)
						.addAnnotatedClass(Senior.class)
						.addAnnotatedClass(Employee.class)
						.addAnnotatedClass(Declaration.class)
						.addAnnotatedClass(Good.class)
						.addAnnotatedClass(License.class)
						.addAnnotatedClass(Merchant.class)
						.addAnnotatedClass(Permission.class)
						.addAnnotatedClass(User.class)
    		    .getMetadataBuilder()
    		    .applyImplicitNamingStrategy( ImplicitNamingStrategyJpaCompliantImpl.INSTANCE )
    		    .build();

        	return metadata.getSessionFactoryBuilder().build();
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


