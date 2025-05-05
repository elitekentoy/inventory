package king.leonidas.inventory.service.user_store.decorator;

import king.leonidas.inventory.dto.user_store.StoreWithOwnerResponse;
import king.leonidas.inventory.model.user_store.StoreWithOwner;
import king.leonidas.inventory.model.user_store.StoreWithOwnerProjection;
import org.apache.commons.collections4.set.AbstractSetDecorator;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Store With Owner Set
 */
public class StoreWithOwnerSet extends AbstractSetDecorator<StoreWithOwner> {

	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = 2525445092534349777L;

	/**
	 * Constructor
	 *
	 * @param stores Collection of Store With Owner
	 */
	public StoreWithOwnerSet(final Collection<StoreWithOwner> stores)
	{
		super(new HashSet<>(stores));
	}

	/**
	 * Constructor
	 *
	 * @param stores List of Store With Owner Projection
	 */
	public StoreWithOwnerSet(final List<StoreWithOwnerProjection> stores)
	{
		super(stores.stream().map(StoreWithOwner::new).collect(Collectors.toSet()));
	}

	/**
	 * Get All Store IDs of the Stores
	 *
	 * @return Set of Store IDs
	 */
	public Set<Integer> getStoreIds()
	{
		return this.stream().map(StoreWithOwner::getStoreId).collect(Collectors.toSet());
	}

	/**
	 * Converts this into Set of Store With Owner Response
	 *
	 * @return Set of Store With Owner Response
	 */
	public Set<StoreWithOwnerResponse> toStoreWithOwnerResponses()
	{
		return this.stream().map(StoreWithOwner::toStoreWithOwnerResponse).collect(Collectors.toSet());
	}
}
