package king.leonidas.inventory.service.user_store;

import king.leonidas.inventory.model.store.Store;
import king.leonidas.inventory.model.user.User;
import king.leonidas.inventory.model.user_store.StoreWithOwner;
import king.leonidas.inventory.model.user_store.StoreWithOwnerProjection;
import king.leonidas.inventory.model.user_store.UserStore;
import king.leonidas.inventory.repository.store.StoreRepository;
import king.leonidas.inventory.repository.user.UserRepository;
import king.leonidas.inventory.repository.user_store.UserStoreRepository;
import king.leonidas.inventory.service.store.exception.StoreNotFoundException;
import king.leonidas.inventory.service.user.exception.UserNotFoundException;
import king.leonidas.inventory.service.user_store.decorator.StoreWithOwnerSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation Class of {@link UserStoreService}
 */
@Service
public class UserStoreServiceImpl implements UserStoreService
{

	/**
	 * User Store Repository
	 */
	private final UserStoreRepository userStoreRepo;

	/**
	 * User Repository
	 */
	private final UserRepository userRepo;

	/**
	 * Store Repository
	 */
	private final StoreRepository storeRepo;

	/**
	 * User Store Service Impl
	 *
	 * @param userStoreRepo User Stores Repository
	 * @param userRepo User Repository
	 * @param storeRepo Store Repository
	 */
	public UserStoreServiceImpl(final UserStoreRepository userStoreRepo, final UserRepository userRepo,
								final StoreRepository storeRepo)
	{
		this.userStoreRepo = userStoreRepo;
		this.userRepo = userRepo;
		this.storeRepo = storeRepo;
	}

	/**
	 * Find All Stores of a User
	 *
	 * @param username Username of a User
	 * @return Stores with its Owner
	 */
	@Override
	public StoreWithOwnerSet findAllStoresByUsername(final String username)
	{
		final User user = userRepo.findByUsernameIgnoreCase(username).orElseThrow(UserNotFoundException::new);
		final List<StoreWithOwnerProjection> stores = userStoreRepo.findAllStoresByUserID(user.getId());
		return new StoreWithOwnerSet(stores);
	}

	/**
	 * Find Specific Store of a User
	 *
	 * @param username Username of a User
	 * @param storeId  Store ID
	 * @return Store with Owner
	 */
	@Override
	public StoreWithOwner findSpecificStore(final String username, final int storeId)
	{
		final User user = userRepo.findByUsernameIgnoreCase(username).orElseThrow(UserNotFoundException::new);
		final StoreWithOwnerProjection store = userStoreRepo
				.findSpecificStore(user.getId(), storeId)
				.orElseThrow(StoreNotFoundException::new);

		return new StoreWithOwner(store);
	}

	/**
	 * Create Store for a User
	 *
	 * @param store    Store to be created
	 * @param username Username of a User
	 */
	@Override
	public void createStore(final Store store, final String username) {
		final User user = userRepo.findByUsernameIgnoreCase(username).orElseThrow(UserNotFoundException::new);
		final Store saved = storeRepo.saveAndFlush(store);

		final UserStore record = new UserStore(saved.getId(), user.getId());
		userStoreRepo.saveAndFlush(record);

	}

	/**
	 * Delete All Stores of a User
	 *
	 * @param username Username of a User
	 */
	@Transactional
	@Override
	public void deleteUserStores(final String username) {
		final User user = userRepo.findByUsernameIgnoreCase(username).orElseThrow(UserNotFoundException::new);
		final StoreWithOwnerSet stores = findAllStoresByUsername(username);

		userStoreRepo.deleteStoresOfUser(user.getId());
		storeRepo.deleteAllById(stores.getStoreIds());
	}

	/**
	 * Delete Specific Store of a User
	 *
	 * @param username Username of a User
	 * @param storeId  Store ID
	 */
	@Transactional
	@Override
	public void deleteSpecificStore(final String username, final int storeId) {
		final User user = userRepo.findByUsernameIgnoreCase(username).orElseThrow(UserNotFoundException::new);
		if (!storeRepo.existsById(storeId)) {
			throw new StoreNotFoundException();
		}

		userStoreRepo.deleteSpecificStore(user.getId(), storeId);
		storeRepo.deleteById(storeId);
	}
}
