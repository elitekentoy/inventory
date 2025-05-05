package king.leonidas.inventory.service.user_store;

import king.leonidas.inventory.model.store.Store;
import king.leonidas.inventory.model.user_store.StoreWithOwner;
import king.leonidas.inventory.service.user_store.decorator.StoreWithOwnerSet;

/**
 * User Store Service Interface
 */
public interface UserStoreService
{
	/**
	 * Find All Stores of a User
	 *
	 * @param username Username of a User
	 * @return Store With Owner Set
	 */
	StoreWithOwnerSet findAllStoresByUsername(String username);

	/**
	 * Find Specific Store of a User
	 *
	 * @param username Username of a User
	 * @param storeId Store ID
	 * @return Store With Owner
	 */
	StoreWithOwner findSpecificStore(String username, int storeId);

	/**
	 * Create Store for a User
	 *
	 * @param store Store to be created
	 * @param username Username of a User
	 */
	void createStore(Store store, String username);

	/**
	 * Delete All Stores of a User
	 *
	 * @param username Username of a User
	 */
	void deleteUserStores(String username);

	/**
	 * Delete Specific Store of a User
	 *
	 * @param username Username of a User
	 * @param storeId Store ID
	 */
	void deleteSpecificStore(String username, int storeId);
}
