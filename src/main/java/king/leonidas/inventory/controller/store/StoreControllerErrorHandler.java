package king.leonidas.inventory.controller.store;

import king.leonidas.inventory.service.store.exception.InvalidStoreException;
import king.leonidas.inventory.service.store.exception.StoreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Store Controller Error Handler
 */
@RestControllerAdvice(assignableTypes = {StoreController.class})
public class StoreControllerErrorHandler
{

	/**
	 * Handles Store Not Found Exception
	 *
	 * @param exception Store Not Found Exception
	 * @return Message
	 */
	@ExceptionHandler(StoreNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String storeNotFound(final StoreNotFoundException exception)
	{
		return exception.getLocalizedMessage();
	}

	/**
	 * Handles Invalid Store Exception
	 *
	 * @param exception Invalid Store Exception
	 * @return Message
	 */
	@ExceptionHandler(InvalidStoreException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String invalidStore(final InvalidStoreException exception)
	{
		return exception.getLocalizedMessage();
	}

}
