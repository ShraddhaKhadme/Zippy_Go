package in.shraddha.service;

public interface ReviewService {

	public boolean submitReview(Long orderId, int rating, String message);
}
