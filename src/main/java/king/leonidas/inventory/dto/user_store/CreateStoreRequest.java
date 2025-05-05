package king.leonidas.inventory.dto.user_store;

/**
 * Create Store Request
 *
 * @param storeName Store Name
 * @param storeType Store Type
 */
public record CreateStoreRequest(String storeName, String storeType)
{
}
