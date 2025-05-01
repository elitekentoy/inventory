package king.leonidas.inventory.service.store.exception;

import java.io.Serial;

/**
 * Store Not Found Exception
 */
public class StoreNotFoundException extends RuntimeException
{

	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = -1757178834395975545L;

	/**
	 * Constructor
	 */
	public StoreNotFoundException() {
		super("Store not found");
	}
}
