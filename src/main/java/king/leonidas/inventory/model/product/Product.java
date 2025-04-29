package king.leonidas.inventory.model.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;


/**
 * Product Model
 */
@Entity
@Getter
@Setter
public class Product {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	/**
	 * Product Name
	 */
	private String name;

	/**
	 * Image URL
	 */
	private String imageUrl;

	/**
	 * Updates that Fields of this instance based from the provided Product
	 * @param source Product to copy fields from
	 */
	public void updateFieldsExceptId(final Product source)
	{
		BeanUtils.copyProperties(source, this, "id");
	}
}
