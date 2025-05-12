package king.leonidas.inventory.service.user;

import king.leonidas.inventory.model.user.User;
import king.leonidas.inventory.repository.user.UserRepository;
import king.leonidas.inventory.service.user.decorator.UserSet;
import king.leonidas.inventory.service.user.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation Class of {@link UserService}
 */
@Service
public class UserServiceImpl implements UserService
{
	/**
	 * User Repository
	 */
	private final UserRepository userRepo;

	/**
	 * Constructor
	 *
	 * @param userRepo User Repository
	 */
	public UserServiceImpl(final UserRepository userRepo)
	{
		this.userRepo = userRepo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserSet findAllUsers()
	{
		return new UserSet(userRepo.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findUserByUsername(final String username)
	{
		return userRepo.findByUsernameIgnoreCase(username).orElseThrow(UserNotFoundException::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUser(final User user)
	{
		final User existing = findUserByUsername(user.getUsername());
		existing.copyFieldsExceptID(user);
		userRepo.saveAndFlush(existing);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User createUser(final User user)
	{
		return userRepo.saveAndFlush(user);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteUserByUsername(final String username)
	{
		final User existing = findUserByUsername(username);
		userRepo.deleteById(existing.getId());
	}
}
