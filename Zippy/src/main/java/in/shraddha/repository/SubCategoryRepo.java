package in.shraddha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shraddha.entity.SubCategory;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Integer>{

	List<SubCategory> findByCategoryId(Integer categoryId);
}
