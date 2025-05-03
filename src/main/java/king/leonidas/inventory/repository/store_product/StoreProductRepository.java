package king.leonidas.inventory.repository.store_product;

import king.leonidas.inventory.model.product.ProductWithPriceProjection;
import king.leonidas.inventory.model.store_product.StoreProduct;
import king.leonidas.inventory.model.store_product.StoreProductId;
import king.leonidas.inventory.repository.store_product.queries.StoreProductQueries;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Store Product Repository
 */
@Repository
public interface StoreProductRepository extends CrudRepository<StoreProduct, StoreProductId>
{

	/**
	 * Fetches Products with its corresponding price depending on the store
	 *
	 * @param storeId Store ID
	 * @return Products with Price
	 */
	@Query(StoreProductQueries.PRODUCTS_BY_STORE)
	List<ProductWithPriceProjection> findProductsWithPriceByStoreId(@Param("storeId") int storeId);

	/**
	 * Fetch one Product with its corresponding price depending on the store and product id
	 *
	 * @param storeId Store ID
	 * @param productId Product ID
	 * @return Product With Price
	 */
	@Query(StoreProductQueries.SPECIFIC_PRODUCT_OF_STORE)
	Optional<ProductWithPriceProjection> findSpecificProductOfStore(
			@Param("storeId") int storeId,
			@Param("productId") int productId);

	/**
	 * Find By Store ID and Product ID
	 *
	 * @param storeId Store ID
	 * @param productId Product ID
	 * @return Store Product
	 */
	Optional<StoreProduct> findByStoreIdAndProductId(int storeId, int productId);

	/**
	 * Checks if a product exists in a store
	 *
	 * @param storeId Store ID
	 * @param productId Product ID
	 * @return True if product exists, otherwise False
	 */
	boolean existsByStoreIdAndProductId(int storeId, int productId);

}
