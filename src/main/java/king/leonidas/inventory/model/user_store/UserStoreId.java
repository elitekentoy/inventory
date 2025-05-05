package king.leonidas.inventory.model.user_store;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

/**
 * User Store Composite ID
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserStoreId implements Serializable
{

	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = -1819669272099376341L;

	/**
	 * Store ID
	 */
	@EqualsAndHashCode.Include
	private int storeId;

	/**
	 * User ID
	 */
	@EqualsAndHashCode.Include
	private UUID userId;
}
