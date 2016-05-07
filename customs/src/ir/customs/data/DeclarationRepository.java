package ir.customs.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.customs.domain.Declaration;

public class DeclarationRepository {
	private Map<Integer, Declaration> repo;
	
	private static DeclarationRepository theRepository = new DeclarationRepository();
	public static DeclarationRepository getRepository() {
		return theRepository;
	}
	
	private DeclarationRepository() {
		repo = new HashMap<Integer, Declaration>();
	}
	
	public void add(Integer id, Declaration dec) {
		repo.put(id, dec);
	}
	
	public Declaration get(Integer id) {
		return repo.get(id);
	}
	
	public List<Declaration> getAll() {
		return (List<Declaration>) repo.values();
	}
}
