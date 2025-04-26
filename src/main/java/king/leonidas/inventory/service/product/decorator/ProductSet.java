package king.leonidas.inventory.service.product.decorator;

import king.leonidas.inventory.model.Product;
import org.apache.commons.collections4.set.AbstractSetDecorator;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;

/**
 * Set of {@link Product}
 */
public class ProductSet extends AbstractSetDecorator<Product>
{

	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = 9109906533978669561L;

	/**
	 * Constructor
	 *
	 * @param products Collection of {@link Product}
	 */
	public ProductSet(final Collection<Product> products) {
		super(new HashSet<>(products));
	}

}
