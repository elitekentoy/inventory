package king.leonidas.inventory.controller.user;

import king.leonidas.inventory.service.user.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * User Controller Error Handler
 */
@RestControllerAdvice(assignableTypes = UserController.class)
public class UserControllerErrorHandler
{

	/**
	 * User Not Found
	 *
	 * @param exception User Not Found Exception
	 * @return Message
	 */
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String userNotFound(final UserNotFoundException exception)
	{
		return exception.getLocalizedMessage();
	}

	/**
	 * Illegal Argument, which usually happens when passing an invalid ID
	 * @return Message
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String illegalArgument()
	{
		return "Invalid ID";
	}
}
