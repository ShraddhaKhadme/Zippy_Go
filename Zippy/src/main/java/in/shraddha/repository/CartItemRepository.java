package in.shraddha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shraddha.entity.CartItem;
import in.shraddha.entity.Product;
import in.shraddha.entity.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	List<CartItem> findByUser(User user);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
    long countByUser(User user);
}
