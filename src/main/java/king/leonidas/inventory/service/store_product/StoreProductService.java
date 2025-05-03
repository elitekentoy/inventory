package king.leonidas.inventory.service.store_product;

import king.leonidas.inventory.model.product.ProductWithPrice;
import king.leonidas.inventory.model.store_product.StoreProduct;
import king.leonidas.inventory.service.store_product.decorator.ProductWithPriceSet;

/**
 * Store Product Interface
 */
public interface StoreProductService
{
	/**
	 * Find All Products of a Store
	 *
	 * @param storeId Store ID
	 * @return All Products of the specified store
	 */
	ProductWithPriceSet findAllProductsByStoreId(int storeId);

	/**
	 * Find Specific Product Of Store
	 *
	 * @param details Product Details
	 * @return Product with Price
	 */
	ProductWithPrice findSpecificProductOfStore(StoreProduct details);

	/**
	 * Create Product
	 *
	 * @param product Product to be created
	 * @param storeId Store ID
	 */
	void createProduct(ProductWithPrice product, int storeId);

	/**
	 * Update the price of a Product
	 *
	 * @param details Product with updated Price
	 */
	void updatePrice(StoreProduct details);

	/**
	 * Delete Product
	 *
	 * @param details Product to be deleted in the store
	 */
	void deleteProduct(StoreProduct details);
}
