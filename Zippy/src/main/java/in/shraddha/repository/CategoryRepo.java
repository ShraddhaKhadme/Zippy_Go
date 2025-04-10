package in.shraddha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shraddha.entity.Category;
import jakarta.transaction.Transactional;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

	public boolean existsByName(String category);
}
