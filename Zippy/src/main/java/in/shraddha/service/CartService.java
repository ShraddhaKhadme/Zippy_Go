package in.shraddha.service;

import java.util.List;
import java.util.Optional;

import in.shraddha.entity.CartItem;
import in.shraddha.entity.Product;
import in.shraddha.entity.User;

public interface CartService {

	List<CartItem> getAllItems(User  user);
	
	Optional<CartItem> findByUserAndProduct(User user, Product product);
    long countByUser(User user);
    
    public void removeCart(User u);
}
