package in.shraddha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.shraddha.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
