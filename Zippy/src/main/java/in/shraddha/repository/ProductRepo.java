package in.shraddha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shraddha.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
