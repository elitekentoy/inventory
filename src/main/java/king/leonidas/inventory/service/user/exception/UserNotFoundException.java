package king.leonidas.inventory.service.user.exception;

import java.io.Serial;

/**
 * User Not Found Exception
 */
public class UserNotFoundException extends RuntimeException
{

	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = 8049965674508835061L;

	/**
	 * Constructor
	 */
	public UserNotFoundException()
	{
		super("User Not Found");
	}
}
