package king.leonidas.inventory.model.user_store;

import king.leonidas.inventory.dto.user_store.StoreWithOwnerResponse;
import king.leonidas.inventory.model.store.StoreType;
import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

/**
 * Store with Owner
 */
@Getter
@Setter
public class StoreWithOwner
{

	/**
	 * Owner
	 */
	private String owner;

	/**
	 * Store ID
	 */
	private int storeId;

	/**
	 * Store Type
	 */
	private StoreType type;

	/**
	 * Store Name
	 */
	private String storeName;

	/**
	 * Constructor
	 *
	 * @param source Store With Owner Projection
	 */
	public StoreWithOwner(final StoreWithOwnerProjection source)
	{
		this.owner = source.getOwner();
		this.storeId = source.getStoreId();
		this.type = StoreType.valueOf(source.getStoreType().toUpperCase(Locale.ROOT));
		this.storeName = source.getStoreName();
	}

	/**
	 * Converts this to Store With Owner Response
	 * @return Store With Owner Response
	 */
	public StoreWithOwnerResponse toStoreWithOwnerResponse()
	{
		return new StoreWithOwnerResponse(this.owner, this.storeName, this.storeId, this.type.name());
	}
}
