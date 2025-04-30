package in.shraddha.service.serviceImpl;

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
    private ReviewRepo reviewRepository;

    public boolean submitReview(Long orderId, int rating, String message) {
        if (reviewRepository.existsByOrderId(orderId)) return false;

        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null || !"delivered".equals(order.getStatus())) return false;

        OrderReview review = new OrderReview();
        review.setOrder(order);
        review.setRating(rating);
        review.setMessage(message);

        reviewRepository.save(review);
        return true;
    }
}
