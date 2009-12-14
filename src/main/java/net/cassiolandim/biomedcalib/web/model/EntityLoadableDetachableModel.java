package net.cassiolandim.biomedcalib.web.model;

import net.cassiolandim.biomedcalib.entity.BaseEntity;
import net.cassiolandim.biomedcalib.service.PersistableService;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author Cassio Landim
 */
public class EntityLoadableDetachableModel<T extends BaseEntity<T>> extends LoadableDetachableModel<T> {
	
	private Long id;
	private final PersistableService<T> simplePersistableService;

	public EntityLoadableDetachableModel(T entity, PersistableService<T> simplePersistableService) {
		super(entity);
		this.id = entity.getId();
		this.simplePersistableService = simplePersistableService;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	protected T load() {
		return simplePersistableService.find(id);
	}
}
