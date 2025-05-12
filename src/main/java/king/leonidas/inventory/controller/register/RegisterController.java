package king.leonidas.inventory.controller.register;

import king.leonidas.inventory.dto.user.UserDetailsRequest;
import king.leonidas.inventory.dto.user.UserDisplayResponse;
import king.leonidas.inventory.model.user.User;
import king.leonidas.inventory.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Register Controller
 */
@RestController
@RequestMapping("/api/v1/register")
public class RegisterController
{

	/**
	 * User Service
	 */
	private final UserService service;

	/**
	 * Constructor
	 *
	 * @param service User Service
	 */
	public RegisterController(final UserService service)
	{
		this.service = service;
	}

	/**
	 * Register
	 *
	 * @param request User Details Request
	 * @return User Display Response
	 */
	@PostMapping
	ResponseEntity<UserDisplayResponse> register(@RequestBody final UserDetailsRequest request)
	{
		final User user = new User(request);
		final User created = service.createUser(user);

		return ResponseEntity.ok().body(created.toDisplayResponse());
	}
}
