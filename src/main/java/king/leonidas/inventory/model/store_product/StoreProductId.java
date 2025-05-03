package king.leonidas.inventory.model.store_product;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StoreProductId implements Serializable
{

	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = -548343520302638236L;

	/**
	 * Store ID
	 */
	@EqualsAndHashCode.Include
	private int storeId;

	/**
	 * Product ID
	 */
	@EqualsAndHashCode.Include
	private int productId;
}
