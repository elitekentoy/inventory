package king.leonidas.inventory.model.store;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import king.leonidas.inventory.dto.user_store.CreateStoreRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;

/**
 * Store Model
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Store
{

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	/**
	 * Store Name
	 */
	private String name;

	/**
	 * Store Type
	 */
	@Enumerated(EnumType.STRING)
	private StoreType type;

	/**
	 * Constructor
	 *
	 * @param source Create Store Request
	 */
	public Store(final CreateStoreRequest source)
	{
		this.name = source.storeName();
		this.type = StoreType.valueOf(source.storeType().toUpperCase(Locale.ROOT));
	}

	/**
	 * Update the fields based from the source
	 *
	 * @param source Store to copy fields from
	 */
	public void updateFields(final Store source)
	{
		this.id = source.id;
		this.name = source.name;
		this.type = source.type;
	}
}
