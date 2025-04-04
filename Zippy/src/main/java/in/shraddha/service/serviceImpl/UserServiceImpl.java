package in.shraddha.service.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shraddha.entity.User;
import in.shraddha.repository.UserRepo;
import in.shraddha.service.UserService;
import jakarta.servlet.http.HttpSession;
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

	@Override
	public String loginUser(String email, String password, HttpSession session) {
		System.out.println(email + "this is in imple");
		String b;
		if ((urepo.findByEmail(email).getEmail().equals(email))
				&& (urepo.findByEmail(email).getPassword().equals(password))) {
			System.out.println("login Succesfull...........");
			session.setAttribute("umail", urepo.findByEmail(email).getEmail());
			session.setAttribute("uname", urepo.findByEmail(email).getName());
			session.setAttribute("uphone", urepo.findByEmail(email).getPhone());
			b = "success";
		} else {
			System.out.println("Login unsuccess..");
			b = "fail";
		}
		return b;

	}

	@Override
	public boolean checkUser(String email) {
		// TODO Auto-generated method stub
		return urepo.existsByEmail(email);
	}
	

}
