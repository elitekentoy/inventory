package king.leonidas.inventory.controller.product;

import king.leonidas.inventory.model.product.Product;
import king.leonidas.inventory.service.product.ProductService;
import king.leonidas.inventory.service.product.decorator.ProductSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Product Controller
 */
@RestController
@RequestMapping(value = "api/v1/products")
public class ProductController {

	/**
	 * Product Service
	 */
	private final ProductService productService;

	/**
	 * Constructor
	 *
	 * @param productService Service Interface for Product
	 */
	@Autowired
	public ProductController(final ProductService productService)
	{
		this.productService = productService;
	}

	/**
	 * Create Product
	 *
	 * @param product {@link Product} to be created
	 * @return Created {@link Product}
	 */
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody final Product product)
	{
		return ResponseEntity.ok()
				.body(productService.createProduct(product));
	}

	/**
	 * Gets all the Product available in the database
	 *
	 * @return Set of all the {@link Product}s
	 */
	@GetMapping
	public ResponseEntity<ProductSet> findALl()
	{
		return ResponseEntity.ok()
				.body(productService.findAllProducts());
	}

	/**
	 * Find Product By ID
	 *
	 * @param productId Product ID to find
	 * @return OK Response
	 */
	@GetMapping(path = "{id}")
	public ResponseEntity<Product> findById(@PathVariable(name = "id") final int productId)
	{
		return ResponseEntity.ok().body(productService.findById(productId));
	}

	/**
	 * Delete Product By ID
	 *
	 * @param productId Product ID to be deleted
	 * @return OK Response
	 */
	@DeleteMapping(path = "{id}")
	public ResponseEntity<?> delete(@PathVariable(name = "id") final int productId)
	{
		productService.deleteById(productId);
		return ResponseEntity.ok().body(null);
	}

	/**
	 * Update Product
	 *
	 * @param product Product to Update
	 * @param productId Product ID to be updated
	 * @return Updated Product
	 */
	@PutMapping(path = "{id}")
	public ResponseEntity<Product> update(@RequestBody final Product product, @PathVariable(name = "id") final int productId)
	{
		return ResponseEntity.ok().body(productService.updateProduct(product, productId));
	}
}
