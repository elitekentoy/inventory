package king.leonidas.inventory.service.user;

import king.leonidas.inventory.model.user.User;
import king.leonidas.inventory.service.user.decorator.UserSet;

/**
 * User Service Interface
 */
public interface UserService
{

	/**
	 * Find All Users in the database
	 *
	 * @return All Users in the database
	 */
	UserSet findAllUsers();

	/**
	 * Finds User based on ID
	 *
	 * @param username Username
	 * @return Requested User
	 */
	User findUserByUsername(String username);

	/**
	 * Update User
	 *
	 * @param user User with updated values
	 */
	void updateUser(User user);

	/**
	 * Create User
	 *
	 * @param user User to be created
	 * @return User created
	 */
	User createUser(User user);

	/**
	 * Delete User by ID
	 *
	 * @param username Username
	 */
	void deleteUserByUsername(String username);
}
