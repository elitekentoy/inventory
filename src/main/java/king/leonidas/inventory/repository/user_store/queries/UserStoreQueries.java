package king.leonidas.inventory.repository.user_store.queries;

/**
 * User Store Queries
 */
public class UserStoreQueries
{

	/**
	 * Base Query
	 */
	private static final String BASE_QUERY = "SELECT u.username AS owner, s.id AS storeId, s.type AS storeType, "
			+ "s.name AS storeName FROM app_users u JOIN User_Store us ON u.id = us.user_id JOIN store s ON "
			+ "us.store_id = s.id WHERE ";

	/**
	 * Base Delete
	 */
	private static final String BASE_DELETE = "DELETE FROM User_Store us WHERE ";

	/**
	 * Match User IDs
	 */
	private static final String MATCH_USER_ID = "us.user_id = :userId ";

	/**
	 * Match Store IDs
	 */
	private static final String MATCH_STORE_ID = "us.store_id = :storeId ";

	/**
	 * And Clause
	 */
	private static final String AND = "AND ";

	/**
	 * Find Stores of a User
	 */
	public static final String STORES_OF_USER = BASE_QUERY + MATCH_USER_ID;

	/**
	 * Find Specific Store of User
	 */
	public static final String SPECIFIC_STORE_OF_USER = BASE_QUERY + MATCH_USER_ID + AND + MATCH_STORE_ID;

	/**
	 * Delete All Stores of User
	 */
	public static final String DELETE_STORES_OF_USER = BASE_DELETE + MATCH_USER_ID;

	/**
	 * Delete Specific Store of User Matching Store ID
	 */
	public static final String DELETE_SPECIFIC_STORE_OF_USER = BASE_DELETE + MATCH_USER_ID + AND + MATCH_STORE_ID;

}
