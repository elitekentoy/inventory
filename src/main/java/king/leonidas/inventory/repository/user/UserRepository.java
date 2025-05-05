package king.leonidas.inventory.repository.user;

import king.leonidas.inventory.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * User Repository
 */
public interface UserRepository extends JpaRepository<User, UUID>
{

	/**
	 * Find User by Username while Ignoring Cases
	 *
	 * @param username Username
	 * @return {@link Optional} of User
	 */
	Optional<User> findByUsernameIgnoreCase(String username);

	/**
	 * Exists by Username
	 *
	 * @param username Username
	 * @return True if user exists, otherwise False
	 */
	boolean existsByUsernameIgnoreCase(String username);

}
