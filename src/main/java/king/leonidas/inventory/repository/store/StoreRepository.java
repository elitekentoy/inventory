package king.leonidas.inventory.repository.store;

import king.leonidas.inventory.model.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Store Repository
 */
public interface StoreRepository extends JpaRepository<Store, Integer>
{
}
