package king.leonidas.inventory.controller.product;

import king.leonidas.inventory.service.product.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * ProductControllerErrorHandler
 */
@RestControllerAdvice(assignableTypes = {ProductController.class})
public class ProductControllerErrorHandler {

	/**
	 * Handles Product Not Found Exception
	 * @param exception Product Not Found Exception
	 * @return Message
	 */
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String productNotFound(final ProductNotFoundException exception)
	{
		return exception.getLocalizedMessage();
	}
}
