package in.shraddha.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import in.shraddha.entity.CartItem;
import in.shraddha.entity.Product;
import in.shraddha.entity.User;
import in.shraddha.repository.CartItemRepository;
import in.shraddha.service.CartService;

public class CartServiceImpl implements CartService{

	@Autowired
	private CartItemRepository crepo;
	@Override
	public List<CartItem> getAllItems(User user) {
		// TODO Auto-generated method stub
		return crepo.findByUser(user);
	}
	@Override
	public Optional<CartItem> findByUserAndProduct(User user, Product product) {
		// TODO Auto-generated method stub
		return crepo.findByUserAndProduct(user, product);
	}
	@Override
	public long countByUser(User user) {
		// TODO Auto-generated method stub
		return crepo.countByUser(user);
	}
	@Override
	public void removeCart(User u) {
		crepo.deleteAll();
		
	}

}
