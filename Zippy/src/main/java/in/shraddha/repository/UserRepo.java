package in.shraddha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.shraddha.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	
	User findByEmail(String email);

	//@Query("select u from User u where u.email=:email")
	boolean existsByEmail(String email); 
	
	
}
