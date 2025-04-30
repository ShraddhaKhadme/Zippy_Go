package in.shraddha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shraddha.entity.OrderReview;

public interface ReviewRepo extends JpaRepository<OrderReview, Long> {
    boolean existsByOrderId(Long orderId);

}
