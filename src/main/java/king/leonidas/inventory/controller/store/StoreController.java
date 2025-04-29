package king.leonidas.inventory.controller.store;

import king.leonidas.inventory.model.store.Store;
import king.leonidas.inventory.service.store.StoreService;
import king.leonidas.inventory.service.store.decorator.StoreSet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Store Controller
 */
@Controller
@RequestMapping(value = "api/v1/stores")
public class StoreController {

	/**
	 * Store Service
	 */
	private final StoreService storeService;

	/**
	 * Constructor
	 *
	 * @param storeService Store Service
	 */
	public StoreController(final StoreService storeService)
	{
		this.storeService = storeService;
	}

	/**
	 * Create Store
	 *
	 * @param store Store to be created
	 * @return Created Store
	 */
	@PostMapping
	public ResponseEntity<Store> createStore(@RequestBody final Store store)
	{
		return ResponseEntity.ok().body(storeService.createStore(store));
	}

	/**
	 * Find All Stores
	 *
	 * @return Stores in the database
	 */
	@GetMapping
	public ResponseEntity<StoreSet> findAllStores()
	{
		return ResponseEntity.ok().body(storeService.findAllStores());
	}

	/**
	 * Find Store By ID
	 *
	 * @param id Store ID to be fetched
	 * @return Requested Store
	 */
	@GetMapping(path = "{id}")
	public ResponseEntity<Store> findStoreByID(@PathVariable(name = "id") final int id)
	{
		return ResponseEntity.ok().body(storeService.findStoreById(id));
	}

	/**
	 * Delete Store by ID
	 *
	 * @param id Store ID to be deleted
	 * @return OK Response
	 */
	@DeleteMapping(path = "{id}")
	public ResponseEntity<?> deleteStoreByID(@PathVariable(name = "id") final int id)
	{
		storeService.deleteStoreById(id);
		return ResponseEntity.ok().body(null);
	}

	/**
	 * Update Store
	 *
	 * @param store Requested Updated Store
	 * @param id Store ID
	 * @return Updated Store
	 */
	@PutMapping(path = "{id}")
	public ResponseEntity<Store> updateStore(@RequestBody final Store store, @PathVariable(name = "id") final int id)
	{
		return ResponseEntity.ok().body(storeService.updateStore(store, id));
	}
}
