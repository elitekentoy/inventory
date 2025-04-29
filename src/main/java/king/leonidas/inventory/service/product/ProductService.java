package king.leonidas.inventory.service.product;

import king.leonidas.inventory.model.product.Product;
import king.leonidas.inventory.service.product.decorator.ProductSet;

/**
 * Product Service Interface
 */
public interface ProductService
{

	/**
	 * Create Product
	 *
	 * @param product {@link Product} to be created
	 * @return created {@link Product}
	 */
	Product createProduct(Product product);

	/**
	 * Gets all the products
	 *
	 * @return A Set of {@link Product}
	 */
	ProductSet findAllProducts();

	/**
	 * Find Product By ID
	 *
	 * @param id Product ID
	 * @return Product
	 */
	Product findById(int id);

	/**
	 * Delete Product By ID
	 *
	 * @param id Product ID
	 */
	void deleteById(int id);

	/**
	 * Update Product
	 *
	 * @param product Product to be updated
	 * @param productId Product ID
	 * @return Updated Product
	 */
	Product updateProduct(Product product, int productId);
}
