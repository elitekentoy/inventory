package king.leonidas.inventory.repository.store_product.queries;

/**
 * Store Product JPQL Queries
 */
public class StoreProductQueries
{

	/**
	 * Base Query (Selects All and Base Join)
	 */
	private static final String BASE_QUERY = "SELECT p.id AS id, p.name AS name, p.imageUrl as imageUrl, sp.price "
			+ "AS price FROM Product p JOIN StoreProduct sp ON p.id = sp.productId WHERE ";

	/**
	 * Match Store IDs
	 */
	private static final String MATCH_STORE_ID = "sp.storeId = :storeId ";

	/**
	 * Match Product IDs
	 */
	private static final String MATCH_PRODUCT_ID = "sp.productId = :productId ";

	/**
	 * And Clause
	 */
	private static final String AND = "AND ";

	/**
	 * Find Products By Store
	 */
	public static final String PRODUCTS_BY_STORE = BASE_QUERY + MATCH_STORE_ID;

	/**
	 * Find Specific Product Of Store
	 */
	public static final String SPECIFIC_PRODUCT_OF_STORE = BASE_QUERY + MATCH_STORE_ID + AND + MATCH_PRODUCT_ID;
}
