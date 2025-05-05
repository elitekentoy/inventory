package king.leonidas.inventory.repository.user_store;

import king.leonidas.inventory.model.user_store.StoreWithOwnerProjection;
import king.leonidas.inventory.model.user_store.UserStore;
import king.leonidas.inventory.model.user_store.UserStoreId;
import king.leonidas.inventory.repository.user_store.queries.UserStoreQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * User Store Repository
 */
@Repository
public interface UserStoreRepository extends JpaRepository<UserStore, UserStoreId>
{

	/**
	 * Find All Stores of a User
	 *
	 * @param userId User ID
	 * @return Store with Owner
	 */
	@Query(value = UserStoreQueries.STORES_OF_USER, nativeQuery = true)
	List<StoreWithOwnerProjection> findAllStoresByUserID(@Param("userId") UUID userId);

	/**
	 * Find Specific Store
	 *
	 * @param userId User ID
	 * @param storeId Store ID
	 * @return Store with Owner
	 */
	@Query(value = UserStoreQueries.SPECIFIC_STORE_OF_USER, nativeQuery = true)
	Optional<StoreWithOwnerProjection> findSpecificStore(@Param("userId") UUID userId, @Param("storeId")  int storeId);

	/**
	 * Delete All Stores of User
	 *
	 * @param userId User ID
	 */
	@Modifying
	@Query(value = UserStoreQueries.DELETE_STORES_OF_USER, nativeQuery = true)
	void deleteStoresOfUser(@Param("userId") UUID userId);

	/**
	 * Delete Specific Store of User
	 *
	 * @param userId User ID
	 * @param storeId Store ID
	 */
	@Modifying
	@Query(value = UserStoreQueries.DELETE_SPECIFIC_STORE_OF_USER, nativeQuery = true)
	void deleteSpecificStore(@Param("userId") UUID userId, @Param("storeId") int storeId);
}
