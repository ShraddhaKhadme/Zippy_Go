package in.shraddha.service;

import java.util.List;
import java.util.Optional;

import in.shraddha.entity.OrderReview;

public interface ReviewService {

	public boolean submitReview(Long orderId, int rating, String message);

	public List<OrderReview> getOrderReview();

}
