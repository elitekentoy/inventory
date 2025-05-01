package king.leonidas.inventory.model.store;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Store Model
 */
@Getter
@Setter
@Entity
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
