package in.shraddha.service;

import java.util.List;

import in.shraddha.entity.Product;

public interface ProductService {

	Integer  saveProduct(Product p);
	List<Product> getProductsByCategoryId(Integer categoryId);
	
	public Product getProduct(Integer id);
	List<Product> filterProducts(Integer subCategoryId, String sort, Integer id);
	
}
