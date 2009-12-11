package net.cassiolandim.biomedcalib.web.model;

import net.cassiolandim.biomedcalib.entity.BaseEntity;
import net.cassiolandim.biomedcalib.service.SimplePersistableService;

import org.apache.wicket.model.LoadableDetachableModel;

public class EntityLoadableDetachableModel<T extends BaseEntity> extends LoadableDetachableModel<T> {
	
	private final Long id;
	private final SimplePersistableService<T> simplePersistableService;

	public EntityLoadableDetachableModel(T entity, SimplePersistableService<T> simplePersistableService) {
		super(entity);
		this.id = entity.getId();
		this.simplePersistableService = simplePersistableService;
	}

	@Override
	protected T load() {
		return simplePersistableService.find(id);
	}
}
