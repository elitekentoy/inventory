package king.leonidas.inventory.controller.store_product;

import king.leonidas.inventory.dto.store_product.PriceUpdateRequest;
import king.leonidas.inventory.dto.store_product.StoreProductCreateRequest;
import king.leonidas.inventory.dto.store_product.StoreProductResponse;
import king.leonidas.inventory.model.product.ProductWithPrice;
import king.leonidas.inventory.model.store_product.StoreProduct;
import king.leonidas.inventory.service.store_product.StoreProductService;
import king.leonidas.inventory.service.store_product.decorator.ProductWithPriceSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

/**
 * Store Product Controller
 */
@Controller
@RequestMapping("api/v1/stores/{storeId}/products")
public class StoreProductController
{

	/**
	 * Store Product Service
	 */
	private final StoreProductService storeProductService;

	/**
	 * Constructor
	 *
	 * @param storeProductService Store Product Service
	 */
	@Autowired
	public StoreProductController(final StoreProductService storeProductService)
	{
		this.storeProductService = storeProductService;
	}

	/**
	 * Finds all Products of a store
	 *
	 * @param storeId Store ID
	 * @return All Products of a Store with Price
	 */
	@GetMapping
	public ResponseEntity<Set<StoreProductResponse>> findAllProductsByStore(
			@PathVariable(name = "storeId") final int storeId)
	{
		final ProductWithPriceSet products = storeProductService.findAllProductsByStoreId(storeId);
		final Set<StoreProductResponse> display = products.storeProductResponseSet();

		return ResponseEntity.ok().body(display);
	}

	/**
	 * Find Specific Product of Store
	 *
	 * @param storeId Store ID
	 * @param productId Product ID
	 * @return Product With Price
	 */
	@GetMapping("{productId}")
	public ResponseEntity<StoreProductResponse> findSpecificProductOfStore(
			@PathVariable(name = "storeId") final int storeId,
			@PathVariable(name = "productId") final int productId)
	{
		final StoreProduct details = new StoreProduct(storeId, productId, null);
		final StoreProductResponse display = storeProductService
				.findSpecificProductOfStore(details)
				.toStoreProductResponse();

		return ResponseEntity.ok().body(display);
	}

	/**
	 * Create Products of a Store
	 *
	 * @param request Product With Price
	 * @param storeId Store ID
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody final StoreProductCreateRequest request,
							  @PathVariable(name = "storeId") final int storeId)
	{
		final ProductWithPrice product = new ProductWithPrice(request);
		storeProductService.createProduct(product, storeId);
	}

	/**
	 * Update Price
	 *
	 * @param request Price Update Request
	 * @param storeId Store ID
	 * @param productId Product ID
	 */
	@PutMapping({"productId"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePrice(@RequestBody final PriceUpdateRequest request,
							@PathVariable(name = "storeId") final int storeId,
							@PathVariable(name = "productId") final int productId)
	{
		final StoreProduct details = new StoreProduct(storeId, productId, request.price());
		storeProductService.updatePrice(details);
	}

	/**
	 * Delete Product
	 *
	 * @param productId Product ID
	 * @param storeId Store ID
	 */
	@DeleteMapping("{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable(name = "productId") final int productId,
							  @PathVariable(name = "storeId") final int storeId)
	{
		final StoreProduct details = new StoreProduct(storeId, productId, null);
		storeProductService.deleteProduct(details);
	}
}
