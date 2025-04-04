package in.shraddha.service;

import java.util.List;

import in.shraddha.entity.User;
import jakarta.servlet.http.HttpSession;

public interface UserService {

	Integer saveUser(User u);
	List<User>getAllUser();
	User getOneUser(int id);
	void delete(int id);
	void update(User u);
	boolean checkUser(String email);
	String loginUser(String email, String password, HttpSession session);
	
}
