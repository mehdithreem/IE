package ir.customs.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ir.customs.domain.Merchant;

public class MerchantRepository {
	private Map<String, Merchant> repo;
	
	private static MerchantRepository theRepository = new MerchantRepository();
	public static MerchantRepository getRepository() {
		return theRepository;
	}
	
	private MerchantRepository() {
		repo = new HashMap<String, Merchant>();
	}
	
	public void add(String id, Merchant mer) {
		repo.put(id, mer);
	}
	
	public Merchant get(String id) {
		return repo.get(id);
	}
	
	public List<Merchant> getAll() {
		return (List<Merchant>) repo.values();
	}
}