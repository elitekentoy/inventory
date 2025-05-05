package king.leonidas.inventory.model.user_store;

/**
 * Store With Owner Projection
 */
public interface StoreWithOwnerProjection
{
	/**
	 * Get Store ID
	 *
	 * @return Store ID
	 */
	int getStoreId();

	/**
	 * Get Store Type
	 *
	 * @return Store Type
	 */
	String getStoreType();

	/**
	 * Get Store Name
	 *
	 * @return Store Name
	 */
	String getStoreName();

	/**
	 * Get Owner
	 *
	 * @return Owner
	 */
	String getOwner();
}
