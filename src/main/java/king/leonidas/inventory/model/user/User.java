package king.leonidas.inventory.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import king.leonidas.inventory.dto.user.UserDetailsRequest;
import king.leonidas.inventory.dto.user.UserDisplayResponse;
import king.leonidas.inventory.model.role.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "app_users")
@NoArgsConstructor
@AllArgsConstructor
public class User
{

	/**
	 * Id
	 */
	@Id
	private UUID id;

	/**
	 * Username
	 */
	@Column(name = "username", unique = true)
	private String username;

	/**
	 * Password
	 */
	private String password;

	/**
	 * First Name
	 */
	private String firstName;

	/**
	 * Last Name
	 */
	private String lastName;

	/**
	 * Role
	 */
	@Enumerated(EnumType.STRING)
	private RoleType role;

	/**
	 * Constructor
	 *
	 * @param source User Details Request
	 */
	public User(final UserDetailsRequest source)
	{
		this.id = UUID.randomUUID();
		this.username = source.username();
		this.password = source.password();
		this.firstName = source.firstName();
		this.lastName = source.lastName();
		this.role = RoleType.valueOf(source.role().toUpperCase(Locale.ROOT));
	}

	/**
	 * Copy other User's Fields to this User
	 *
	 * @param source Source to copy fields from
	 */
	public void copyFieldsExceptID(final User source)
	{
		this.username = source.getUsername();
		this.password = source.password;
		this.firstName = source.firstName;
		this.lastName = source.lastName;
		this.role = source.role;
	}

	/**
	 * Map User Model to User Display Response
	 *
	 * @return User Display Response
	 */
	public UserDisplayResponse toDisplayResponse()
	{
		return new UserDisplayResponse(this.username, this.firstName, this.lastName, this.role.toString());
	}

}
