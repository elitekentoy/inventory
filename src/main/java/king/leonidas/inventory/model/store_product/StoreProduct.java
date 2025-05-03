package king.leonidas.inventory.model.store_product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Store Product
 */
@Entity
@Table(name = "StoreProduct")
@IdClass(StoreProductId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StoreProduct
{
	/**
	 * Store ID
	 */
	@Id
	@Column(name = "storeId")
	private int storeId;

	/**
	 * Product ID
	 */
	@Id
	@Column(name = "productId")
	private int productId;

	/**
	 * Price
	 */
	@Column(name = "price")
	private Double price;

}
