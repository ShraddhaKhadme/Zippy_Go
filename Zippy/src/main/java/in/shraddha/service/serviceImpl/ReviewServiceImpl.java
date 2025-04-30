package in.shraddha.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shraddha.entity.Order;
import in.shraddha.entity.OrderReview;
import in.shraddha.repository.OrderRepo;
import in.shraddha.repository.ReviewRepo;
import in.shraddha.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService{

	@Autowired
    private OrderRepo orderRepository;

    @Autowired
    private ReviewRepo reviewRepo;

    public boolean submitReview(Long orderId, int rating, String message) {
        if (reviewRepo.existsByOrderId(orderId)) return false;

        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null || !"delivered".equals(order.getStatus())) return false;

        OrderReview review = new OrderReview();
        review.setOrder(order);
        review.setRating(rating);
        review.setMessage(message);

        reviewRepo.save(review);
        return true;
    }

	@Override
	public List<OrderReview> getOrderReview() {
		
		return reviewRepo.findAll();
	}

	
}
