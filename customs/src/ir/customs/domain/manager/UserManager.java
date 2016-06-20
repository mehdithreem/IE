package ir.customs.domain.manager;

import ir.customs.data.UserRepository;
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
	
	public void signOut() {
		activeUser = null;
	}
}
