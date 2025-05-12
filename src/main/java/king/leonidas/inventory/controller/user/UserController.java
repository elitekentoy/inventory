package king.leonidas.inventory.controller.user;

import king.leonidas.inventory.dto.user.UserDetailsRequest;
import king.leonidas.inventory.dto.user.UserDisplayResponse;
import king.leonidas.inventory.model.user.User;
import king.leonidas.inventory.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

/**
 * User Controller
 */
@RequestMapping("api/v1/users")
@Controller
public class UserController {

	/**
	 * User Service
	 */
	private final UserService userService;

	/**
	 * Constructor
	 *
	 * @param userService User Service
	 */
	@Autowired
	public UserController(final UserService userService)
	{
		this.userService = userService;
	}

	/**
	 * Find all Users
	 *
	 * @return all Users in the database
	 */
	@GetMapping
	public ResponseEntity<Set<UserDisplayResponse>> findAllUsers()
	{
		final Set<UserDisplayResponse> users = userService.findAllUsers().toDisplayResponseSet();
		return ResponseEntity.ok().body(users);
	}

	/**
	 * Find User By ID
	 *
	 * @param username Username
	 * @return Requested User
	 */
	@GetMapping("{username}")
	public ResponseEntity<UserDisplayResponse> findUserByUsername(@PathVariable(name = "username") final String username)
	{
		final User user = userService.findUserByUsername(username);
		return ResponseEntity.ok().body(user.toDisplayResponse());
	}

	/**
	 * Update User
	 *
	 * @param request User to be updated
	 * @param username Username of the User
	 */
	@PutMapping("{username}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@RequestBody final UserDetailsRequest request,
						   @PathVariable(name = "username") final String username)
	{
		if (!StringUtils.equalsIgnoreCase(request.username(), username)) {
			throw new IllegalArgumentException();
		}
		userService.updateUser(new User(request));
	}

	/**
	 * Delete User By ID
	 *
	 * @param username Username to be deleted
	 */
	@DeleteMapping("{username}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserByUsername(@PathVariable(name = "username") final String username)
	{
		userService.deleteUserByUsername(username);
	}
}
