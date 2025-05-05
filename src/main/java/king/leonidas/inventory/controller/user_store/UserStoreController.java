package king.leonidas.inventory.controller.user_store;

import king.leonidas.inventory.dto.user_store.CreateStoreRequest;
import king.leonidas.inventory.dto.user_store.StoreWithOwnerResponse;
import king.leonidas.inventory.model.store.Store;
import king.leonidas.inventory.model.user_store.StoreWithOwner;
import king.leonidas.inventory.service.user_store.UserStoreService;
import king.leonidas.inventory.service.user_store.decorator.StoreWithOwnerSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("api/v1/users/{username}/stores")
@RestController
public class UserStoreController
{

	/**
	 * User Store Service
	 */
	private final UserStoreService userStoreService;

	/**
	 * Constructor
	 *
	 * @param userStoreService User Store Service
	 */
	@Autowired
	public UserStoreController(final UserStoreService userStoreService)
	{
		this.userStoreService = userStoreService;
	}

	/**
	 * Find All Stores of User
	 *
	 * @param username Username
	 * @return Stores of the User
	 */
	@GetMapping
	public ResponseEntity<Set<StoreWithOwnerResponse>> findAllStoresOfUser(
			@PathVariable(name = "username") final String username)
	{
		final StoreWithOwnerSet stores = userStoreService.findAllStoresByUsername(username);
		return ResponseEntity.ok().body(stores.toStoreWithOwnerResponses());
	}

	/**
	 * Find Specific Store of User
	 *
	 * @param username Username
	 * @param storeId Store ID
	 * @return Store of the User
	 */
	@GetMapping("{storeId}")
	public ResponseEntity<StoreWithOwnerResponse> findSpecificStore(
			@PathVariable(name = "username") final String username,
			@PathVariable(name = "storeId") final int storeId)
	{
		final StoreWithOwner store = userStoreService.findSpecificStore(username, storeId);
		return ResponseEntity.ok().body(store.toStoreWithOwnerResponse());
	}

	/**
	 * Create Store
	 *
	 * @param request Create Store Request
	 * @param username Username
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createStore(@RequestBody final CreateStoreRequest request,
							@PathVariable(name = "username") final String username)
	{
		final Store store = new Store(request);
		userStoreService.createStore(store, username);
	}

	/**
	 * Delete All Stores of the User
	 *
	 * @param username Username
	 */
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStoresOfUser(@PathVariable(name = "username") final String username)
	{
		userStoreService.deleteUserStores(username);
	}

	/**
	 * Delete Specific Store
	 *
	 * @param username Username
	 * @param storeId Store ID
	 */
	@DeleteMapping("{storeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSpecificStore(@PathVariable(name = "username") final String username,
									@PathVariable(name = "storeId") final int storeId)
	{
		userStoreService.deleteSpecificStore(username, storeId);
	}
}
