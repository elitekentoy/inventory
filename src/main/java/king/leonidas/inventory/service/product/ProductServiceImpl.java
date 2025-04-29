package king.leonidas.inventory.service.product;

import king.leonidas.inventory.model.product.Product;
import king.leonidas.inventory.repository.product.ProductRepository;
import king.leonidas.inventory.service.product.decorator.ProductSet;
import king.leonidas.inventory.service.product.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation Class of {@link ProductService}
 */
@Service
public class ProductServiceImpl implements ProductService
{

	/**
	 * Product Repository
	 */
	private final ProductRepository productRepository;

	/**
	 * Constructor
	 *
	 * @param productRepository Product Repository
	 */
	@Autowired
	public ProductServiceImpl(final ProductRepository productRepository)
	{
		this.productRepository = productRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product createProduct(final Product product) {
		return productRepository.saveAndFlush(product);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductSet findAllProducts()
	{
		return new ProductSet(productRepository.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product findById(final int id) {
		return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(final int id) {
		if (!productRepository.existsById(id)) {
			throw new ProductNotFoundException();
		}

		productRepository.deleteById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product updateProduct(final Product product, final int productId) {
		final Product existing = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
		existing.updateFieldsExceptId(product);

		return productRepository.saveAndFlush(existing);
	}

}
