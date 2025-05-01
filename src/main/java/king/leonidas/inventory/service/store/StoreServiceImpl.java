package king.leonidas.inventory.service.store;

import king.leonidas.inventory.model.store.Store;
import king.leonidas.inventory.repository.store.StoreRepository;
import king.leonidas.inventory.service.store.decorator.StoreSet;
import king.leonidas.inventory.service.store.exception.InvalidStoreException;
import king.leonidas.inventory.service.store.exception.StoreNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementation Class of {@link StoreService}
 */
@Service
public class StoreServiceImpl implements StoreService
{

	/**
	 * Store Repository
	 */
	private final StoreRepository storeRepo;

	/**
	 * Constructor
	 *
	 * @param storeRepo Store Repository
	 */
	public StoreServiceImpl(final StoreRepository storeRepo)
	{
		this.storeRepo = storeRepo;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Store createStore(final Store store)
	{
		return storeRepo.saveAndFlush(store);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StoreSet findAllStores()
	{
		return new StoreSet(storeRepo.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Store findStoreById(final int id)
	{
		return storeRepo.findById(id).orElseThrow(StoreNotFoundException::new);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteStoreById(final int id) {
		if (!storeRepo.existsById(id)) {
			throw new StoreNotFoundException();
		}

		storeRepo.deleteById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Store updateStore(final Store store, final int id)
	{
		if (store.getId() != id) {
			throw new InvalidStoreException();
		}

		final Store existing = storeRepo.findById(id).orElseThrow(StoreNotFoundException::new);
		existing.updateFields(store);

		return storeRepo.saveAndFlush(existing);
	}
}
