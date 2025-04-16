package in.shraddha.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shraddha.entity.CartEntity;

public interface CartRepo extends JpaRepository<CartEntity, Integer>{

	Optional<CartEntity> findByUserIdAndProductId(int userId, int productId);
}
