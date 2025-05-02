package king.leonidas.inventory.service.user.decorator;

import king.leonidas.inventory.dto.user.UserDisplayResponse;
import king.leonidas.inventory.model.user.User;
import org.apache.commons.collections4.set.AbstractSetDecorator;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User Set
 */
public class UserSet extends AbstractSetDecorator<User>
{

	/**
	 * Auto Generated Serial Version UID
	 */
	@Serial
	private static final long serialVersionUID = 3600980721476157761L;

	/**
	 * Constructor
	 *
	 * @param users Collection of Users
	 */
	public UserSet(final Collection<User> users)
	{
		super(new HashSet<>(users));
	}

	/**
	 * Convert Set of Users into Set of User Display Response
	 *
	 * @return Set of User Display Response
	 */
	public Set<UserDisplayResponse> toDisplayResponseSet()
	{
		return this.stream().map(User::toDisplayResponse).collect(Collectors.toSet());
	}

}
