package in.shraddha.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shraddha.entity.Product;
import in.shraddha.repository.ProductRepo;
import in.shraddha.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepo prepo;
	@Override
	public Integer saveProduct(Product p) {
		// TODO Auto-generated method stub
		return prepo.save(p).getId();
	}
	@Override
	public List<Product> getProductsByCategoryId(Integer categoryId) {
		// TODO Auto-generated method stub
		return prepo.findByCategoryId(categoryId);
		
	}
	@Override
	public Product getProduct(Integer id) {
		// TODO Auto-generated method stub
		return prepo.findById(id).get();
	}
//	@Override
//    public List<Product> getFilteredProducts(Double minPrice, Double maxPrice, String discount) {
//        return prepo.filterProducts(minPrice, maxPrice, discount);
//    }
	 @Override
	    public List<Product> getFilteredProducts(String category, Double minPrice, Double maxPrice, Double minDiscount) {
	        return prepo.findFilteredProducts(category, minPrice, maxPrice, minDiscount);
	    }


}
