package king.leonidas.inventory.service.store.decorator;

import king.leonidas.inventory.model.store.Store;
import org.apache.commons.collections4.set.AbstractSetDecorator;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;

/**
 * Product Set
 */
public class StoreSet extends AbstractSetDecorator<Store>
{
	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = -339572933940403050L;

	/**
	 * Constructor
	 *
	 * @param stores Collection of {@link Store}
	 */
	public StoreSet(final Collection<Store> stores)
	{
		super(new HashSet<>(stores));
	}
}
