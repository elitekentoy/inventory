package king.leonidas.inventory.service.store.exception;

import java.io.Serial;

/**
 * Invalid Store Exception
 */
public class InvalidStoreException extends RuntimeException {

	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = -7660541831904796045L;

	/**
	 * Constructor
	 */
	public InvalidStoreException() {
		super("Invalid Store");
	}
}
