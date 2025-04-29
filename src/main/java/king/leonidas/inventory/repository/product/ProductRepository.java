package king.leonidas.inventory.repository.product;

import king.leonidas.inventory.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product Repository
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{
}
