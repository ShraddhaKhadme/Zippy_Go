package in.shraddha.service;

import java.util.List;

import in.shraddha.entity.User;

public interface UserService {

	Integer saveUser(User u);
	List<User>getAllUser();
	User getOneUser(int id);
	void delete(int id);
	void update(User u);
	
	
}
