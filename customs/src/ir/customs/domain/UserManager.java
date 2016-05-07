package ir.customs.domain;

import ir.customs.data.UserRepository;

public class UserManager {
	private static UserManager theManager = new UserManager();
	public static UserManager getManager() {
		return theManager;
	}
	
	private User activeUser = null;
	
	public Boolean signIn(String username, String password) {
		UserRepository repo = UserRepository.getRepository();
		
		User target = repo.get(username);
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
