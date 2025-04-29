package king.leonidas.inventory.service.store;

import king.leonidas.inventory.model.store.Store;
import king.leonidas.inventory.service.store.decorator.StoreSet;


/**
 * Store Service Interface
 */
public interface StoreService
{

	/**
	 * Creates Store
	 *
	 * @param store Store to be created
	 * @return Created Store
	 */
	Store createStore(Store store);

	/**
	 * Find All Stores
	 *
	 * @return All Stores in the Database
	 */
	StoreSet findAllStores();

	/**
	 * Find Store by ID
	 *
	 * @param id ID of the store to be fetched
	 * @return Requested Store
	 */
	Store findStoreById(int id);

	/**
	 * Delete Store By ID
	 *
	 * @param id Store ID to be deleted
	 */
	void deleteStoreById(int id);

	/**
	 * Update Store
	 *
	 * @param store Requested Updated Store
	 * @param id Store ID
	 * @return Updated Store
	 */
	Store updateStore(Store store, int id);
}
