package net.cassiolandim.biomedcalib.web.model;

import java.util.Collection;

import net.cassiolandim.biomedcalib.entity.BaseEntity;
import net.cassiolandim.biomedcalib.service.PersistableService;

import org.apache.wicket.model.LoadableDetachableModel;

/**
 * @author Cassio Landim
 */
public class EntityListLoadableDetachableModel<T extends BaseEntity<T>, L extends Collection<T>> extends LoadableDetachableModel<L> {
	
	private final PersistableService<T> persistableService;

	public EntityListLoadableDetachableModel(PersistableService<T> persistableService) {
		this.persistableService = persistableService;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected L load() {
		return (L) persistableService.findAll();
	}
}
