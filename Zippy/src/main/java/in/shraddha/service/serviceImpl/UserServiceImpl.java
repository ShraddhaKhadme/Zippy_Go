package in.shraddha.service.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shraddha.entity.User;
import in.shraddha.repository.UserRepo;
import in.shraddha.service.UserService;
@Service 
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo urepo;
	@Override
	public Integer saveUser(User u) {
		// TODO Auto-generated method stub
		return urepo.save(u).getId();
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return urepo.findAll();
	}

	@Override
	public User getOneUser(int id) {
		// TODO Auto-generated method stub
		return urepo.findById(id).get();
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		urepo.deleteById(id);
	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub
		urepo.save(u);
	}

}
