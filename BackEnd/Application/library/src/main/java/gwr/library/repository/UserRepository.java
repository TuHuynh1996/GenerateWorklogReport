package gwr.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gwr.library.entity.Users;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	
	/**
	 * Find by name.
	 *
	 * @param username the username
	 * @return the users
	 */
	public Users findByUserName(String username);
	

    /**
     * Find by user name like.
     *
     * @param userName the user name
     * @param pageable the pageable
     * @return the page
     */
    public Page<Users> findByUserNameLike(String userName, Pageable pageable);

}
