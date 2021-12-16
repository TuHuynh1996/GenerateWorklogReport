package gwr.application.service.admin.user;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import gwr.application.model.user.UserRequest;
import gwr.application.model.user.UserResponse;
import gwr.library.entity.Users;
import gwr.library.repository.UserRepository;

/**
 * The Class UsersService.
 */
@Service
public class UsersService {

    /** The user repository. */
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Gets the users.
     *
     * @param request  the request
     * @param pageable the pageable
     * @return the users
     */
    public Page<UserResponse> getUsers(UserRequest request, Pageable pageable) {
        Page<Users> users;
        if (request.getUserName() == null) {
            users = userRepository.findAll(pageable);
        } else {
            users = userRepository.findByUserNameLike(request.getUserName(), pageable);
        }

        return users.map(e -> {
            UserResponse item = modelMapper.map(e, UserResponse.class);
            item.setRoles(e.getRoles().stream().map(rl -> {
                return rl.getName();
            }).collect(Collectors.toList()));
            return item;
        });
    }

    /**
     * Gets the all user.
     *
     * @return the all user
     */
    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    /**
     * Adds the user.
     *
     * @param entity the entity
     */
    public void addUser(Users entity) {
        userRepository.save(entity);
    }

}
