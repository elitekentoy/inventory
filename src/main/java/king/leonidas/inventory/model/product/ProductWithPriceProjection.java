package king.leonidas.inventory.model.product;

/**
 * Product With Price Projection
 */
public interface ProductWithPriceProjection
{

	/**
	 * Get ID
	 * @return Product ID
	 */
	int getId();

	/**
	 * Get Name
	 *
	 * @return Product Name
	 */
	String getName();

	/**
	 * Get Image URL
	 *
	 * @return Image URL
	 */
	String getImageUrl();

	/**
	 * Get Product Price
	 *
	 * @return Product Price
	 */
	Double getPrice();
}
