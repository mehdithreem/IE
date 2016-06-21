package ir.customs.domain.manager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;

import ir.customs.data.UserRepository;
import ir.customs.domain.Access;
import ir.customs.domain.Agent;
import ir.customs.domain.User;

public class UserManager {
	private static UserManager theManager = new UserManager();
	public static UserManager getManager() {
		return theManager;
	}
	
	private User activeUser = null;
	
	public Boolean signIn(String username, String password) {
		UserRepository repo = UserRepository.getRepository();
		
		User target = repo.read(username);
		if (target == null || activeUser != null)
			return false;
		
		if (target.checkPassword(password)) {
			activeUser = target;
			return true;
		} else {
			return false;
		}
	}
	
	public Integer createUser(
			String NID,
			String firstName,
			String lastName,
			String password,
			String orgName,
			@SuppressWarnings("rawtypes") Class userClass
			) {
		// -2 : InternalError
		// -1 : duplicate NID
		
		User nuser = null;	
		try {
			nuser = (User) userClass.getConstructors()[0].newInstance(NID, password, firstName, lastName);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			e.printStackTrace();
			System.out.println("Create user failed");
			return -2;
		}
		
		if (userClass.equals(Agent.class))
			((Agent) nuser).setOrganizationName(orgName);
		
		try {
			UserRepository.getRepository().create(nuser);
		} catch (ConstraintViolationException e) {
			System.out.println("duplicate user NID");
			return -1;
		}
		
		return 0;
	}
	
	public Map<String,String> getAgentList() {
		Map<String,String> retmap = new HashMap<String,String>();
		
		for(Agent e : UserRepository.getRepository().getAllAgents()) {
			retmap.put(e.getNationalID(), e.getOrganizationName());
		}
		
		return retmap;
	}
	
	public void signOut() {
		activeUser = null;
	}
	
	public Boolean hasAccess(Access a) {
		if (activeUser == null)
			return false;
		
		return activeUser.hasAcess(a);
	}
}
