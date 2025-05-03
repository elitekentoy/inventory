package king.leonidas.inventory.service.store_product;

import king.leonidas.inventory.model.product.Product;
import king.leonidas.inventory.model.product.ProductWithPrice;
import king.leonidas.inventory.model.product.ProductWithPriceProjection;
import king.leonidas.inventory.model.store_product.StoreProduct;
import king.leonidas.inventory.model.store_product.StoreProductId;
import king.leonidas.inventory.repository.product.ProductRepository;
import king.leonidas.inventory.repository.store_product.StoreProductRepository;
import king.leonidas.inventory.service.product.exception.ProductNotFoundException;
import king.leonidas.inventory.service.store_product.decorator.ProductWithPriceSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation Class of {@link StoreProductService}
 */
@Service
public class StoreProductServiceImpl implements StoreProductService
{

	/**
	 * Store Product Repository
	 */
	private final StoreProductRepository storeProductRepo;

	/**
	 * Product Repository
	 */
	private final ProductRepository productRepo;

	/**
	 * Constructor
	 *
	 * @param storeProductRepo Store Product Repository
	 * @param productRepo Product Repository
	 */
	@Autowired
	public StoreProductServiceImpl(final StoreProductRepository storeProductRepo, final ProductRepository productRepo)
	{
		this.storeProductRepo = storeProductRepo;
		this.productRepo = productRepo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductWithPriceSet findAllProductsByStoreId(final int storeId)
	{
		final List<ProductWithPriceProjection> products = storeProductRepo.findProductsWithPriceByStoreId(storeId);
		return new ProductWithPriceSet(products);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductWithPrice findSpecificProductOfStore(final StoreProduct details) {
		final ProductWithPriceProjection existing = storeProductRepo
				.findSpecificProductOfStore(details.getStoreId(), details.getProductId())
				.orElseThrow(ProductNotFoundException::new);
		return new ProductWithPrice(existing);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createProduct(final ProductWithPrice product, final int storeId)
	{
		final Product saved = productRepo.saveAndFlush(product.toProduct());
		storeProductRepo.save(new StoreProduct(storeId, saved.getId(), product.getPrice()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePrice(final StoreProduct details)
	{
		final StoreProduct existing = storeProductRepo
				.findByStoreIdAndProductId(details.getStoreId(), details.getProductId())
				.orElseThrow(ProductNotFoundException::new);
		existing.setPrice(details.getPrice());

		storeProductRepo.save(existing);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteProduct(final StoreProduct details)
	{
		if (!storeProductRepo.existsByStoreIdAndProductId(details.getStoreId(), details.getProductId())) {
			throw new ProductNotFoundException();
		}

		final StoreProductId productId = new StoreProductId(details.getStoreId(), details.getProductId());
		storeProductRepo.deleteById(productId);
	}
}
