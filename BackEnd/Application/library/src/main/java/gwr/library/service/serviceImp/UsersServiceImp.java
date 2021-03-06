package gwr.library.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gwr.library.entity.Users;
import gwr.library.repository.UserRepository;
import gwr.library.service.UsersService;

/**
 * The Class UsersServiceImp.
 */
@Service
public class UsersServiceImp implements UsersService {

	/** The user repository. */
	@Autowired
	private UserRepository userRepository;


	/* (non-Javadoc)
	 * @see com.example.library.service.UsersService#getAllUser()
	 */
	@Override
	public List<Users> getAllUser() {
		return userRepository.findAll();
	}
	

	/* (non-Javadoc)
	 * @see com.example.library.service.UsersService#addUser(com.example.library.entity.Users)
	 */
	@Override
	public void addUser(Users entity) {
		userRepository.save(entity);
	}

}
