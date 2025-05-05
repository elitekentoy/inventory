package king.leonidas.inventory.model.user_store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * User Store
 */
@Entity
@Table(name = "UserStore")
@IdClass(UserStoreId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserStore
{
	/**
	 * Store ID
	 */
	@Id
	@Column(name = "storeId")
	private int storeId;

	/**
	 * User ID
	 */
	@Id
	@Column(name = "userId")
	private UUID userId;
}
