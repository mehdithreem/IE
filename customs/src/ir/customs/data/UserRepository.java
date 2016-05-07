package ir.customs.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.customs.domain.User;

public class UserRepository {
	private Map<String, User> repo;
	
	private static UserRepository theRepository = new UserRepository();
	public static UserRepository getRepository() {
		return theRepository;
	}
	
	private UserRepository() {
		repo = new HashMap<String, User>();
	}
	
	public void add(String id, User usr) {
		repo.put(id, usr);
	}
	
	public User get(String id) {
		return repo.get(id);
	}
	
	public List<User> getAll() {
		return (List<User>) repo.values();
	}
}