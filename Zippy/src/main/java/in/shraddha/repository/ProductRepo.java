package in.shraddha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.shraddha.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	List<Product> findByCategoryId(Integer categoryId);
//	@Query("SELECT p FROM Product p WHERE " +
//		       "(" +
//		       "  (:minPrice IS NOT NULL AND p.pprice >= :minPrice) OR " +
//		       "  (:maxPrice IS NOT NULL AND p.pprice <= :maxPrice) OR " +
//		       "  (:discount IS NOT NULL AND (" +
//		       "    (:discount = 'gt10' AND (p.pdiscount / p.pprice) * 100 > 10) OR " +
//		       "    (:discount = '30to50' AND (p.pdiscount / p.pprice) * 100 BETWEEN 30 AND 50) OR " +
//		       "    (:discount = 'gt50' AND (p.pdiscount / p.pprice) * 100 > 50)" +
//		       "  ))" +
//		       ")")
//		List<Product> filterProducts(
//		    @Param("minPrice") Double minPrice,
//		    @Param("maxPrice") Double maxPrice,
//		    @Param("discount") String discount
//		);
	@Query("SELECT p FROM Product p WHERE "
		     + "(:category IS NOT NULL AND p.category = :category) OR "
		     + "(:minPrice IS NOT NULL AND p.pprice >= :minPrice) OR "
		     + "(:maxPrice IS NOT NULL AND p.pprice <= :maxPrice) OR "
		     + "(:minDiscount IS NOT NULL AND p.pdiscount >= :minDiscount)")
		List<Product> findFilteredProducts(
		    @Param("category") String category,
		    @Param("minPrice") Double minPrice,
		    @Param("maxPrice") Double maxPrice,
		    @Param("minDiscount") Double minDiscount
		);


}
