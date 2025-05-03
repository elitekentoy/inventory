package king.leonidas.inventory.model.product;

import king.leonidas.inventory.dto.store_product.StoreProductCreateRequest;
import king.leonidas.inventory.dto.store_product.StoreProductResponse;
import king.leonidas.inventory.model.store_product.StoreProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Product With Price
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductWithPrice
{
	/**
	 * Product ID
	 */
	private int id;

	/**
	 * Product Name
	 */
	private String name;

	/**
	 * Image URL
	 */
	private String imageUrl;

	/**
	 * Price
	 */
	private Double price;

	/**
	 * Constructor
	 *
	 * @param source Store Product Create Request
	 */
	public ProductWithPrice(final StoreProductCreateRequest source)
	{
		this.name = source.productName();
		this.imageUrl = source.imageUrl();
		this.price = source.price();
	}

	/**
	 * Constructor
	 *
	 * @param source Product With Price Projection
	 */
	public ProductWithPrice(final ProductWithPriceProjection source)
	{
		this.id = source.getId();
		this.name = source.getName();
		this.imageUrl = source.getImageUrl();
		this.price = source.getPrice();
	}

	/**
	 * Converts Product with Price to Product
	 * @return Product
	 */
	public Product toProduct()
	{
		return Product.builder()
				.id(this.id)
				.name(this.name)
				.imageUrl(this.imageUrl)
				.build();
	}

	/**
	 * Converts Product with Price to Store Product
	 *
	 * @param storeId Store ID
	 * @return Store Product Model
	 */
	public StoreProduct toStoreProduct(final int storeId)
	{
		return new StoreProduct(storeId, this.id, this.price);
	}

	/**
	 * Converts Product With Price to Store Product response
	 *
	 * @return Store Product Response
	 */
	public StoreProductResponse toStoreProductResponse()
	{
		return new StoreProductResponse(this.id,  this.name, this.imageUrl, this.price);
	}
}
