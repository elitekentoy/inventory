package king.leonidas.inventory.service.product.exception;

import java.io.Serial;

/**
 * Product Not Found Exception
 */
public class ProductNotFoundException extends RuntimeException {

	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = -1259439713085849661L;

	/**
	 * Constructor
	 */
	public ProductNotFoundException() {
		super("Product not found");
	}

}
