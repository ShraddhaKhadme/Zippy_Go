package in.shraddha.service.serviceImpl;

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

}
