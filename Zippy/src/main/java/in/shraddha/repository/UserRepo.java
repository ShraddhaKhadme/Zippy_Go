package in.shraddha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.shraddha.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
