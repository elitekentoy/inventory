package king.leonidas.inventory.dto.user_store;

/**
 * Store With Owner Response
 *
 * @param owner Owner
 * @param storeName Store Name
 * @param storeId Store ID
 * @param type Store Type
 */
public record StoreWithOwnerResponse(String owner, String storeName, int storeId, String type)
{
}
