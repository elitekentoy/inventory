package king.leonidas.inventory.controller.store_product;

import king.leonidas.inventory.service.product.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Store Product Controller Error Handler
 */
@RestControllerAdvice(assignableTypes = {StoreProductController.class})
public class StoreProductControllerErrorHandler
{

	/**
	 * Handles Product Not Found Exception
	 *
	 * @param exception Product Not Found
	 * @return Message
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String productNotFound(final ProductNotFoundException exception)
	{
		return exception.getLocalizedMessage();
	}
}
