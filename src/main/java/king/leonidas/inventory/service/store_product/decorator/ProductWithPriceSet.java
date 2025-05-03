package king.leonidas.inventory.service.store_product.decorator;

import king.leonidas.inventory.dto.store_product.StoreProductResponse;
import king.leonidas.inventory.model.product.ProductWithPrice;
import king.leonidas.inventory.model.product.ProductWithPriceProjection;
import org.apache.commons.collections4.set.AbstractSetDecorator;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Product With Price Set
 */
public class ProductWithPriceSet extends AbstractSetDecorator<ProductWithPrice> {

	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = -1579150607787063161L;

	/**
	 * Constructor
	 *
	 * @param products Collection of Products with Price
	 */
	public ProductWithPriceSet(final Collection<ProductWithPrice> products)
	{
		super(new HashSet<>(products));
	}

	/**
	 * Constructor
	 *
	 * @param products List of Product With Price Projections
	 */
	public ProductWithPriceSet(final List<ProductWithPriceProjection> products)
	{
		super(products.stream().map(ProductWithPrice::new).collect(Collectors.toSet()));
	}


	/**
	 * Converts This Product With Price Set into Store Product Response Set
	 * @return Store Product Response Set
	 */
	public Set<StoreProductResponse> storeProductResponseSet()
	{
		return this.stream().map(ProductWithPrice::toStoreProductResponse).collect(Collectors.toSet());
	}
}
